package ru.job4j.tracker;

/**
 * Class MenuOutException.
 *
 * @author dshustrov
 * @version 1
 * @since 17.11.2018
 */
public class MenuOutException extends RuntimeException {
    public MenuOutException(String str) {
        super(str);
    }
}
