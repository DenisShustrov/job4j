package ru.job4j.coffee;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author dshustrov (denisshustroff@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachineTest {
    /**
     * * Test changes.
     */
    @Test
    public void whenOneArrayThenTwoArrays() {
        CoffeeMachine coffe = new CoffeeMachine();
        int[] result = coffe.changes(100, 35);
        int[] expect = {10, 10, 10, 10, 10, 10, 5};
        assertThat(result, is(expect));
    }
}
