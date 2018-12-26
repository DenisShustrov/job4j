package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleArray<T>
 *
 * @author dshustrov
 * @version 1
 * @since 24.12.2018
 */
public class SimpleArray<T> implements Iterable<T> {
    /**
     * array array T.
     */
    private T[] array;
    /**
     * index count position.
     */
    private int index;

    /**
     * Constructor.
     *
     * @param index length array.
     */
    public SimpleArray(Integer index) {
        this.array = (T[]) new Object[index];
    }

    /**
     * Method add element model in the first free cell.
     *
     * @param model adding element.
     */
    public void add(T model) {
        if (array.length > index) {
            array[index++] = model;
        }
    }

    /**
     * Method set element under the specified index on the element model.
     *
     * @param index replaceable item position.
     * @param model replaceable element.
     */
    public void set(int index, T model) {
        if (array.length > index) {
            array[index] = model;
        }
    }

    /**
     * Method return count elements in the array.
     *
     */
    public int size() {
        return this.index;
    }

    /**
     * Method delete element under the specified index.
     *
     * @param index delete position.
     */
    public T[] remove(int index) {
        if (array.length > index) {
            System.arraycopy(array, index + 1, array, index, array.length - index - 1);
            this.index--;
        }
        return array;
    }

    /**
     * Method get element under the specified index.
     *
     * @param index get element.
     */
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            /**
             * position count for iterator.
             */
            private int position;

            @Override
            public boolean hasNext() {
                return index >= position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(position++);
            }
        };
    }
}
