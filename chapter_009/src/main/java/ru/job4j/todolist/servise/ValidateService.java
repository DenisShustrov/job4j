package ru.job4j.todolist.servise;

import ru.job4j.todolist.dao.TodolistDao;
import ru.job4j.todolist.model.Item;

import java.util.List;

public class ValidateService implements Validate {

    private final static Validate VALIDATE_SERVICE = new ValidateService();

    private final TodolistDao todolistDao = new TodolistDao();

    private ValidateService() {

    }

    public static Validate getInstance() {
        return VALIDATE_SERVICE;
    }

    @Override
    public void save(Item item) {
        todolistDao.save(item);
    }

    @Override
    public List<Item> findAll() {
        return todolistDao.findAll();
    }

    @Override
    public void changeStatus(List<String> list) {
        todolistDao.changeStatus(list);
    }

    @Override
    public List<Item> findAllItemWhenDoneTrue() {
        return todolistDao.findAllItemWhenDoneTrue();
    }
}
