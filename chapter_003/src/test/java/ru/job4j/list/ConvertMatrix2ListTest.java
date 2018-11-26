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
public class ConvertMatrix2ListTest {
    /**
     * Test ConvertMatrix2List.
     */
    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2, 8, 9},
                {3, 4}
        };
        List<Integer> expect = Arrays.asList(
                1, 2, 8, 9, 3, 4
        );
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }
}
