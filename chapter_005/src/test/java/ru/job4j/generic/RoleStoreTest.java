package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SimpleArrayTest.
 *
 * @author dshustrov
 * @version 1
 * @since 04.12.2018
 */
public class RoleStoreTest {
    RoleStore it;

    @Before
    public void setUp() {
        it = new RoleStore(new SimpleArray(5));
        it.add(new Role("123"));
        it.add(new Role("124"));
        it.add(new Role("125"));
        it.add(new Role("126"));
        it.add(new Role("127"));

    }

    @Test
    public void whenReplaseElementThenGetNewElement() {
        it.replace("125", new Role("521"));
        String result = it.findById("521").getId();
        assertThat(result, is("521"));
    }

    @Test
    public void whenDeleteElementThenUpdateArray() {
        it.delete("124");
        Base result = it.findById("124");
        assertThat(result, is(nullValue()));
    }
}
