package ru.job4j.department;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author dshustrov (denisshustroff@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StringComparTest {
    @Test
    public void thenTwoStringsComparWnenTrue1() {
        StringCompar stringCompar = new StringCompar("колесо", "коелсо");
        boolean result = stringCompar.twoStringsCompar();
        assertThat(result, is(true));
    }

    @Test
    public void thenTwoStringsComparWnenTrue2() {
        StringCompar stringCompar = new StringCompar("оолеск", "колесо");
        boolean result = stringCompar.twoStringsCompar();
        assertThat(result, is(true));
    }

    @Test
    public void thenTwoStringsComparWnenFalse1() {
        StringCompar stringCompar = new StringCompar("колесо", "колесо");
        boolean result = stringCompar.twoStringsCompar();
        assertThat(result, is(false));
    }

    @Test
    public void thenTwoStringsComparWnenFalse2() {
        StringCompar stringCompar = new StringCompar("колесо", "колесооооо");
        boolean result = stringCompar.twoStringsCompar();
        assertThat(result, is(false));
    }
}
