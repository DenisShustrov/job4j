package ru.job4j.poll;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThreadPoolTest {

    private ThreadPool tp;

    @Before
    public void beforeTest() {
        tp = new ThreadPool();
        tp.work(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Doing something 1");
            }
        });

        tp.work(() -> {
            for (int i = 0; i < 150; i++) {
                System.out.println("Doing something 2");
            }
        });
    }

    @Test
    public void whenRunThreadThatPoolWorks() throws InterruptedException {
        tp.executor();
        tp.shutdown();
        assertThat(tp.getSizeTasks(), is(0));
    }
}
