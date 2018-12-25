package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Class SimpleArrayTest.
 *
 * @author dshustrov
 * @version 1
 * @since 04.12.2018
 */
public class SimpleArrayTest {
    private SimpleArray<Integer> arr;
    private Iterator<Integer> iter;

    @Before
    public void setUp() {
        arr = new SimpleArray<>(5);
        arr.add(5);
        arr.add(1);
        arr.add(3);
        arr.add(6);
        arr.add(7);
        iter = arr.iterator();
    }

    @Test
    public void whenAddFiveElementsToArraysThenGetFiveElements() {
        assertThat(arr.get(0), is(5));
        assertThat(arr.get(1), is(1));
        assertThat(arr.get(2), is(3));
        assertThat(arr.get(3), is(6));
        assertThat(arr.get(4), is(7));
    }

    @Test
    public void whenSetOneElementWhenGetNewElement() {
        arr.set(0, 56);
        assertThat(arr.get(0), is(56));
    }

    @Test
    public void whenDeleteOneElementGetNewSequence() {
        arr.remove(0);
        assertThat(arr.get(0), is(1));
        assertThat(arr.get(1), is(3));
        assertThat(arr.get(2), is(6));
        assertThat(arr.get(3), is(7));
        assertThat(arr.get(4), is(7));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(5));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(6));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(7));
    }
}
