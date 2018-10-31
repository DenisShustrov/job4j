package ru.job4j.loop;

/**
 * Class Board.
 * @author dshustrov
 * @version 1
 * @since 31.10.2018
 */
public class Board {
    /**
     * Метод рисует шахматную доску из символов x и пробелов.
     *
     * @param width ширина доски.
     * @param height высота доски.
     * @return нарисованная шахматная доска.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((j + i) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}
