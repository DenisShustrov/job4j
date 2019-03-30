package ru.job4j.crud.store;

import ru.job4j.crud.model.User;

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
public final class MemoryStore implements Store<User> {

    private List<User> storage = new CopyOnWriteArrayList<>();

    private final static MemoryStore MEMORY_STORE = new MemoryStore();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return MEMORY_STORE;
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
                break;
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
    public boolean checkAddUser(String login, String password) {
        return false;
    }

    @Override
    public boolean isConformity(User user) {
        return false;
    }

    @Override
    public List<String> getAllCountry() {
        return null;
    }

    @Override
    public List<String> getAllRegionByCountry(String country) {
        return null;
    }

    @Override
    public List<String> getAllCitiesByRegion(String region) {
        return null;
    }

    @Override
    public User findUser(String login, String password) {
        User result = null;
        for (User us : storage) {
            if (us.getLogin().equals(login) & us.getPassword().equals(password)) {
                result = us;
            }
        }
        return result;
    }
}
