package ru.job4j.crud.store;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface Store.
 *
 * @author dshustrov
 * @version 1
 * @since 06.03.2019
 */
public interface Store<User> {
    User add(User user);

    boolean replace(User user);

    boolean delete(int id);

    List<User> findAll();

    ArrayList<User> findByName(String key);

    User findById(int id);
}
