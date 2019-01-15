package ru.job4j.map;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class MyHashMapTest {

    private MyHashMap<String, Integer> map;

    @Before
    public void beforeTest() {
        map = new MyHashMap<>();
        map.insert("1", 1);
        map.insert("2", 2);
        map.insert("3", 3);
        map.insert("4", 4);
        map.insert("5", 5);
        map.insert("6", 6);
    }

    @Test
    public void whenAddSixElementsThatSizeSix() {
        assertThat(map.getSize(), is(6));
    }

    @Test
    public void whenAddSameElementThatSizeNotChange() {
        map.insert("4", 15);
        assertThat(map.getSize(), is(6));
    }

    @Test
    public void whenGetElementThatNotNull() {
        assertThat(map.get("4"), is(4));
    }

    @Test
    public void whenDeleteElementThatGetNull() {
        map.delete("4");
        assertNull(map.get("4"));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        Iterator<Integer> it = map.iterator();
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(6));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(4));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(5));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(1));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(3));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(2));
        assertThat(it.hasNext(), Matchers.is(false));

    }
}
