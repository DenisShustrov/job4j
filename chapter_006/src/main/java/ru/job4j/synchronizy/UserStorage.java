package ru.job4j.synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.map.MyHashMap;

/**
 * Class UserStorage.
 *
 * @author dshustrov
 * @version 1
 * @since 21.01.2019
 */
@ThreadSafe
public class UserStorage {
    /**
     * storage users.
     */
    @GuardedBy("this")
    private MyHashMap<Integer, User> storage = new MyHashMap<>();

    /**
     * default constructor.
     */
    public UserStorage() {
    }

    /**
     * Method add user.
     *
     * @param user add.
     */
    public synchronized boolean add(User user) {
        return storage.insert(user.getId(), user);
    }

    /**
     * Method delete user.
     *
     * @param user delete.
     */
    public synchronized boolean delete(User user) {
        return storage.delete(user.getId());
    }

    /**
     * Method update user.
     *
     * @param user update.
     */
    public synchronized boolean update(User user) {
        boolean result = false;
        if (storage.get(user.getId()) != null) {
            storage.insert(user.getId(), user);
        }
        return result;
    }

    /**
     * Method transfer amount from one id to another.
     *
     * @param fromId id user.
     * @param toId   id user.
     * @param amount transfer funds.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (storage.get(fromId) != null && storage.get(toId) != null
                && storage.get(fromId).getAmount() >= storage.get(toId).getAmount()) {
            User from = storage.get(fromId);
            User to = storage.get(toId);
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
            result = true;
        }
        return result;
    }

    /**
     * Method get user.
     *
     * @param id user.
     */
    public synchronized User get(int id) {
        return storage.get(id);
    }

    /**
     * Method return size storage.
     */
    public synchronized int getSizeStorage() {
        return storage.getSize();
    }
}
