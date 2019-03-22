package ru.job4j.crud.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UsersRules {

    private List<String> list = new CopyOnWriteArrayList<>();

    public UsersRules() {
        list.add("ADMINISTRATOR");
        list.add("USER");
        list.add("GUEST");
    }

    public List<String> getList() {
        return list;
    }
}
