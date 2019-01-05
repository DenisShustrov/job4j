package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicContainerTest {

    private DynamicContainer<Integer> container;

    @Before
    public void beforeTest() {
        container = new DynamicContainer<>(3);
        container.add(1);
        container.add(2);
        container.add(3);
    }

    @Test
    public void whenAddFourElementThenContainerFullAndIncrease() {
        container.add(4);
        assertThat(container.size(), is(4));

    }

    @Test
    public void whenGetElementTreeIndexThenNumberTen() {
        container.add(10);
        assertThat(container.get(3), is(10));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        Iterator<Integer> it = container.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));

    }

    @Test(expected = ConcurrentModificationException.class)
    public void thenhasNextNextBeforeAddNewElementThenThrowConcurrentModificationException() {
        Iterator<Integer> it = container.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        container.add(25);
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void thenhasNextNextThenThrowNoSuchElementException() {
        Iterator<Integer> it = container.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        it.next();
    }

}
