package ru.job4j.todolist.servise;

import ru.job4j.todolist.model.Item;

import java.util.List;

public interface Validate {
    void save(Item item);

    List<Item> findAll();

    void changeStatus(List<String> list);

    List<Item> findAllItemWhenDoneTrue();
}
