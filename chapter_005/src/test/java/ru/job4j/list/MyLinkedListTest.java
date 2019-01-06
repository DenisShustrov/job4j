package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MyLinkedListTest {

    private MyLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));

    }

    @Test(expected = ConcurrentModificationException.class)
    public void thenhasNextNextBeforeAddNewElementThenThrowConcurrentModificationException() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        list.add(25);
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void thenhasNextNextThenThrowNoSuchElementException() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        it.next();
    }
}
