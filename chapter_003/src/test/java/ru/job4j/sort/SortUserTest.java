package ru.job4j.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author dshustrov
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {
    @Test
    public void whenListThenSortedSet() {
        SortUser user = new SortUser();
        User[] us = {new User("Denis", 35),
                new User("Pavel", 32)};
        List<User> list = Arrays.asList(us);
        Set<User> result = user.sort(list);
        assertThat(result.iterator().next().getName(), is("Pavel"));

    }
}
