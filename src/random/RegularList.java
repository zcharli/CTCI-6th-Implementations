package random;

import java.util.Iterator;

/**
 * Created by czl on 04/09/16.
 */
public class RegularList<T> implements Iterable<T> {

    private T[] elements;

    public RegularList() {

    }

    public RegularList(T[] a) {
        elements = a;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < elements.length - 1;
            }

            @Override
            public T next() {
                return elements[currentIndex++];
            }
        };
        return it;
    }
}
