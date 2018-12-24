package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class EvenIterator
 *
 * @author dshustrov
 * @version 1
 * @since 22.12.2018
 */
public class EvenIterator implements Iterator {
    /**
     * numbers array of integers.
     */
    private final int[] numbers;
    /**
     * index counter position.
     */
    private int index;

    /**
     * Constructor.
     *
     * @param numbers array of integers.
     */
    public EvenIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (numbers.length > index) {
            if (numbers[index] % 2 == 0) {
                result = true;
                break;
            } else {
                index++;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        Object result;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        result = numbers[index];
        index++;
        if (numbers.length == index && numbers[numbers.length - 1] % 2 != 0) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
