package ru.job4j.crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateStub implements Validate {

    private final Map<Integer, User> store = new HashMap<>();

    @Override
    public String add(User user) {
        this.store.put(user.getId(), user);
        return "Add";
    }

    @Override
    public String update(User user) {
       this.store.replace(user.getId(), user);
        return "Update";
    }

    @Override
    public String delete(int id) {
        this.store.remove(id);
        return "Del";
    }

    @Override
    public List<User> find() {
        return new ArrayList<>(this.store.values());
    }

    @Override
    public boolean isConformity(String login, String password) {
        return false;
    }

    @Override
    public String findRules(String login, String password) {
        return null;
    }

    @Override
    public String findAll() {
        return null;
    }


}
