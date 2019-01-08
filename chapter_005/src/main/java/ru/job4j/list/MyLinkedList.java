package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class MyLinkedList<E>.
 *
 * @author dshustrov
 * @version 1
 * @since 06.01.2019
 */
public class MyLinkedList<E> implements Iterable<E> {
    /**
     * size list.
     */
    private int size;
    /**
     * first element in the list.
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
     * Method add elements in the list.
     *
     * @param value add of element.
     */
    public void add(E value) {
        Node<E> newLink = new Node<>(value);
        newLink.next = first;
        first = newLink;
        size++;
    }

    /**
     * Method delete elements of the list.
     */
    public E delete() {
        E result = null;
        if (size > 0) {
            Node<E> temp = first;
            result = first.value;
            first = temp.next;
            size--;
        }
        return result;
    }

    /**
     * Method return size list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Method returns elements of list.
     *
     * @param index elements.
     */
    public E get(int index) {
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private Node<E> res = first;

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
                E result = this.res.value;
                this.res = this.res.next;
                count++;
                return result;
            }
        };
    }
}
