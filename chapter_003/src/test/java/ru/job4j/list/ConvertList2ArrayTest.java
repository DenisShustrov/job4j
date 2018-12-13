package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author dshustrov (denisshustroff@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2ArrayTest {
    /**
     * Test toArray.
     */
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    /**
     * Test toArray.
     */
    @Test
    public void when7ElementsThen8() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                2
        );
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 0},

        };
        assertThat(result, is(expect));
    }

    /**
     * Test convert.
     */
    @Test
    public void whenListArrayIntThenListInteger() {
        ConvertList2Array list = new ConvertList2Array();
//        List<int[]> array = new ArrayList<>();
//        array.add(new int[]{1, 2, 3});
//        array.add(new int[]{5, 6, 0});
        List<int[]> array = List.of(
                new int[]{1, 2, 3},
                new int[]{5, 6, 0});
        List<Integer> result = list.convert(array);
//        List<Integer> expect = new ArrayList<>();
//        expect.add(1);
//        expect.add(2);
//        expect.add(3);
//        expect.add(5);
//        expect.add(6);
//        expect.add(0);
        List<Integer> expect = List.of(1, 2, 3, 5, 6, 0);
        assertThat(result, is(expect));
    }
}
