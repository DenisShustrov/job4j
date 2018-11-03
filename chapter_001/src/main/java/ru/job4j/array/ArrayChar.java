package ru.job4j.array;

/**
 * Class ArrayChar.
 * @author dshustrov
 * @version 1
 * @since 03.11.2018
 */
public class ArrayChar {

    /**
     * символный массив.
     */
    private char[] data;

    /**
     * Конструктор.
     * @param line строка.
     */
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинается с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = false;
        char[] value = prefix.toCharArray();
        if (value[0] == data[0] & value[1] == data[1]) {
            result = true;
        }
        return result;
    }
}

