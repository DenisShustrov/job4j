package ru.job4j.list;

/**
 * Class Cyclicality.
 *
 * @author dshustrov
 * @version 1
 * @since 08.01.2019
 */
public class Cyclicality {

    /**
     * Class is intended for data storage.
     */
    public static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }

    /**
     * Method determines that the list contains closures.
     *
     * @param first elements list.
     */
    public boolean hasCycle(Node first) {
        boolean result;
        if (first == null) {
            return false;
        }
        Node firstElement = first;
        Node secondElement = first.next;
        while (true) {
            if (firstElement == secondElement) {
                result = true;
                break;
            }
            if (firstElement == null || secondElement == null || secondElement.next == null) {
                result = false;
                break;
            }
            firstElement = firstElement.next;
            secondElement = secondElement.next.next;
        }
        return result;
    }


}
