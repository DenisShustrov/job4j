package ru.job4j.array;

/**
 * Class BubbleSort.
 * @author dshustrov
 * @version 1
 * @since 03.11.2018
 */
public class BubbleSort {

    /**
     * Метод сортирует массив целых чисел.
     *
     * @param array массив чисел.
     * @return отсортированный массив чисел.
     */
    public int[] sort(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
