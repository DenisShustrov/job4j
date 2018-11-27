package ru.job4j.сonvert;

/**
 * Class User.
 *
 * @author dshustrov
 * @version 1
 * @since 27.11.2018
 */
public class User {
    /**
     * id уникальный номер.
     */
    private int id;
    /**
     * name имя пользователя.
     */
    private String name;
    /**
     * city город пользователя.
     */
    private String city;

    /**
     * Конструктор.
     *
     * @param id   уникальный номер.
     * @param name имя.
     * @param city город.
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

}