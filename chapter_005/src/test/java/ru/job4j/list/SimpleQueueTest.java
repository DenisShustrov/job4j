package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {

    private SimpleQueue<Integer> sq;

    @Before
    public void beforeTest() {
        sq = new SimpleQueue<>();
        sq.push(1);
        sq.push(2);
        sq.push(3);
    }

    @Test
    public void whenPollThreeTimesThenDeleteFreeElementsFifo() {
        assertThat(sq.poll(), is(1));
        assertThat(sq.poll(), is(2));
        assertThat(sq.poll(), is(3));
    }
}
