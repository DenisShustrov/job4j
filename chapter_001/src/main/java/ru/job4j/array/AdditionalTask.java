package ru.job4j.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class AdditionalTask.
 *
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
        Arrays.sort(array);
        array2.add(array[array.length - 1]);
        int sum1 = array[array.length - 1];
        int sum2 = 0;
        for (int k = array.length - 1; k > 0; k--) {
            if (sum1 > sum2) {
                array3.add(array[k - 1]);
                sum2 = sum2 + array[k - 1];
            } else {
                array2.add(array[k - 1]);
                sum1 = sum1 + array[k - 1];
            }
        }







        return new PairArrays(transformationInt(array2), transformationInt(array3));
    }

    public static int[] transformationInt(List<Integer> integers) {
        int[] result = new int[integers.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = integers.get(i);
        }
        return result;
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



