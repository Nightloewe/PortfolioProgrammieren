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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PersonService {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static NameComparator nameComparator = new NameComparator();
    private static AgeComparator ageComparator = new AgeComparator();

    public static void main(String[] args) {
        Path path = Path.of("C:\\").resolve("Users").resolve("Jonas").resolve("Desktop").resolve("persons.csv");

        PersonService service = new PersonService();

        try {
            List<Person> persons = service.loadPersons(path);

            System.out.println("Unsorted: ");
            print(persons);

            Collections.sort(persons, nameComparator);

            System.out.println("Sorted by Last Name ASC: ");
            print(persons);

            nameComparator.setDirection(SortDirection.DESCENDING);
            Collections.sort(persons, nameComparator);

            System.out.println("Sorted by Last Name DESC: ");
            print(persons);

            Collections.sort(persons, ageComparator);

            System.out.println("Sorted by Age ASC: ");
            print(persons);

            ageComparator.setDirection(SortDirection.DESCENDING);

            Collections.sort(persons, ageComparator);

            System.out.println("Sorted by Age DESC: ");
            print(persons);
        } catch (IOException e) {
            System.err.println("Exception occured: " + e.getMessage());
        }
    }

    public static void print(List<Person> persons) {
        persons.forEach(System.out::println);
    }

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

    @SuppressWarnings("unchecked")
    public <T> void sort(List<T> collection, Comparator<T> comparator) {
        Object[] result = this.mergeSort(collection.toArray(), (Comparator<Object>) comparator);

        //Hier musst du die originale Collection quasi updaten
        //collection.set(selbe Index, selbes Element wie Index)                                                          //collection.set(selbe Index, selbes Element wie Index)
    }

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

    // Merges the leftList and rightList array in ascending order.
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
                if (leftList[leftPointer] < rightList[rightPointer]) {                                                            //Objekte miteinander Vergleichen welches kleiner ist?

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