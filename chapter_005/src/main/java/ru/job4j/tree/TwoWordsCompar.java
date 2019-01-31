package ru.job4j.tree;

import java.util.HashMap;
import java.util.Map;

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
     * @param first words.
     * @param two   word.
     */
    public boolean wordsComparison(String first, String two) {
        boolean result = false;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        char[] arrOne = first.toCharArray();
        char[] arrTwo = two.toCharArray();
        if (first.length() == two.length()) {
            for (int i = 0; i < first.length(); i++) {
                map1.putIfAbsent(arrOne[i], 1);
                map1.computeIfPresent(arrOne[i], (k, v) -> v + 1);
                map2.putIfAbsent(arrTwo[i], 1);
                map2.computeIfPresent(arrTwo[i], (k, v) -> v + 1);
            }
            result = map1.equals(map2);
        }
        return result;
    }
}
