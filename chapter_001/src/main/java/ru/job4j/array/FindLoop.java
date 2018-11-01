package ru.job4j.array;

/**
 * Class FindLoop.
 * @author dshustrov
 * @version 1
 * @since 01.11.2018
 */
public class FindLoop {

    /**
     * Метод индекс элемента массива по заданному условию или -1.
     *
     * @param data массив чисел.
     * @param el заданный параметр.
     * @return индекс элемента в массиве.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                rst = i;
                break;
            }
        }
        return rst;
    }
}
