package ru.job4j.convert;

import org.junit.Test;
import ru.job4j.сonvert.User;
import ru.job4j.сonvert.UserConvert;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author dshustrov
 * @version $Id$
 * @since 0.1
 */
public class UserConvertTest {
    @Test
    public void whenListUserThenMapIntegerUserGetId() {
        UserConvert userConvert = new UserConvert();
//        User[] us = {new User(1, "Ivan", "Moscow"),
//                new User(2, "Denis", "Vyazma")
//        };
//        List<User> list = Arrays.asList(us);
        List<User> list = List.of(
                new User(1, "Ivan", "Moscow"),
                new User(2, "Denis", "Vyazma"));
        Map<Integer, User> map = userConvert.process(list);
        Iterator<Map.Entry<Integer, User>> iter = map.entrySet().iterator();
        Map.Entry<Integer, User> entry = iter.next();
        Integer result = entry.getKey();
        assertThat(result, is(1));
    }

    @Test
    public void whenListUserThenMapIntegerUserGetName() {
        UserConvert userConvert = new UserConvert();
//        User[] us = {new User(1, "Ivan", "Moscow"),
//                new User(2, "Denis", "Vyazma")
//        };
//        List<User> list = Arrays.asList(us);
        List<User> list = List.of(
                new User(1, "Ivan", "Moscow"),
                new User(2, "Denis", "Vyazma"));
        Map<Integer, User> map = userConvert.process(list);
        Iterator<Map.Entry<Integer, User>> iter = map.entrySet().iterator();
        Map.Entry<Integer, User> entry = iter.next();
        String result = entry.getValue().getName();
        assertThat(result, is("Ivan"));
    }
}
