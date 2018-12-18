package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class JaggedArrayIterator.
 *
 * @author dshustrov
 * @version 1
 * @since 17.12.2018
 */
public class JaggedArrayIterator implements Iterator {
    /**
     * array two-dimensional array of integers.
     */
    private final int[][] array;
    /**
     * index counter position.
     */
    private int index;
    /**
     * row position.
     */
    private int row;
    /**
     * cells position.
     */
    private int cells;

    /**
     * Constructor.
     *
     * @param array two-dimensional array of integers.
     */
    public JaggedArrayIterator(final int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return array.length >= index;
    }

    @Override
    public Object next() {
        if (array[row].length == cells) {
            row++;
            cells = 0;
        }
        if (array.length < index) {
            throw new NoSuchElementException();
        }
        index++;
        return array[row][cells++];
    }

}
