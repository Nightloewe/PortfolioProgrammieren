package de.dhbw_mannheim.student.support;

import java.util.Comparator;

public abstract class OrderedComparator<T> implements Comparator<T> {

    protected SortDirection direction;

    /**
     * @return the direction
     */
    public SortDirection getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }
}
