package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Interface ITracker.
 *
 * @author dshustrov
 * @version 1
 * @since 17.02.2019
 */
public interface ITracker {
    Item add(Item item);

    boolean replace(String id, Item item);

    boolean delete(String id);

    ArrayList<Item> findAll();

    ArrayList<Item> findByName(String key);

    Item findById(String id);
}
