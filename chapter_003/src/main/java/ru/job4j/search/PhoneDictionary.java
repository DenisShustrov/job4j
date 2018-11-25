package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Class PhoneDictionary.
 *
 * @author dshustrov
 * @version 1
 * @since 21.11.2018
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     *
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person p : persons) {
            if (p.getName().equals(key) || p.getSurname().equals(key) || p.getPhone().equals(key) || p.getAddress().equals(key)) {
                result.add(p);
            }
        }
        return result;
    }
}