package ru.job4j.storage.userstorage;

import org.springframework.stereotype.Component;
import ru.job4j.storage.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryStorage implements Storage {

    private List<User> users = new ArrayList<>();

    @Override
    public User add(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }
}
