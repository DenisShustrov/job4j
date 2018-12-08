package ru.job4j.department;

import java.util.Arrays;

/**
 * Class StringCompar.
 *
 * @author dshustrov
 * @version 1
 * @since 08.12.2018
 */
public class StringCompar {
    /**
     * first первая строка.
     */
    private String first;
    /**
     * two вторая строка.
     */
    private String second;

    /**
     * Конструктор.
     *
     * @param first  первая строка.
     * @param second вторая строка.
     */
    public StringCompar(String first, String second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Метод возвращает true если во второй строке сделано одно переставление символа.
     */
    public boolean twoStringsCompar() {
        boolean result = false;
        if (first.length() == second.length()) {
            char[] firstArr = first.toCharArray();
            char[] secondArr = second.toCharArray();
            int count = 0;
            for (int i = 0; i < firstArr.length; i++) {
                if (firstArr[i] != secondArr[i]) {
                    count++;
                }
            }
            Arrays.sort(firstArr);
            Arrays.sort(secondArr);
            result = (String.valueOf(firstArr).equals(String.valueOf(secondArr)) && count == 2);
        }
        return result;
    }
}
