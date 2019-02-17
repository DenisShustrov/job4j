package ru.job4j.testing;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

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
        Semaphore one = new Semaphore(0);
        Semaphore two = new Semaphore(1);
        int numberIterations = 5;
        Thread oneT = new Thread(() -> {
            for (int i = 0; i < numberIterations; i++) {
                try {
                    two.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 10; j++) {
                    ss.stringAppEnd(1);
                }
                one.release();
            }

        });

        Thread twoT = new Thread(() -> {
            for (int i = 0; i < numberIterations; i++) {
                try {
                    one.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 10; j++) {
                    ss.stringAppEnd(2);
                }
                two.release();
            }
        });
        oneT.start();
        twoT.start();
        oneT.join();
        twoT.join();
        String result = ss.getString();
        assertThat(result, is("Start string:1111111111222222222211111111112222222222111111111122222222221111111111222222222211111111112222222222"));
    }
}
