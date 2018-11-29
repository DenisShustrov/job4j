package ru.job4j.comparator;

import java.util.Comparator;

/**
 * Class ListCompare.
 *
 * @author dshustrov
 * @version 1
 * @since 29.11.2018
 */
public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int first = left.length();
        int second = right.length();
        int lim = Math.min(first, second);
        int count = 0;
        while (count < lim) {
            if (left.charAt(count) != right.charAt(count)) {
                return left.charAt(count) - right.charAt(count);
            }
            count++;
        }
        return first - second;
    }

    public static void main(String[] args) {
        ListCompare list = new ListCompare();
        int rt = list.compare("AAA", "BBB");
        System.out.println(rt);

    }
}
