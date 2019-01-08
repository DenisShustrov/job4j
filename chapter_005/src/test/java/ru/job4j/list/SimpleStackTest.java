package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    private SimpleStack<Integer> st;

    @Before
    public void beforeTest() {
        st = new SimpleStack<>();
        st.push(1);
        st.push(2);
        st.push(3);
    }

    @Test
    public void whenPollThreeTimesThenDeleteFreeElementsLifo() {
        assertThat(st.poll(), is(3));
        assertThat(st.poll(), is(2));
        assertThat(st.poll(), is(1));
    }
}
