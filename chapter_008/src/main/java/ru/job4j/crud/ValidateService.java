package ru.job4j.crud;

import java.util.List;

/**
 * Class ValidateService.
 *
 * @author dshustrov
 * @version 1
 * @since 06.03.2019
 */
public class ValidateService {

    private final Store logic = MemoryStore.getInstance();

    private static ValidateService validateService;

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        if (null == validateService) {
            validateService = new ValidateService();
        }
        return validateService;
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
        User addUser = logic.add(user);
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
}
