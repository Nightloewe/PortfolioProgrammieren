package de.dhbw_mannheim.student.support;

import de.dhbw_mannheim.student.model.Person;

import java.time.LocalDate;
import java.util.Comparator;

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
        long thisAge = thisPerson.getAge(this.date);
        long otherAge = other.getAge(this.date);

        if(this.direction == SortDirection.ASCENDING) {
            return this.compareAge(thisAge, otherAge);
        } else {
            return this.compareAge(otherAge, thisAge);
        }
    }

    private int compareAge(long thisAge, long otherAge) {
        if(thisAge > otherAge) {
            return 1;
        } else if(thisAge < otherAge) {
            return -1;
        }
        return 0;
    }
}
