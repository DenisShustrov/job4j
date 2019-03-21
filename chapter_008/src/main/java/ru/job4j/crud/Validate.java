package ru.job4j.crud;

import java.util.List;

public interface Validate {

    String add(User user);

    String update(User user);

    String delete(int id);

    List<User> find();

    boolean isConformity(User user);

    String findRules(User user);

    String findAll();

    User findUser(String login, String password);


}
