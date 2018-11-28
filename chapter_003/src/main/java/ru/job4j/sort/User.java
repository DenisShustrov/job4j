package ru.job4j.sort;

/**
 * Class User.
 *
 * @author dshustrov
 * @version 1
 * @since 29.11.2018
 */
public class User implements Comparable<User> {
    /**
     * name имя пользователя.
     */
    private String name;
    /**
     * age возраст пользователя.
     */
    private int age;

    /**
     * Конструктор.
     *
     * @param name имя.
     * @param age  возраст.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public int compareTo(User user) {
        return this.age - user.getAge();
    }
}
