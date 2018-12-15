package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        //List<Person> result;
//        for (Person p : persons) {
//            if (p.getName().equals(key) || p.getSurname().equals(key) || p.getPhone().equals(key) || p.getAddress().equals(key)) {
//                result.add(p);
//            }
//        }
        var result = persons.stream().filter(f -> f.getName().equals(key) || f.getSurname().equals(key)
                || f.getPhone().equals(key) || f.getAddress().equals(key)).collect(Collectors.toList());
        return result;
    }
}
