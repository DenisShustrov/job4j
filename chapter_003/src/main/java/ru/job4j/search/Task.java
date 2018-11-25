package ru.job4j.search;

/**
 * Class Task.
 *
 * @author dshustrov
 * @version 1
 * @since 26.11.2018
 */
public class Task {
    private String desc;
    private int priority;

    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }
}

