package ru.job4j.tracker;

import java.io.IOException;

/**
 * Interface Input.
 * @author dshustrov
 * @version 1
 * @since 14.11.2018
 */
public interface UserAction {
    /**
     * Метод возвращает ключ опции.
     * @return ключ
     */
    int key();
    /**
     * Основной метод.
     * @param input объект типа Input
     * @param tracker объект типа ITracker
     */
    void execute(Input input, ITracker tracker) throws IOException;
    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return Строка меню
     */
    String info();
}
