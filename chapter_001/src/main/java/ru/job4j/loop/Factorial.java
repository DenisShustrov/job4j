package ru.job4j.loop;

/**
 * Class Factorial
 * @author dshustrov
 * @version 1
 * @since 31.10.2018
 */
public class Factorial {
    /**
     * Метод вычисления факториала.
     *
     * @param n положительное целое число.
     * @return расчитанный факториал числа.
     */
    public int calc(int n) {
        if (n == 0) return 1;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result = result * i;
        }
        return  result;
    }
}
