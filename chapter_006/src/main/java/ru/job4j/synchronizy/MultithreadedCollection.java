package ru.job4j.synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicContainer;

import java.util.Iterator;

/**
 * Class MultithreadedCollection.
 *
 * @author dshustrov
 * @version 1
 * @since 22.01.2019
 */
@ThreadSafe
public class MultithreadedCollection<E> implements Iterable<E> {
    /**
     * mc storage of elements.
     */
    @GuardedBy("this")
    private DynamicContainer<E> dc = new DynamicContainer<>();
    /**
     * Constructor default.
     */
    public MultithreadedCollection() {
    }
    /**
     * Method returns the number of elements.
     */
    public synchronized int getSizeMC() {
        return dc.size();
    }
    /**
     * Method add elements in the storage.
     *
     * @param value add of elements.
     */
    public synchronized void add(E value) {
        dc.add(value);
    }
    /**
     * Method returns elements of storage.
     *
     * @param index return element number.
     */
    public synchronized void get(int index) {
        dc.get(index);
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy().iterator();
    }

    private synchronized DynamicContainer<E> copy() {
        DynamicContainer<E> container = new DynamicContainer<>();
        for (E element : this.dc) {
            if (element != null) {
                container.add(element);
            }
        }
        return container;
    }

}
