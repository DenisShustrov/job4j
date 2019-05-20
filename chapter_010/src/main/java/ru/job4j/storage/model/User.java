package ru.job4j.storage.model;

import java.util.Objects;

public class User {

    private String name;

    private String lastName;

    private int years;

    public User(String name, String lastName, int years) {
        this.name = name;
        this.lastName = lastName;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
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
        return years == user.years
                &&
                Objects.equals(name, user.name)
                &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, years);
    }

    @Override
    public String toString() {
        return "User{"
                +
                "name='" + name + '\''
                +
                ", lastName='" + lastName + '\''
                +
                ", years=" + years
                +
                '}';
    }
}
