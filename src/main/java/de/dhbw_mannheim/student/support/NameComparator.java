package de.dhbw_mannheim.student.support;

import de.dhbw_mannheim.student.model.Person;


public class NameComparator extends OrderedComparator<Person> {

    public NameComparator() {
        this(SortDirection.ASCENDING);
    }

    public NameComparator(SortDirection direction) {
        this.direction = direction;
    }

    @Override
    public int compare(Person thisPerson, Person other) {
        if(this.direction == SortDirection.ASCENDING) {
            return thisPerson.getLastName().compareTo(other.getLastName());
        } else {
            return other.getLastName().compareTo(thisPerson.getLastName());
        }
    }
}
