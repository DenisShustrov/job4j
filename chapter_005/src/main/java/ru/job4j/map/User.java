package ru.job4j.map;

import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                &&
                Objects.equals(name, user.name)
                &&
                Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User one = new User("Denis", 2, new GregorianCalendar(1983, Calendar.JANUARY, 24));
        User two = new User("Denis", 2, new GregorianCalendar(1983, Calendar.JANUARY, 24));
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
