package ru.job4j.tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
    /**
     * Метод для ввода данных с консоли пользователями.
     *
     * @param question строка запроса ввести данные.
     * @param range массив ключей.
     */
    public int ask(String question, List<Integer> range) throws MenuOutException, IOException {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int x : range) {
            if (x == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
           throw new MenuOutException("Out of menu range");
        }

    }
}
