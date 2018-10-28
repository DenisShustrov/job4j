package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author dshustrov (denisshustroff@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class CalculatorTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Test subtract.
     */
    @Test
    public void whenSubtractThreeMinusTwoThenOne() {
        Calculator calc = new Calculator();
        calc.subtract(3D, 2D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    /**
     * Test multiply.
     */
    @Test
    public void whenMultiplyThreeOnTwoThenSix() {
        Calculator calc = new Calculator();
        calc.multiply(3D, 2D);
        double result = calc.getResult();
        double expected = 6D;
        assertThat(result, is(expected));
    }

    /**
     * Test div.
     */
    @Test
    public void whenDivThreeOnThreeThenOne() {
        Calculator calc = new Calculator();
        calc.div(3D, 3D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }
}
