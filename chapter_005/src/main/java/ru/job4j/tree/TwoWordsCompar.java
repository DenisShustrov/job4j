package ru.job4j.tree;

import java.util.HashSet;

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
        HashSet<Character> set1 = new HashSet<>();
        HashSet<Character> set2 = new HashSet<>();
        char[] arrOne = first.toCharArray();
        char[] arrTwo = two.toCharArray();
        if (first.length() == two.length()) {
            for (int i = 0; i < first.length(); i++) {
                set1.add(arrOne[i]);
                set2.add(arrTwo[i]);
            }
            result = (set1.size() == set2.size());
        }
        System.out.println(set1.size() + " " + set2.size());
        return result;
    }
}
