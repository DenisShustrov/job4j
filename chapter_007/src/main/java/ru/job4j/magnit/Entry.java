package ru.job4j.magnit;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
public class Entry {

    private List<Field> fields;

    public Entry(int field) {
    }

    public Entry() {
    }

    public Entry(List<Field> fields) {
        this.fields = fields;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}

