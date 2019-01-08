package ru.job4j.list;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class CyclicalityTest {

    private Cyclicality cyclicality;

    @Test
    public void thanHasCycleWhenTrue() {
        cyclicality = new Cyclicality();
        Cyclicality.Node<Integer> first = new Cyclicality.Node<>(1);
        Cyclicality.Node<Integer> second = new Cyclicality.Node<>(2);
        Cyclicality.Node<Integer> third = new Cyclicality.Node<>(3);
        Cyclicality.Node<Integer> four = new Cyclicality.Node<>(4);
        first.next = second;
        second.next = third;
        third.next = four;
        four.next = first;
        assertThat(cyclicality.hasCycle(first), is(true));
    }

    @Test
    public void thanHasCycleWhenfalse() {
        cyclicality = new Cyclicality();
        Cyclicality.Node<Integer> first = new Cyclicality.Node<>(1);
        Cyclicality.Node<Integer> second = new Cyclicality.Node<>(2);
        Cyclicality.Node<Integer> third = new Cyclicality.Node<>(3);
        Cyclicality.Node<Integer> four = new Cyclicality.Node<>(4);
        first.next = second;
        second.next = third;
        third.next = four;
        four.next = null;
        assertThat(cyclicality.hasCycle(first), is(false));
    }
}
