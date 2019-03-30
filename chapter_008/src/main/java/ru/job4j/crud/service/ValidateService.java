package ru.job4j.crud.service;

import ru.job4j.crud.store.DbStore;
import ru.job4j.crud.store.Store;
import ru.job4j.crud.model.User;

import java.util.List;

/**
 * Class ValidateService.
 *
 * @author dshustrov
 * @version 1
 * @since 06.03.2019
 */
public class ValidateService implements Validate {

    private final Store logic = DbStore.getInstance();

    private final static Validate VALIDATE_SERVICE = new ValidateService();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return VALIDATE_SERVICE;
    }

    public String findAll() {
        String result;
        List listUser = logic.findAll();
        if (listUser.size() == 0) {
            result = "No users in the storage!";
        } else {
            result = "All users: " + listUser.toString();
        }
        return result;
    }

    public String add(User user) {
        String result;
        if (logic.checkAddUser(user.getLogin(), user.getPassword())) {
            logic.add(user);
            result = "Added user with name :" + user.getName();
        } else {
            result = "User with such id is already a subscription!";
        }
        return result;
    }

    public String update(User user) {
        String result;
        if (logic.replace(user)) {
            result = "User replaced";
        } else {
            result = "User not replaced";
        }
        return result;
    }

    public String delete(int id) {
        String result;
        if (logic.delete(id)) {
            result = "User deleted";
        } else {
            result = "User not deleted";
        }
        return result;
    }

    public List<User> find() {
        return logic.findAll();
    }

    public boolean isConformity(User user) {
        return logic.isConformity(user);
    }

    public String findRules(User user) {
        String result = null;
        if (!isConformity(user)) {
            result = user.getRules();
        }
        return result;
    }

    public User findUser(String login, String password) {
        return (User) logic.findUser(login, password);
    }

    public List<String> getAllCountry() {
        return logic.getAllCountry();
    }

    public List<String> getAllRegionByCountry(String country) {
        return logic.getAllRegionByCountry(country);
    }

    public List<String> getAllCitiesByRegion(String region) {
        return logic.getAllCitiesByRegion(region);
    }

}
