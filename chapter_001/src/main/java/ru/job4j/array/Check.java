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
        //boolean result = false;
        int f = 0;
        int t = 0;
        for (boolean b : data) {
            if (!b) {
                f++;
            } else {
                t++;
            }
        }
        return !(f != data.length && t != data.length);
    }
}

