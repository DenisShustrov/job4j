package ru.job4j.testing;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringStorageTest {

    private StringStorage ss;

    @Before
    public void beforeTest() {
        ss = new StringStorage("Start string:");
    }

    @Test
    public void whenStringAppEndNumberThenStringWithNumber() {
        String str = ss.stringAppEnd(45);
        assertThat(str, is("Start string:45"));
    }

    @Test
    public void whenTwoStreamsAddNumbersInTheStringThenNumbersAddedEvenly() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        int numberIterations = 5;
        for (int i = 0; i < numberIterations; i++) {
            Thread one = new Thread(() -> {
                lock.lock();
                for (int j = 0; j < 10; j++) {
                    ss.stringAppEnd(1);
                }
                lock.unlock();
            });
            Thread two = new Thread(() -> {
                lock.lock();
                for (int j = 0; j < 10; j++) {
                    ss.stringAppEnd(2);
                }
                lock.unlock();

            });
            one.start();
            one.join();
            two.start();
            two.join();
        }
        String result = ss.getString();
        assertThat(result, is("Start string:1111111111222222222211111111112222222222111111111122222222221111111111222222222211111111112222222222"));
    }
}
