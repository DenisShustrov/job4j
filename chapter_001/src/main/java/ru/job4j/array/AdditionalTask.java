package ru.job4j.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Class AdditionalTask.
 * @author dshustrov
 * @version 1
 * @since 08.11.2018
 */
public class AdditionalTask {
    /**
     * Метод записывает данные из массива целых чисел в объект PairArrays(int[] array1, int[] array2) так, чтобы сумма элементов в array1 была максимально одинаковой с суммой в array2.
     *
     * @param array массив целых чисел с четным количестовм элементов.
     * @return объект типа Pair, внутри имеет два массива.
     */
    public PairArrays separation(int[] array) {
        List<Integer> array2 = new ArrayList<>();
        List<Integer> array3 = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        array2.add(array[0]);
        int sum1 = array[0];
        int sum2 = 0;
        int[] mas2;
        int[] mas3;
        for (int k = 0; k < array.length - 1; k++) {
            if (sum1 > sum2) {
                array3.add(array[k + 1]);
                sum2 = sum2 + array[k + 1];
            } else {
                array2.add(array[k + 1]);
                sum1 = sum1 + array[k + 1];
            }
        }
        mas2 = array2.stream().mapToInt(b -> b).toArray();
        mas3 = array3.stream().mapToInt(b -> b).toArray();
        return new PairArrays(mas2, mas3);
    }
}

class PairArrays {
    private int[] array1;
    private int[] array2;

    public PairArrays(int[] array1, int[] array2) {
        this.array1 = array1;
        this.array2 = array2;
    }

    public int[] getArray1() {
        return array1;
    }

    public int[] getArray2() {
        return array2;
    }
}



