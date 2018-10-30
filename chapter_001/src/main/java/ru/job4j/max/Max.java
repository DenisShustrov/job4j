package ru.job4j.max;

/**
 * Class Max
 * @author dshustrov
 * @version 2
 * @since 28.10.2018
 */
public class Max {
    /**
     * Возвращает максимальное число.
     * @param first первое число.
     * @return second второе число.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
    /**
     * Возвращает максимальное число.
     * @param first первое число.
     * @param second второе число.
     * @param third  третье число.
     * @return максимальное число.
     */
    public int max(int first, int second, int third) {
        int temp = max(first, second);
        return max(third, temp);
    }
}
