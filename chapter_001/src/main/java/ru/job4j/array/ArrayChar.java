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
        for (int i = 0; i < value.length; i++) {
            if (value[i] == data[i] & value[i] == data[i]) {
                result = true;
            }
        }
        return result;
    }
}

