package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Class Paint.
 * @author dshustrov
 * @version 1
 * @since 31.10.2018
 */
public class Paint {

    /**
     * Метод для сокращения кода.
     *
     * @param height высота пирамиды.
     * @return нарисованная половина пирамида.
     */
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    /**
     * Метод рисует правосторонний треугольник.
     *
     * @param height высота пирамиды.
     * @return нарисованная половина пирамида.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Метод рисует левосторонний треугольник.
     *
     * @param height высота пирамиды.
     * @return нарисованная половина пирамида.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }


    /**
     * Метод рисует рисует пирамиду из символа ^ и пробелов.
     *
     * @param height ширина высота пирамиды.
     * @return нарисованная пирамида.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }
}