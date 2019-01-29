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
     * @param first collection.
     * @param two   collection.
     */
    public boolean wordsComparison(String first, String two) {
        boolean result = false;
//        char[] arrOne = first.toCharArray();
//        char[] arrTwo = two.toCharArray();
//        Arrays.sort(arrOne);
//        Arrays.sort(arrTwo);
//        String firstWord = new String(arrOne);
//        String twoWord = new String(arrTwo);
//        if (firstWord.equals(twoWord)) {
//            result = true;
//        }
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        char[] arrOne = first.toCharArray();
        char[] arrTwo = two.toCharArray();
        if (first.length() == two.length()) {
            for (int i = 0; i < first.length(); i++) {
                if (!map1.containsKey(arrOne[i])) {
                    map1.put(arrOne[i], 1);
                } else {
                    map1.put(arrOne[i], map1.get(arrOne[i]) + 1);
                }
                if (!map2.containsKey(arrTwo[i])) {
                    map2.put(arrTwo[i], 1);
                } else {
                    map2.put(arrTwo[i], map2.get(arrTwo[i]) + 1);
                }
            }
            result = map1.equals(map2);
        }
        return result;
    }
}
