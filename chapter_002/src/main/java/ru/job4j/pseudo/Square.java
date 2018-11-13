package ru.job4j.pseudo;

/**
 * Class Square.
 *
 * @author dshustrov
 * @version 1
 * @since 13.11.2018
 */
public class Square implements Shape {
    /**
     * Метод формирует строку из символов в виде квадрата.
     * @return строка в виде квадрата.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("++++");
        pic.append("+     +");
        pic.append("+     +");
        pic.append("++++");
        return pic.toString();
    }
}
