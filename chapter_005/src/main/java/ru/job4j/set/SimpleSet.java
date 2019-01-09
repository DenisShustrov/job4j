package ru.job4j.set;

import ru.job4j.list.DynamicContainer;

import java.util.Iterator;

/**
 * Class SimpleSet<E>.
 *
 * @author dshustrov
 * @version 1
 * @since 09.01.2019
 */
public class SimpleSet<E> implements Iterable<E> {

    /**
     * container of elements.
     */
    private DynamicContainer<E> dc = new DynamicContainer<>();

    /**
     * Method add elements in the container if element missing in container.
     *
     * @param e add element.
     */
    public void add(E e) {
        if (!contains(e) & e != null) {
            dc.add(e);
        }
    }

    /**
     * Method check the presence of an element in the set.
     * Returns true if the element is found.
     *
     * @param e checked element.
     */
    public boolean contains(E e) {
        boolean result = false;
        for (int i = 0; i < dc.size(); i++) {
            if (dc.get(i).equals(e)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Method returns size container.
     */
    public int size() {
        return dc.size();
    }

    @Override
    public Iterator<E> iterator() {
        return dc.iterator();
    }
}
