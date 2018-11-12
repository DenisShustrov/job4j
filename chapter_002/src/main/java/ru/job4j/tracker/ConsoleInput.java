package ru.job4j.tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class ConsoleInput.
 *
 * @author dshustrov.
 * @version 1
 * @since 11.11.2018
 */
public class ConsoleInput implements Input {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Метод для ввода данных с консоли пользователями.
     *
     * @param question строка запроса ввести данные.
     */
    public String ask(String question) throws IOException {
        System.out.println(question);
        return reader.readLine();
    }
}
