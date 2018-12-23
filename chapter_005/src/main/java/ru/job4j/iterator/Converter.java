package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Converter
 *
 * @author dshustrov
 * @version 1
 * @since 23.12.2018
 */
public class Converter {
    /**
     * Method return iterator integers.
     *
     * @param it iterator iterators integers.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            Iterator<Integer> iter = it.next();
            int count;

            @Override
            public boolean hasNext() {
                if (it.hasNext() && !iter.hasNext()) {
                    iter = it.next();
                }
                return iter.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                count = iter.next();
                return count;
            }
        };
    }
}
