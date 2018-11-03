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
public class BubbleSortTest {

    /**
     * * Test sort.
     */
    @Test
    public void whenArrayNotSortThenArraySort() {
        int[] str = {5, 1, 2, 7, 3};
        BubbleSort bubbleSort = new BubbleSort();
        int[] resultArray = bubbleSort.sort(str);
        int[] expectArray =  {1, 2, 3, 5, 7};
        assertThat(resultArray, is(expectArray));
    }

}
