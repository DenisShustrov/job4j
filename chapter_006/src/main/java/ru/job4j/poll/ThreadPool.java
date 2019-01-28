package ru.job4j.poll;

import ru.job4j.producer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Class ThreadPool.
 *
 * @author dshustrov
 * @version 1
 * @since 27.01.2019
 */
public class ThreadPool {
    /**
     * size of poll.
     */
    int size = Runtime.getRuntime().availableProcessors();
    /**
     * threads poll.
     */
    private final List<Thread> threads = new LinkedList<Thread>();
    /**
     * tasks to perform.
     */
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(5);

    /**
     * Method adds tasks.
     *
     * @param job to be done.
     */
    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method add thread in the poll.
     */
    public void executor() {
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
                try {
                    tasks.poll().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
    }

    /**
     * Method starts threads.
     */
    public void shutdown() throws InterruptedException {

        for (Thread thread : threads) {
            thread.start();
            Thread.sleep(5000);
        }
        System.out.println("Количество нитей " + threads.size());

    }

    public int getSizeTasks() {
        return tasks.getsize();
    }

}

