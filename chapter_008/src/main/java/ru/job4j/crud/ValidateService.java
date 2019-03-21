package ru.job4j.crud;

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
        if (logic.findAll().size() == 0) {
            result = "No users in the storage!";
        } else {
            result = "All users: " + logic.findAll().toString();
        }
        return result;
    }

    public String add(User user) {
        String result;
        User addUser = (User) logic.add(user);
        if (addUser != null) {
            result = "Added user with name :" + addUser.getName();
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

    public boolean isConformity(String login, String password) {
        boolean result = false;
        for (User us : find()) {
            if (us.getLogin().equals(login) && us.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public String findRules(String login, String password) {
        String result = "";
        for (User us : find()) {
            if (us.getLogin().equals(login) && us.getPassword().equals(password)) {
                result = us.getRules();
            }
        }
        return result;
    }
}
