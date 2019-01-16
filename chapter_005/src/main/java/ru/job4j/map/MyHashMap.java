package ru.job4j.map;

import java.util.*;

public class MyHashMap<K, V> implements Iterable<V> {
    /**
     * size container.
     */
    private int size;

    /**
     * count of changes.
     */
    private int mod;

    /**
     * container of elements.
     */
    private Node<K, V>[] container;

    public MyHashMap() {
        container = new Node[16];
    }

    /**
     * Implements hash function.
     */
    public int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {

            private int expectedModCount = mod;

            private int count;

            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != mod) {
                    throw new ConcurrentModificationException();
                }
                return size > index;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                for (int i = count; i < container.length; i++) {
                    if (container[i] != null) {
                        count = i;
                        break;
                    }
                }
                index++; // без еще одной переменнной не работает ->  count = i -> i может быть каким угодно
                return container[count++].value;
            }
        };
    }

    /**
     * Base node.
     */
    private class Node<K, V> {
        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<K, V> node = (Node<K, V>) o;
            return Objects.equals(key, node.key)
                    &&
                    Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    /**
     * Method increases the capacity of the container in case of its overflow.
     */
    private void containerIncrease() {
        Node<K, V>[] oldContainer = container;
        container = new Node[oldContainer.length * 2];
        this.size = 0;
        for (Node<K, V> node : oldContainer) {
            if (node != null) {
                this.insert(node.getKey(), node.getValue());
                System.out.println(node.getKey());
            }
        }
    }

    /**
     * Method insert elements of containers.
     *
     * @param key   of element.
     * @param value of element.
     */
    public boolean insert(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        boolean result = false;
        int index = hash(newNode.hashCode()) % container.length;
        if (container.length == size) {
            this.containerIncrease();
        }
        //Методы разрешения коллизий реализовывать не надо.
        if (!Objects.equals(container[index], newNode) & container[index] == null) {
            container[index] = newNode;
            size++;
            mod++;
            result = true;
        }
        return result;
    }

    /**
     * Method get elements by key.
     *
     * @param key of element.
     */
    public V get(K key) {
        V result = null;
        for (Node<K, V> node : container) {
            if (node != null) {
                if (Objects.equals(node.getKey(), key)) {
                    result = node.getValue();
                }
            }
        }
        return result;
    }

    /**
     * Method return size container.
     */
    public int getSize() {
        return size;
    }

    /**
     * Method delete elements by key.
     *
     * @param key of element.
     */
    public boolean delete(K key) {
        boolean result = false;
        for (int i = 0; i < container.length; i++) {
            if (container[i] != null) {
                if (container[i].getKey().equals(key)) {
                    container[i] = null;
                    result = true;
                    mod++;
                    size--;
                }
            }
        }
        return result;
    }
}
