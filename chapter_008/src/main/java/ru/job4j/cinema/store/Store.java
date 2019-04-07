package ru.job4j.cinema.store;

import ru.job4j.cinema.model.Visitor;

import java.util.Map;

/**
 * Interface Store.
 *
 * @author dshustrov
 * @version 1
 * @since 02.04.2019
 */
public interface Store {

    Map<String, String> getAllHalls();

    boolean checkPlace(String place);

    void addVisitor(Visitor visitor);
}
