package ru.job4j.array;

/**
 * Class Matrix.
 * @author dshustrov
 * @version 1
 * @since 03.11.2018
 */
public class Matrix {

    /**
     * Метод создает таблицу умножения на основе двухмерного массива.
     *
     * @param size размер таблицы.
     * @return двухмерный массив.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
