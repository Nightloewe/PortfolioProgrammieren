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
import java.util.Collections;
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

}
