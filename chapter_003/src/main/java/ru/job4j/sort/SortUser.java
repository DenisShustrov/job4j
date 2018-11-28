package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class SortUser.
 *
 * @author dshustrov
 * @version 1
 * @since 29.11.2018
 */
public class SortUser {
    /**
     * Метод сортировки.
     *
     * @param list список на основе List.
     */
    public Set<User> sort(List<User> list) {
       return new TreeSet<>(list);
    }
}