package ru.job4j.sort;

import java.util.*;

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

    /**
     * Метод сортировки по длине имени.
     *
     * @param list список на основе List.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.getName().toCharArray().length - o1.getName().toCharArray().length;
            }
        });
        return list;
    }

    /**
     * Метод сортировки по имени в лексикографическом порядке, потом по возрасту.
     *
     * @param list список на основе List.
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int compare = o1.getName().compareTo(o2.getName());
                return compare == 0 ? o1.getAge() - o2.getAge() : compare;
            }
        });
        return list;
    }
}