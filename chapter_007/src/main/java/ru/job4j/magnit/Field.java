package ru.job4j.magnit;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Field {
    private int value;

    public Field() {
    }

    public Field(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
