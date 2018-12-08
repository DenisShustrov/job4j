package ru.job4j.bank;

import java.util.Objects;

/**
 * Class User.
 *
 * @author dshustrov
 * @version 1
 * @since 01.12.2018
 */
public class User {
    /**
     * name имя пользователя.
     */
    private String name;
    /**
     * passport паспорт пользователя.
     */
    private String passport;

    /**
     * Конструктор.
     *
     * @param name имя.
     * @param passport пасспорт.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
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
        return name.equals(user.name)
                &&
                passport.equals(user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }

    @Override
    public String toString() {
        return "User{"
                +
                "passport='"
                + passport
                + '\''
                +
                '}';
    }
}
