package ru.job4j.array;

/**
 * Class MatrixCheck.
 * @author dshustrov
 * @version 1
 * @since 03.11.2018
 */
public class MatrixCheck {

    /**
     * Метод проверяет, что все элементы по диагоналям равны true или false.
     *
     * @param data массив типа boolean.
     * @return true или false.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[0][0] != data[i + 1][i + 1]) {
                 result = false;
                 break;
            }
            if (data[data.length - 1][0] != data[data.length - i - 2][i + 1]) {
                result = false;
                break;
            }
        }
        return result;
    }
}

