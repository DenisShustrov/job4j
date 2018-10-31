package ru.job4j.loop;

/**
 * Class Paint.
 * @author dshustrov
 * @version 1
 * @since 31.10.2018
 */
public class Paint {
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
