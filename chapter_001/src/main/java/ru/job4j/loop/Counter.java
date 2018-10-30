package ru.job4j.loop;

/**
 * Class Counter
 * @author dshustrov
 * @version 1
 * @since 30.10.2018
 */
public class Counter {

    /**
     * Метод вычисления сумму четныx чисел в диапазоне от start до finish.
     *
     * @param start начало диапазона.
     * @param finish конец диапазона.
     * @return сумма четныx чисел в диапазоне.
     */
    public int add(int start, int finish) {
        int count = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}
