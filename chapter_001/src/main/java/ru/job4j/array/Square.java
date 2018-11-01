package ru.job4j.array;

/**
 * Class Square.
 * @author dshustrov
 * @version 1
 * @since 01.11.2018
 */
public class Square {

    /**
     * Метод заполняет массив числами возведенными в квадрат.
     *
     * @param bound длина массива.
     * @return заполненный массив.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        rst[0] = 1;
        int i, j;
        for (i = 1, j = 2; i < rst.length; i++, j++) {
            rst[i] = j * j;
        }
        return rst;
    }
}

