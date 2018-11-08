package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author dshustrov (denisshustroff@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class AdditionalTaskTest {

    /**
     * * Test separation.
     */
    @Test
    public void whenOneArrayThenTwoArrays() {
        AdditionalTask task = new AdditionalTask();
        int[] array = {10, 1, 2, 3, 2, 1};
        PairArrays pair = task.separation(array);
        int[] result = pair.getArray1();
        int[] expect = {10};
        assertThat(result, is(expect));
    }
    /**
     * * Test separation.
     */
    @Test
    public void whenOneArrayThenTwoArrays2() {
        AdditionalTask task = new AdditionalTask();
        int[] array = {10, 1, 2, 3, 2, 1};
        PairArrays pair = task.separation(array);
        int[] result = pair.getArray2();
        int[] expect = {3, 2, 2, 1, 1};
        assertThat(result, is(expect));
    }
    /**
     * * Test separation.
     */
    @Test
    public void whenOneArrayThenTwoArrays3() {
        AdditionalTask task = new AdditionalTask();
        int[] array = {10, 10, 10, 10, 10, 10};
        PairArrays pair = task.separation(array);
        int[] result = pair.getArray2();
        int[] expect = {10, 10, 10};
        assertThat(result, is(expect));
    }

    /**
     * * Test separation.
     */
    @Test
    public void whenOneArrayThenTwoArrays4() {
        AdditionalTask task = new AdditionalTask();
        int[] array = {56, 10, 35, 56, 15};
        PairArrays pair = task.separation(array);
        int[] result = pair.getArray1();
        int[] expect = {56, 35};
        assertThat(result, is(expect));
    }
}