package ru.job4j.loop;

/**
 * Class Paint.
 * @author dshustrov
 * @version 1
 * @since 31.10.2018
 */
public class Paint {

    /**
     * Метод рисует правосторонний треугольник.
     *
     * @param height высота пирамиды.
     * @return нарисованная половина пирамида.
     */
    public String rightTrl(int height) {
        // Буфер для результата.
        StringBuilder screen = new StringBuilder();
        // ширина будет равна высоте.
        //int weight = height;
        // внешний цикл двигается по строкам.
        for (int row = 0; row != height; row++) {
            // внутренний цикл определяет положение ячейки в строке.
            for (int column = 0; column != height; column++) {
                // если строка равна ячейки, то рисуем галку.
                // в данном случае строка определяем, сколько галок будет на строке
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод строки.
            screen.append(System.lineSeparator());
        }
        // Получаем результат.
        return screen.toString();
    }

    /**
     * Метод рисует левосторонний треугольник.
     *
     * @param height высота пирамиды.
     * @return нарисованная половина пирамида.
     */
    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();
        //int weight = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != height; column++) {
                if (row >= height - column - 1) {
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
     * Метод рисует рисует пирамиду из символа ^ и пробелов.
     *
     * @param height ширина высота пирамиды.
     * @return нарисованная пирамида.
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    public static void main(String[] args) {
        Paint paint = new Paint();
        System.out.println(paint.pyramid(5));
    }
}
