package ru.job4j.storage.userstorage;

import ru.job4j.storage.model.User;

import java.util.List;

public interface Storage {

    User add(User user);

    List<User> findAllUsers();

}
