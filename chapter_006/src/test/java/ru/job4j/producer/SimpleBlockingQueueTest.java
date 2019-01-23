package ru.job4j.producer;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {

    @Test
    public void whenProducerConsumerStartThenSizeQueue0() throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(5);
        Thread one = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    sbq.offer(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread two = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    sbq.poll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        one.start();
        two.start();
        one.join();
        two.join();
        assertThat(sbq.getsize(), is(0));
    }
}
