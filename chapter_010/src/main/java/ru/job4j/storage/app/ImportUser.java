package ru.job4j.storage.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.storage.model.User;
import ru.job4j.storage.userstorage.Storage;

@Component
public class ImportUser {

    private Storage storage;

    @Autowired
    public ImportUser(final Storage storage) {
        this.storage = storage;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public User addUser(User user) {
        storage.add(user);
        return user;
    }
}
