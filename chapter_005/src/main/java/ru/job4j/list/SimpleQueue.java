package ru.job4j.list;

/**
 * Class SimpleQueue<E>.
 *
 * @author dshustrov
 * @version 1
 * @since 08.01.2019
 */
public class SimpleQueue<E> {
    /**
     * first stack.
     */
    private SimpleStack<E> in;
    /**
     * second stack.
     */
    private SimpleStack<E> out;
    /**
     * size stack.
     */
    private int size;

    /**
     * Constructor initializing stacks.
     */
    public SimpleQueue() {
        this.in = new SimpleStack<>();
        this.out = new SimpleStack<>();
    }

    /**
     * Method add elements in the first stack.
     *
     * @param value add of element.
     */
    public void push(E value) {
        this.in.push(value);
        size++;
    }

    /**
     * Method delete and add all elements of the first stack in the
     * second stack. After return and delete element of the second stack.
     */
    public E poll() {
        if (this.out.getSize() == 0) {
            while (this.in.getSize() != 0) {
                this.out.push(in.poll());
            }
        }
        E result = null;
        if (this.out.getSize() != 0) {
            result = this.out.poll();
            size--;
        }
        return result;
    }
}
