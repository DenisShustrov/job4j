package ru.job4j.synchronizy;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MultithreadedCollectionTest {

    private MultithreadedCollection<Integer> mc;

    @Before
    public void beforeTest() {
        mc = new MultithreadedCollection<>();
        mc.add(10);
        mc.add(20);
        mc.add(30);
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        Iterator<Integer> it = mc.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(10));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(20));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(30));
        assertThat(it.hasNext(), is(false));

    }

    private class ThreadMC extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                mc.add(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void whenExecute2ThreadThenCorrectSizeMC() throws InterruptedException {
        ThreadMC threadMC1 = new ThreadMC();
        ThreadMC threadMC2 = new ThreadMC();
        threadMC1.start();
        threadMC2.start();
        threadMC1.join();
        threadMC2.join();
        assertThat(mc.getSizeMC(), Is.is(13));
    }
}
