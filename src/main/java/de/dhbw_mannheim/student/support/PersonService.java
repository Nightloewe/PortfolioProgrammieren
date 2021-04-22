package de.dhbw_mannheim.student.support;

import de.dhbw_mannheim.student.exceptions.FileIsDirectoryException;
import de.dhbw_mannheim.student.model.Person;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PersonService {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Ladet eine Liste von Personen aus einer
     * Datei.
     *
     * @param path Path
     * @return Liste von {@link de.dhbw_mannheim.student.model.Person}
     * @throws FileNotFoundException wenn die Datei nicht existiert
     * @throws FileIsDirectoryException wenn die Datei ein Ordner ist
     * @throws IOException bei anderen IO Fehlern
     */
    public List<Person> loadPersons(Path path) throws IOException {
        //Prüft, ob die Datei existiert
        if(!Files.exists(path)) {
            throw new FileNotFoundException(path.toAbsolutePath().toString());
        }

        //Prüft, ob die Datei ein Ordner ist
        if(Files.isDirectory(path)) {
           throw new FileIsDirectoryException(path.toAbsolutePath().toString());
        }

        //Liest die Datei ein und separatiert nach Line Breaks
        List<String> csv = Files.readAllLines(path);
        List<Person> persons = new ArrayList<>(csv.size());

        //Iteration über die Einträge
        for (String entry : csv) {
            if (entry.isBlank()) {
                continue;
            }

            //Personen werden komma separiert angegeben
            //z.B. Maulwurf,Hans,21.04.1943,Springfield
            String[] contents = entry.split(",");

            //Wenn die Zeile keine Kommas enthält ignorieren wir sie, tut mir leid.
            if (contents.length == 0) {
                continue;
            }

            Person person = new Person();


            //Iteriert über die getrennten Strings
            //Diese Methode erlaubt einige Dynamik, bspw.
            //Kann man den Wohnort weglassen oder gar das
            //Geburtsdatum
            for (int j = 0; j < contents.length; ++j) {
                switch (j) {
                    case 0:
                        person.setLastName(contents[j]);
                        break;
                    case 1:
                        person.setSurname(contents[j]);
                        break;
                    case 2:
                        person.setBirthDate(LocalDate.parse(contents[j], formatter));
                        break;
                    case 3:
                        person.setCity(contents[j]);
                        break;
                }
            }

            persons.add(person);
        }

        return persons;
    }


    /**
     * Sortiert eine Liste nach MergeSort.
     *
     * @param collection die unsortierte Liste
     * @param comparator der Vergleicher
     */
    @SuppressWarnings("unchecked")
    public <T> void sort(List<T> collection, Comparator<T> comparator) {
        Object[] result = this.mergeSort(collection.toArray(), (Comparator<Object>) comparator);

        //Die Liste muss hier nur noch geupdatet werden mit den Ergebnissen aus dem Sortieralgorithmus
        for(int i = 0; i < collection.size(); i++) {
            collection.set(i, (T) result[i]);
        }
    }

    /**
     * Sortiert eine Liste mit Hilfe des MergeSort Algorithmus.
     *
     * Bei MergeSort werden solange die Listen halbiert, bis man an einen Punkt
     *      kommt, bei der eine linke und rechte Liste mit nur jeweils einem Element entstehen.
     *      Danach werden diese linken und rechten Listen zusammengefügt und dabei sortiert.
     *      Bei der Zusammenfügung wird immer das erste Element verglichen und das kleinere
     *      der beiden angefügt. Dabei wird das angefügte Element aus der jeweiligen
     *      Listen "entfernt" (In unserem Fall wird der Index für diese Liste um eins erhöht).
     *      Bei der Zusammenfügung kann es zu dem Fall kommen, dass mindestens ein Element
     *      vorkommt, welches in der ersten while-Schleife nicht verarbeitet wird. Dieses
     *      wird dann in einer der nächsten while-Schleifen noch angefügt.
     *
     *      <B>Ein Beispiel:</B>
     *      <code>
     *      Man habe das unsortierte Array [5, 3, 23, 2, 1]
     *
     *      Das Array wird halbiert in linkes Array L[] und rechtes Array R[]
     *      L[] = [5, 3]
     *      R[] = [23, 2, 1]
     *
     *      In der nächsten Rekursionsebene wird nun geteilt
     *      --- A1[] = [5, 3]
     *      --- L1[] = [5]
     *      --- R1[] = [3]
     *      ---
     *      --- Da nun L1 und R1 nur ein Element enthalten wird nicht weiter halbiert
     *      --- L1 und R1 werden nun gemerged:
     *      ---
     *      --- M1[] = [null, null]
     *      --- Da L größer ist als R1, wird zuerst R1 eingefügt
     *      --- M1[] = [3,null]
     *      --- Danach wird durch die zweite While noch L1 angefügt
     *      --- M1[] = [3, 5]
     *
     *      L ist nun bereits fertig sortiert
     *      L[] = [3,5]
     *
     *      Nun wird R behandelt und rekursiv geteilt:
     *      --- A2[] = [23, 2, 1]
     *      --- L2[] = [23]
     *      --- R2[] = [2,1]
     *      ---
     *      --- In diesem wird L2 nicht weiter halbiert, R2 aber schon
     *      ------ A2.1[] = [2,1]
     *      ------ L2.1[] = [2]
     *      ------ R2.1[] = [1]
     *      ------
     *      ------ Nun wird aber nicht mehr geteilt, daher wird jetzt gemerged:
     *      ------ M2.1[] = [null, null]
     *      ------ Da L größer als 1 ist, wird 1 zuerst eingefügt
     *      ------ M2.1[] = [1, null]
     *      ------ Danach wird R eingefügt
     *      ------ M2.1[] = [1,2]
     *      --- R2 ist nun fertig sortiert
     *      --- R2[] = [1,2]
     *      ---
     *      --- Jetz wird L2 und R2 gemerged:
     *      --- M2[] = [null, null, null]
     *      --- Da L2[0] größer ist als R2[0], wird nun zuerst R2[0] eingefügt:
     *      --- M2[] = [1, null, null]
     *      --- Da L2[0] wieder größer ist als R2[1], wird nun R2[1] eingefügt:
     *      --- M2[] = [1, 2, null]
     *      --- Zum Schluss wird dann L2 noch angefügt
     *      --- M2[] = [1, 2, 23]
     *
     *      L[] = [3, 5]
     *      R[] = [1, 2, 23]
     *
     *      Nun sind R und L sortiert und kann für das "Mergen" der kompletten
     *      Liste genutzt werden:
     *      M[] = [null, null, null, null, null]
     *
     *      Nun wird zuerst die 1 aus R eingefügt, danach die 2 aus R, danach die 3
     *      aus L, danach die 5 aus L und zum Schluss wird noch die 23 aus R angefügt.
     *      M[] = [1, 2, 3, 5, 23]
     *
     *      M ist nun die sortierte Liste.
     *      </code>
     *
     * @param list die Liste
     * @param comparator der Vergleicher
     */
    public Object[] mergeSort(Object[] list, Comparator<Object> comparator) {
        // Wenn die List nur ein Element hat, geben wir sie zurück
        if(list.length <= 1) {
            return list;
        }

        //Die Liste wird in eine rechte und linke Liste geteilt
        int separator = list.length / 2;

        //Die Linke Liste geht vom Index 0 bis separator - 1
        Object[] left = this.copyArray(list, 0, separator);
        //Die Rechte Liste geht von separator bis list.length - 1
        Object[] right = this.copyArray(list, separator, list.length);

        //Führe mergeSort nochmal auf Unterlisten auf
        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);

        //Verbinde beide Listen
        return merge(left, right, comparator);
    }
  
    /**
     * Füge die rechte und die linke Liste zusammen für
     * MergeSort zusammen und sortiert dabei die Elemente
     * nach ihrer durch den Vergleicher vorgegebenen Größe.
     *
     * @param leftList die linke Liste
     * @param rightList die rechte Liste
     * @param comparator der Vergleicher
     * @return die verbundene und sortierte Liste
     */
    private static Object[] merge(Object[] leftList, Object[] rightList, Comparator<Object> comparator) {
        //Die verbundene Liste ist so groß wie Linke List Länge plus Rechte Liste Länge
        Object[] list = new Object[leftList.length + rightList.length];

        //Erstelle Indexe für neue Liste, linke Liste und rechte Liste
        int idx = 0, rIdx = 0, lIdx = 0;

        //Solange nicht vollständig durch die linke oder rechte Liste iteriert
        // wurde, wird iteriert
        while(lIdx < leftList.length && rIdx < rightList.length) {
            //Vergleiche nach dem gegebenen Vergleicher
            int res = comparator.compare(leftList[lIdx], rightList[rIdx]);
            if(res < 1) {
                //Wenn das Element der linken Liste kleiner ist, wird es eingefügt
                list[idx] = leftList[lIdx];
                //erhöhe nur den linken Index
                ++lIdx;
            } else {
                //Wenn das Element der rechten Liste kleiner ist, wird es eingefügt
                list[idx] = rightList[rIdx];
                //erhöhe nur den rechten Index, aber nicht den linken
                ++rIdx;
            }
            //erhöhe den Index für das verbundene Array
            ++idx;
        }

        //Füge alle restlichen linken Elemente an
        while(lIdx < leftList.length) {
            list[idx] = leftList[lIdx];
            ++lIdx;
            ++idx;
        }

        //Füge alle restlichen rechten Elemente an
        while(rIdx < rightList.length) {
            list[idx] = rightList[rIdx];
            ++rIdx;
            ++idx;
        }

        //Nun haben wir eine neue sortierte Liste
        return list;
    }

    @SuppressWarnings("unchecked")
    private <T> T[] copyArray(T[] array, int start, int endExclusive) {
        T[] copied = (T[]) new Object[endExclusive - start];

        System.arraycopy(array, start, copied, 0, endExclusive - start);

        return copied;
    }
}