package de.dhbw_mannheim.student.support;

import de.dhbw_mannheim.student.exceptions.FileIsDirectoryException;
import de.dhbw_mannheim.student.model.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

        //Hier musst du die originale Collection quasi updaten
        //collection.set(selbe Index, selbes Element wie Index)                                                          //collection.set(selbe Index, selbes Element wie Index)
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
        if(list.length <= 1) {

            return list;

        }

        int midpoint = list.length / 2;

        // Declaration left und right Objekt
        Object[] left = new Object[midpoint];
        Object[] right;

        if(list.length % 2 == 0) {

            right = new Object[midpoint];

        } else {

            right = new Object[midpoint + 1];

        }

        // Füllen des linken und rechten Objekt
        for(int i=0; i < midpoint; i++) {

            left[i] = list[i];

        }

        for(int j=0; j < right.length; j++) {

            right[j] = list[midpoint+j];

        }

        Object [] result = new Object[list.length];

        // Recursiver Aufruf mit left und right
        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);

        // Get the merged left and right arrays.
        result = merge(left, right, comparator);

        // return der zusammengerührten liste
        return result;

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


        // Zusammengeführtes Ergebnisarray
        Object[] result = new Object[leftList.length + rightList.length];

        // Deklariert und Initialisiert pointers für alle Arrays
        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;

        // gibt es ellemente in beiden
        while (leftPointer < leftList.length || rightPointer < rightList.length) {

            // Ist ein Item in beiden? Wenn beide ein Element haben wird geprüft welches größer ist
            if (leftPointer < leftList.length && rightPointer < rightList.length) {

                // Prüft ob das linke item kleiner als das rechte ist
                if (comparator.compare(leftList, rightList) < 1) {                                                            //Objekte miteinander Vergleichen welches kleiner ist?

                    result[resultPointer++] = leftList[leftPointer++];

                }
                // Wenn das rechte Item größer als das linke ist werden dies in result getauscht
                else {

                    result[resultPointer++] = rightList[rightPointer++];

                }

            }
        }
        return result;
    }

            //  public Object[] merge(Object[] leftList, Object[] rightList, Comparator<Object> comparator) {
            //      return null;
            //  }



}