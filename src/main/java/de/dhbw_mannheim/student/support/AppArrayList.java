package de.dhbw_mannheim.student.support;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class AppArrayList<T> implements List<T> {

    public static void main(String[] args) {
        AppArrayList<String> list = new AppArrayList<>();

        list.add("Test");
        list.add("Test2");
        list.add("Test3");

        print(list);
    }

    public static void print(List<String> list) {
        for(String s : list) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }

    private Object[] array;
    private int count;

    public AppArrayList() {
        this(20);
    }

    public AppArrayList(int size) {
        this.array = new Object[size];
    }

    /**
     * Vergrößert/verkleinert das Array auf den
     * gegebenen Wert
     *
     * @param i die neue Größe
     */
    private void resizeArray(int i) {
        if(i < 10) {
            throw new IllegalArgumentException("Min size of this array list is 10");
        }

        System.arraycopy(this.array, 0, this.array, 0, i);
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public boolean isEmpty() {
        return this.count < 0;
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>(this);
    }

    @Override
    public Object[] toArray() {
        return this.toArray(new Object[] {});
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        if(!a.getClass().isAssignableFrom(this.array.getClass())) {
            throw new IllegalArgumentException("Given array is not of right type");
        }

        Object[] copied = new Object[this.count];

        System.arraycopy(this.array, 0, copied, 0, this.count);

        return (T1[]) copied;
    }

    @Override
    public boolean add(T t) {
        if(this.count >= this.array.length) {
            this.resizeArray(this.array.length + 10);
        }

        this.array[count] = t;
        ++count;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = this.indexOf(o);

        if(index < 0) {
            return false;
        }

        return this.remove(index) != null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o : c) {
            if(!this.contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for(T t : c) {
            this.add(t);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object o : c) {
            this.remove(o);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.array = new Object[10];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if(index > this.size()) {
            throw new IndexOutOfBoundsException();
        }

        return (T) this.array[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        if(index > this.size()) {
            throw new IndexOutOfBoundsException();
        }

        var temp = this.array[index];

        this.array[index] = element;

        return (T) temp;
    }

    @Override
    public void add(int index, T element) {
        // Index 0 1 2 3 4 5 6 7 8
        //           ^ dort soll z.B. eingefügt werden

        // Einfügen nach List Interface nur in bestehenden Bereich möglich
        if(index < this.count) {
            // Alle Elemente rechts sollen gesichert werden
            Object[] temp = new Object[this.array.length - index];

            // Kopiere alle Elemente rechts der 2 in ein temporäres Array ein
            System.arraycopy(this.array, index, temp, 0, temp.length);

            // Setze Element
            this.array[index] = element;

            // Wenn das Array durch das neue Element zu klein ist, müssen wir es vergrößern
            if(this.array.length < index + temp.length) {
                this.resizeArray(index + temp.length + 10);
            }

            // Kopieren der Daten rechts des Index in das Array
            System.arraycopy(temp, 0, this.array, index + 1, temp.length);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if(index > this.size()) {
            throw new IndexOutOfBoundsException();
        }

        Object[] temp = new Object[this.array.length - index];

        System.arraycopy(this.array, index, temp, 0, temp.length);

        Object removed = this.array[index];
        this.array[index] = null;
        --count;

        System.arraycopy(temp, 0, this.array, index - 1, temp.length);

        return (T) removed;
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < 0; i++) {
            Object obj = this.array[i];

            if(obj == o
                || obj.equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private class ArrayIterator<T> implements Iterator<T> {
        private int index;
        private AppArrayList<T> list;

        public ArrayIterator(AppArrayList list) {
            this.list = list;
            this.index = -1;
        }


        @Override
        public boolean hasNext() {
            return index < list.size();
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if(this.hasNext()) {
                ++index;

                return (T) this.list.array[index];
            }

            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            this.list.remove(index);

            //Da dadurch das nächste Element auf den derzeitigen Index fällt, müssen wir den
            //Index für den nächsten Aufruf von next() auf den vorigen Wert setzen.
            --index;
        }
    }
}
