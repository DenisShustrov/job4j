package ru.job4j.inheritance;

/**
 * Class Patient.
 * @author dshustrov
 * @version 1
 * @since 08.11.2018
 */
public class Patient {
    private String name;
    private int appearance;
    public Patient(String name, int appearance) {
        this.name = name;
        this.appearance = appearance;
    }
    public int getAppearance() {
        return appearance;
    }
}
