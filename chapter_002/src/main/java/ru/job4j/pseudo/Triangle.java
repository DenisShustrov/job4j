package ru.job4j.pseudo;

/**
 * Class Triangle.
 *
 * @author dshustrov
 * @version 1
 * @since 13.11.2018
 */
public class Triangle implements Shape {
    /**
     * Метод формирует строку из символов в виде треугольника.
     *
     * @return строка в виде треугольника.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("   ^   ");
        pic.append("  ^^^  ");
        pic.append(" ^^^^^ ");
        pic.append("^^^^^^^");
        return pic.toString();
    }
}
