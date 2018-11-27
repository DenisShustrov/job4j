package ru.job4j.сonvert;

import java.util.HashMap;
import java.util.List;

/**
 * Class UserConvert.
 *
 * @author dshustrov
 * @version 1
 * @since 27.11.2018
 */
public class UserConvert {
    /**
     * Метод принимает в себя список пользователей
     * и конвертирует его в Map с ключом Integer id
     * и соответствующим ему User.
     *
     * @param list колллекция на основе List.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> user = new HashMap<>();
        for (User iter : list) {
            user.put(iter.getId(), iter);
        }
        return user;
    }
}
