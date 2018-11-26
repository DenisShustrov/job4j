package ru.job4j.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class ConvertList2Array.
 *
 * @author dshustrov
 * @version 1
 * @since 26.11.2018
 */
public class ConvertList2Array {
    /**
     * Метод равномерно разбивает лист на количество строк двумерного массива.
     *
     * @param list список на основе List.
     * @param rows количество строк.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows;
        if (list.size() % rows != 0) {
            cells = list.size() / rows + 1;
        }
        int[][] array = new int[rows][cells];
        Iterator<Integer> iter = list.iterator();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (iter.hasNext()) {
                    array[i][j] = iter.next();
                }
            }
        }
        return array;
    }

    /**
     * Метод добавляет все элементы входного листа в другой лист.
     *
     * @param list список на основе List состоящий из int[].
     * @return result список на основе List<Integer>.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int y : list.get(i)) {
                result.add(y);
            }
        }
        return result;
    }
}

