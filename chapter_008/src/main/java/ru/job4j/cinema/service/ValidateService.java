package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Visitor;
import ru.job4j.cinema.store.HallsStore;
import ru.job4j.cinema.store.Store;

import java.util.Map;

/**
 * Class ValidateService.
 *
 * @author dshustrov
 * @version 1
 * @since 01.04.2019
 */
public class ValidateService {

    private final Store logic = HallsStore.getInstance();

    private final static ValidateService VALIDATE_SERVICE = new ValidateService();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return VALIDATE_SERVICE;
    }

    public Map<String, String> getAllHalls() {
        return logic.getAllHalls();
    }

    public void addVisitor(Visitor visitor) {
        logic.addVisitor(visitor);
    }
}
