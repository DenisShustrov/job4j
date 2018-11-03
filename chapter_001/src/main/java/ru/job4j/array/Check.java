package ru.job4j.array;

/**
 * Class Check.
 * @author dshustrov
 * @version 1
 * @since 01.11.2018
 */
public class Check {

    /**
     * Метод  проверияет, что все элементы в массиве являются true или false..
     *
     * @param data массив чисел.
     * @return перевернутый массив.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] != data[i + 1]) {
                result = false;
                break;
            }
        }
        return result;
    }
}

