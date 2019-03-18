package ru.job4j.crud;

import java.util.Date;
import java.util.Objects;

/**
 * Class User.
 *
 * @author dshustrov
 * @version 1
 * @since 06.03.2019
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private String password;
    private String rules;

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public User(int id, String name, String login, String email, String password, String rules) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.rules = rules;
        this.createDate = new Date();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email, createDate);
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRules() {
        return rules;
    }

    @Override
    public String toString() {
        return "User{"
                +
                "id="
                + id
                +
                ", name='"
                + name
                + '\''
                +
                ", login='"
                + login
                + '\''
                +
                ", email='"
                + email
                + '\''
                +
                ", createDate="
                + createDate
                +
                '}';
    }
}
