package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleStack<E>.
 *
 * @author dshustrov
 * @version 1
 * @since 08.01.2019
 */
public class SimpleStack<E> {
    /**
     * list elements.
     */
    private MyLinkedList<E> stack = new MyLinkedList<>();

    /**
     * Method add elements in the stack.
     *
     * @param value add of element.
     */
    public void push(E value) {
        stack.add(value);
    }

    /**
     * Method return and delete first element in the stack.
     */
    public E poll() {
        return stack.delete();
    }

    /**
     * Method return size stack.
     */
    public int getSize() {
        return stack.getSize();
    }
}
