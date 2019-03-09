package ru.job4j.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class MemoryStore.
 *
 * @author dshustrov
 * @version 1
 * @since 06.03.2019
 */
public class MemoryStore implements Store {

    private List<User> storage = new CopyOnWriteArrayList<>();

    private static MemoryStore memoryStore;

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        if (null == memoryStore) {
            memoryStore = new MemoryStore();
        }
        return memoryStore;
    }

    @Override
    public User add(User user) {
        User result = null;
        if (!storage.contains(user)) {
            storage.add(user);
            result = user;
        }
        return result;
    }

    @Override
    public boolean replace(User user) {
        boolean result = false;
        for (int i = 0; i < storage.size(); i++) {
            if (user.getId() == storage.get(i).getId()) {
                storage.set(i, user);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        for (int i = 0; i < storage.size(); i++) {
            if (id == storage.get(i).getId()) {
                storage.remove(i);
                result = true;
            }
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return storage;
    }

    @Override
    public ArrayList<User> findByName(String name) {
        ArrayList<User> temp = new ArrayList<>();
        for (User us : storage) {
            if (us.getName().equals(name)) {
                temp.add(us);
            }
        }
        return temp;
    }

    @Override
    public User findById(int id) {
        User result = null;
        for (User us : storage) {
            if (us.getId() == id) {
                result = us;
            }
        }
        return result;
    }
}
