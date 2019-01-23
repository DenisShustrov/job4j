package ru.job4j.producer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class SimpleBlockingQueue.
 *
 * @author dshustrov
 * @version 1
 * @since 24.01.2019
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    /**
     * queue of elements.
     */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    /**
     * size queue.
     */
    private int size;

    /**
     * Constructor default.
     */
    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    /**
     * Method insert to the top of the queue.
     *
     * @param value offer element.
     */
    public synchronized void offer(T value) throws InterruptedException {
        this.queue.offer(value);
        this.notify();
        while (queue.size() > size) {
            this.wait();
        }
    }

    /**
     * Method return size of the queue.
     */
    public synchronized int getsize() {
        return queue.size();
    }

    /**
     * Method remove from end of queue.
     */
    public synchronized T poll() throws InterruptedException {
        T result = null;
        while (queue.isEmpty()) {
            this.wait();
        }
        result = queue.poll();
        this.notify();
        return result;
    }
}
