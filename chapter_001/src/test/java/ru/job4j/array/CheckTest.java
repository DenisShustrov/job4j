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
public class CheckTest {

    /**
     * Test Mono.
     */
    @Test
    public void whenDataMonoByTrueThenTrue() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, true, true};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    /**
     * Test NotMono.
     */
    @Test
    public void whenDataNotMonoByTrueThenFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, false, true};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }

    /**
     * Test MonoEven.
     */
    @Test
    public void whenDataMonoByFalseFalseFalseFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {false, false, false, false};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    /**
     * Test NotMonoEven.
     */
    @Test
    public void whenDataNotMonoEvenByTrueThenFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, false, true, false};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }
}
