package ru.job4j.tracker;

import java.io.IOException;
import java.util.List;

/**
 * Class MenuOutException.
 *
 * @author dshustrov
 * @version 1
 * @since 17.11.2018
 */
public class ValidateInput implements Input {
    /**
     * Поле для реализации композиции.
     */
    private final Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) throws IOException {
        return this.input.ask(question);
    }

    public int ask(String question, List<Integer> range) throws IOException {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid value.");
            } catch (MenuOutException moe) {
                System.out.println("Please choose a value from the menu range.");
            }
        } while (invalid);
        return value;
    }
}
