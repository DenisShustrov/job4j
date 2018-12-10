package ru.job4j.list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class ConvertList2Array.
 *
 * @author dshustrov
 * @version 1
 * @since 26.11.2018
 */
public class ConvertMatrix2List {
    /**
     * Метод преобразует двумерный массив целых чисе в ArrayList.
     *
     * @param array двумерный массив.
     * @return list динамический массив.
     */
    public List<Integer> toList(int[][] array) {
//        List<Integer> list = new ArrayList<>();
//        for (int[] row : array) {
//            for (int col : row) {
//                list.add(col);
//            }
//        }
        return Arrays.stream(array).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }
}