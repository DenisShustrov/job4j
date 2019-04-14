package ru.job4j.todolist.model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Class Item.
 *
 * @author dshustrov
 * @version 1
 * @since 09.04.2019
 */
public class Item {
    private int id;
    private String description;
    private Timestamp created;
    private boolean done;

    public Item() {
    }

    public Item(String description) {
        this.description = description;
        this.created = new Timestamp(System.currentTimeMillis());
        this.done = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id
                &&
                done == item.done
                &&
                Objects.equals(description, item.description)
                &&
                Objects.equals(created, item.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, done);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public boolean isDone() {
        return done;
    }
}
