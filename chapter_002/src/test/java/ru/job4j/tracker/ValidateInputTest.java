package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author dshustrov
 * @version $Id$
 * @since 0.1
 */
public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidInput() throws IOException {
        List<Integer> range = new ArrayList<>();
        range.add(1);
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"invalid", "1"})
        );
        input.ask("Enter", range);
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Please enter a valid value.%n")
                )
        );
    }

    @Test
    public void whenOutRangeInput() throws IOException {
        List<Integer> range = new ArrayList<>();
        range.add(1);
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"2", "1"})
        );
        input.ask("Enter", range);
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Please choose a value from the menu range.%n")
                )
        );
    }
}