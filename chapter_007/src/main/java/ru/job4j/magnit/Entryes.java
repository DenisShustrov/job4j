package ru.job4j.magnit;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
public class Entryes {

    private List<Entry> entry;

    public Entryes(List<Entry> entry) {
        this.entry = entry;
    }

    public Entryes() {
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    public List<Entry> getEntry() {
        return entry;
    }
}
