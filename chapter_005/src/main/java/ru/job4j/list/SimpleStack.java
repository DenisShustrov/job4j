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
public class SimpleStack<E> implements Iterable<E> {
    /**
     * size stack.
     */
    private int size;
    /**
     * first element in the stack.
     */
    private Node<E> first;

    /**
     * Class is intended for data storage.
     */
    private static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }

    /**
     * Method add elements in the stack.
     *
     * @param value add of element.
     */
    public void push(E value) {
        Node<E> newLink = new Node<>(value);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Method return and delete first element in the stack.
     */
    public E poll() {
        E result = null;
        if (size > 0) {
            Node<E> res = this.first;
            result = this.first.value;
            this.first = res.next;
            size--;
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int expectedModCount = size;

            private int count;

            @Override
            public boolean hasNext() {
                return size > this.count;
            }

            @Override
            public E next() {
                if (expectedModCount != size) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> res = first;
                E result = first.value;
                first = res.next;
                count++;
                return result;
            }
        };
    }
}
