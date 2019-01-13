package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public String toString() {
        return "User{"
                +
                "name='" + name + '\''
                +
                ", children=" + children
                +
                ", birthday=" + birthday.getTime()
                +
                '}';
    }

    public static void main(String[] args) {
        User one = new User("Denis", 2, new GregorianCalendar(1983, 3, 24));
        User two = new User("Denis", 2, new GregorianCalendar(1983, 3, 24));
        Map<User, Integer> map = new HashMap<>();
        map.put(one, 100);
        map.put(two, 150);
        for (Map.Entry<User, Integer> entry : map.entrySet()) {
            User key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + " - " + value);

        }

    }
}
