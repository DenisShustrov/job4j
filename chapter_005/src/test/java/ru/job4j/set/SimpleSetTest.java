package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    private SimpleSet<Integer> simpleSet;

    @Before
    public void beforeTest() {
        simpleSet = new SimpleSet<>();
        simpleSet.add(1);
        simpleSet.add(2);
        simpleSet.add(3);
    }

    @Test
    public void whenAddDuplicateElementsThenSizeDoesNotChange() {
        assertThat(simpleSet.size(), is(3));
        simpleSet.add(3);
        assertThat(simpleSet.size(), is(3));
        simpleSet.add(null);
        assertThat(simpleSet.size(), is(3));

    }

    @Test
    public void hasNextNextSequentialInvocation() {
        Iterator<Integer> it = simpleSet.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));

    }
}
