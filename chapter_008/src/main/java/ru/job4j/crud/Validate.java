package ru.job4j.crud;

import java.util.List;

public interface Validate {

    String add(User user);

    String update(User user);

    String delete(int id);

    List<User> find();

    boolean isConformity(String login, String password);

    String findRules(String login, String password);

    String findAll();


}
