package de.dhbw_mannheim.student.model;

import de.dhbw_mannheim.student.support.PersonService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Person {

    private String surname;
    private String lastName;

    private LocalDate birthDate;

    private String city;

    /**
     * Erstellt eine leere Person.
     */
    public Person() {}

    /**
     * Erstellt eine neue Person.
     *
     * @param surname Vorname
     * @param lastName Nachname
     * @param birthDate Geburtsdatum
     * @param city Wohnort
     */
    public Person(String surname, String lastName, LocalDate birthDate, String city) {
        this.surname = surname;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.city = city;
    }

    /**
     * @return der Vorname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname setzt den Vornamen
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return der Nachname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName setzt den Vornamen
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return das Geburtsdatum
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Berechnet das Alter einer Person zu dem
     * angegebenen Datum.
     *
     * @param date das Datum
     * @return das Alter in Jahren
     */
    public long getAge(LocalDate date) {
        if(this.birthDate == null) {
            return 0L;
        } else {
            return Period.between(this.birthDate, date).getYears();
        }
    }

    /**
     * @param birthDate setzt das Geburtsdatum
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return den Wohnort
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city setzt den Wohnort
     */
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return this.lastName + ", " + this.surname + ", " + PersonService.formatter.format(this.birthDate)
                + ", " + city + ", " + this.getAge(LocalDate.now());
    }
}
