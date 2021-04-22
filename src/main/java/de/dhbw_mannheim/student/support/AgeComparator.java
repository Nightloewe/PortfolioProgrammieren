package de.dhbw_mannheim.student.support;

import de.dhbw_mannheim.student.model.Person;

import java.time.LocalDate;

public class AgeComparator extends OrderedComparator<Person> {

    private LocalDate date;

    public AgeComparator() {
        this(LocalDate.now());
    }

    public AgeComparator(LocalDate date) {
        this(SortDirection.ASCENDING, date);
    }

    public AgeComparator(SortDirection direction, LocalDate date) {
        this.direction = direction;
        this.date = date;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int compare(Person thisPerson, Person other) {
        LocalDate thisBirthDate = thisPerson.getBirthDate();
        LocalDate otherBirthDate = other.getBirthDate();

        if(this.direction == SortDirection.ASCENDING) {
            // Aufsteigend ist Standardreihenfolge
            return this.compareAge(thisBirthDate, otherBirthDate);
        } else {
            // Für absteigend wird einfach die Reihenfolge umgekehrt, d.h.
            // aus 1 wird z.B. -1
            // aus -1 wird 1
            // und 0 bleibt 0
            return this.compareAge(otherBirthDate, thisBirthDate);
        }
    }

    private int compareAge(LocalDate thisAge, LocalDate otherAge) {
        //Umgekehrte Reihenfolge, da Datum die Daten so strukturiert, dass das
        //jüngste Datum die höchste Zahl bekommt, und das älteste die niedrigste
        //In aufsteigender Reihenfolge, wollen wir aber das Alter und daher
        //soll das älteste Datum am höchsten sein.
        return otherAge.compareTo(thisAge);
    }
}
