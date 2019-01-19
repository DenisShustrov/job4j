package ru.job4j.tree;

import java.util.Arrays;

/**
 * Class TwoWordsCompar.
 *
 * @author dshustrov
 * @version 1
 * @since 19.01.2019
 */
public class TwoWordsCompar {
    /**
     * Method checks whether it is possible to collect the first word from the second.
     *
     * @param first collection.
     * @param two   collection.
     */
    public boolean wordsComparison(String first, String two) {
        boolean result = false;
        char[] arrOne = first.toCharArray();
        char[] arrTwo = two.toCharArray();
        Arrays.sort(arrOne);
        Arrays.sort(arrTwo);
        String firstWord = new String(arrOne);
        String twoWord = new String(arrTwo);
        if (firstWord.equals(twoWord)) {
            result = true;
        }
        return result;
    }
}
