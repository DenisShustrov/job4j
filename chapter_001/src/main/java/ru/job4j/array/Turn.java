package ru.job4j.array;

/**
 * Class FindLoop.
 * @author dshustrov
 * @version 1
 * @since 01.11.2018
 */
public class Turn {

    /**
     * Метод индекс элемента массива по заданному условию или -1.
     *
     * @param array массив чисел.
     * @return перевернутый массив.
     */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
}
