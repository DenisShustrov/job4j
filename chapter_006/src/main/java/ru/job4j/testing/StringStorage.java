package ru.job4j.testing;

/**
 * Class StringStorage.
 *
 * @author dshustrov
 * @version 1
 * @since 22.01.2019
 */
public class StringStorage {
    /**
     * str variable string.
     */
    private StringBuilder str;

    /**
     * Constructor.
     *
     * @param str initial state of the string.
     */
    public StringStorage(String str) {
        this.str = new StringBuilder(str);
    }

    /**
     * Method return current state of the string.
     */
    public String getString() {
        return this.str.toString();
    }

    /**
     * Method add numbers in the end string.
     *
     * @param number add of number.
     */
    public String stringAppEnd(int number) {
        return this.str.append(number).toString();
    }
}

