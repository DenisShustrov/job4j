package ru.job4j.tracker;

import java.io.IOException;
import java.util.List;

/**
 * Interface Input.
 * @author dshustrov
 * @version 1
 * @since 11.11.2018
 */
public interface Input {
    String ask(String question) throws IOException;
    int ask(String question, List<Integer> range) throws IOException;
}
