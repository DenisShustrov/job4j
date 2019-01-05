package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class DynamicContainer<E>.
 *
 * @author dshustrov
 * @version 1
 * @since 05.01.2019
 */
public class DynamicContainer<E> implements Iterable<E> {

    /**
     * container of elements.
     */
    private Object[] container;

    /**
     * index counter.
     */
    private int index;

    /**
     * Constructor default.
     */
    public DynamicContainer() {
        this.container = new Object[10];
    }

    /**
     * Constructor.
     *
     * @param containerSize set size.
     */
    public DynamicContainer(int containerSize) {
        this.container = new Object[containerSize];
    }

    /**
     * Method returns the number of items.
     */
    public int size() {
        return index;
    }

    /**
     * Method add elements in the container.
     *
     * @param value add of elements.
     */
    public void add(E value) {
        if (this.container.length == this.index) {
            Object[] newContainer = new Object[this.container.length + 10];
            System.arraycopy(this.container, 0, newContainer, 0, this.container.length);
            this.container = newContainer;
        }
        this.container[index++] = value;
    }

    /**
     * Method returns elements of containers.
     *
     * @param index add of elements.
     */
    public E get(int index) {
        E result = null;
        if (this.index >= index) {
            result = (E) this.container[index];
        }
        return result;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int expectedModCount = index;

            private int count;

            @Override
            public boolean hasNext() {
                return index > this.count;
            }

            @Override
            public E next() {
                if (expectedModCount != index) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[count++];
            }
        };
    }
}
