package ru.job4j.crud.service;

import ru.job4j.crud.model.User;

import java.util.List;
import java.util.Map;

public interface Validate {

    String add(User user);

    String update(User user);

    String delete(int id);

    List<User> find();

    boolean isConformity(User user);

    String findRules(User user);

    String findAll();

    User findUser(String login, String password);

    List<String> getAllCountry();

    List<String> getAllRegionByCountry(String country);

    List<String> getAllCitiesByRegion(String region);
}
