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
public class ArrayDuplicateTest {

    /**
     * * Test ArrayDuplicate.
     */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] startingLine = {"Привет", "Мир", "Привет", "Супер", "Мир", "Привет"};
        String[] result = arrayDuplicate.remove(startingLine);
        String[] expect = {"Привет", "Мир", "Супер"};
        assertThat(result, is(expect));
    }
}
