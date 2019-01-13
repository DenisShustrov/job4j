package ru.job4j.map;

import java.util.Calendar;

/**
 * Class User.
 *
 * @author dshustrov
 * @version 1
 * @since 13.01.2019
 */
public class User {
    /**
     * name user.
     */
    private String name;
    /**
     * count of children.
     */
    private int children;
    /**
     * day of birthday.
     */
    private Calendar birthday;

    /**
     * Constructor.
     *
     * @param name     user.
     * @param children count of children.
     * @param birthday day of birthday.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Method getting name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method getting count of children.
     */
    public int getChildren() {
        return children;
    }

    /**
     * Method getting day of birthday.
     */
    public Calendar getBirthday() {
        return birthday;
    }
}
