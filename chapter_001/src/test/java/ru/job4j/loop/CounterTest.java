package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author dshustrov (denisshustroff@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CounterTest {
    /**
     * Test add.
     */
    @Test
    public void whenStartNumberAndfinishNumberThenSumEven() {
        Counter counter = new Counter();
        int result = counter.add(1, 10);
        int expected = 5;
        assertThat(result, is(expected));
    }
}
