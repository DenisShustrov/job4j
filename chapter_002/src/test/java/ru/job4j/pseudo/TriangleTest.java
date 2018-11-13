package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author dshustrov
 * @version $Id$
 * @since 0.1
 */
public class TriangleTest {
    /**
     * * Test Triangle.
     */
    @Test
    public void whenDrawSquare() {
        Triangle triangle = new Triangle();
        assertThat(
                triangle.draw(),
                is(
                        new StringBuilder()
                                .append("   ^   ")
                                .append("  ^^^  ")
                                .append(" ^^^^^ ")
                                .append("^^^^^^^")
                                .toString()
                )
        );
    }
}