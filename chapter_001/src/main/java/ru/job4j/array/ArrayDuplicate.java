package ru.job4j.array;
import java.util.Arrays;
/**
 * Class ArrayDuplicate.
 * @author dshustrov
 * @version 1
 * @since 03.11.2018
 */
public class ArrayDuplicate {

    /**
     * Метод удаляет дубликаты в массиве.
     *
     * @param array массив строк.
     * @return массив строк без дубликатов.
     */
    public String[] remove(String[] array) {
        int count = 0;
        for (int i = 0; i < array.length / 2; i++) {
            for (int j = i; j < array.length - 1; j++) {
                if (array[i].equals(array[j + 1]) & !array[i].equals("повтор")) {
                    count++;
                    array[j + 1] = "повтор";
                }
            }
        }
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j].equals("повтор")) {
                    array[j] = array[j + 1];
                    array[j + 1] = "повтор";
                }
            }
        }
        return Arrays.copyOf(array, array.length - count);
    }
}
