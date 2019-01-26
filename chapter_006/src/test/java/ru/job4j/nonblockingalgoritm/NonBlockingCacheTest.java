package ru.job4j.nonblockingalgoritm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NonBlockingCacheTest {

    private NonBlockingCache blockingCache;

    @Before
    public void beforeTest() {
        blockingCache = new NonBlockingCache();
        blockingCache.add(new Base(1));
        blockingCache.add(new Base(2));
        blockingCache.add(new Base(3));
    }

    @Test
    public void whenUpdateModeWhereIdEqualse1ThanVersionHasBecomeEquals1() {
        blockingCache.update(new Base(1));
        assertThat(blockingCache.getVersion(1), is(1));
    }

    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<OptimisticException> ex = new AtomicReference<>();
        Thread thread1 = new Thread(
                () -> {
                    try {
                        blockingCache.update(new Base(1));
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    try {
                        blockingCache.update(new Base(1));
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Assert.assertThat(ex.get().getMessage(), is("This is OptimisticException"));
    }
}

