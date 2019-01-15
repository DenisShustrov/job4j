package ru.job4j.map;

import java.util.*;

public class MyHashMap<K, V> implements Iterable<V> {
    /**
     * size container.
     */
    private int size = 0;

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

            private int expectedModCount = size;

            private int count;

            private Node<K, V>[] containerNotNull() {
                List<Node<K, V>> list = new ArrayList<>();
                for (Node<K, V> node : container) {
                    if (node != null) {
                        list.add(node);
                    }
                }
                return list.toArray(new Node[size]);
            }

            @Override
            public boolean hasNext() {
                return size > this.count;
            }

            @Override
            public V next() {
                if (expectedModCount != size) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return containerNotNull()[count++].getValue();
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
                    size--;
                }
            }
        }
        return result;
    }
}
