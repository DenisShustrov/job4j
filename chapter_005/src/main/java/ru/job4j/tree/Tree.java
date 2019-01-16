package ru.job4j.tree;

import java.util.*;

/**
 * Class Tree.
 *
 * @author dshustrov
 * @version 1
 * @since 14.01.2019
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * size tree.
     */
    private int size;
    /**
     * root element in the tree.
     */
    private Node<E> root;

    /**
     * Constructor.
     *
     * @param value initialize the value of the tree.
     */
    public Tree(E value) {
        root = new Node<E>(value);
        size++;
    }

    /**
     * Method add elements in the tree.
     *
     * @param parent element.
     * @param child  element.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (root.eqValue(parent)) {
            if (!findBy(child).isPresent()) {
                root.add(new Node<>(child));
                size++;
                result = true;
            }
        } else if (findBy(parent).isPresent()) {
            findBy(parent).get().add(new Node<>(child));
            size++;
            result = true;
        }
        return result;
    }

    /**
     * Method find element in the tree.
     *
     * @param value element.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Method defines a binary tree.
     */
    public boolean isBinary() { // без итератора
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }

//        Iterator<E> iter = iterator();
//        while (iter.hasNext()) {
//            if (findBy(iter.next()).get().leaves().size() > 2) {
//                result = false;
//                break;
//            }
//        }
        return result;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private Queue<Node<E>> nodes = new LinkedList<>(Arrays.asList(root));

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
                Node<E> res = nodes.poll();
                for (Node<E> node : res.leaves()) {
                    nodes.offer(node);
                }
                count++;
                return res.getValue();
            }
        };
    }

}
