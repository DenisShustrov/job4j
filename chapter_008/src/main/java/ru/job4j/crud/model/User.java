package ru.job4j.crud.model;

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
    private String country;
    private String region;
    private String city;
    private Date createDate;

    public User(int id,
                String name,
                String login,
                String email,
                String password,
                String rules,
                String country,
                String region,
                String city) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.rules = rules;
        this.country = country;
        this.region = region;
        this.city = city;
        this.createDate = new Date();
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        return id == user.id
                &&
                Objects.equals(name, user.name)
                &&
                Objects.equals(login, user.login)
                &&
                Objects.equals(email, user.email)
                &&
                Objects.equals(password, user.password)
                &&
                Objects.equals(rules, user.rules)
                &&
                Objects.equals(country, user.country)
                &&
                Objects.equals(region, user.region)
                &&
                Objects.equals(city, user.city)
                &&
                Objects.equals(createDate, user.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                login,
                email,
                password,
                rules,
                country,
                region,
                city,
                createDate);
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
                ", password='"
                + password
                + '\''
                +
                ", rules='"
                + rules
                + '\''
                +
                ", country='"
                + country
                + '\''
                +
                ", region='"
                + region
                + '\''
                +
                ", city='"
                + city
                + '\''
                +
                ", createDate="
                + createDate
                +
                '}';
    }
}
