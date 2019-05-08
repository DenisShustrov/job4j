package ru.job4j.carsales.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class Seller.
 *
 * @author dshustrov
 * @version 1
 * @since 30.04.2019
 */
@Entity
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_s")
    private String name;

    @Column(name = "login_s")
    private String login;

    @Column(name = "password_s")
    private String password;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdvertAuto> adverts;

    public Seller() {

    }

    public Seller(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        adverts = new ArrayList<>();
    }

    public void addAdvert(AdvertAuto advertAuto) {
        advertAuto.setSeller(this);
        adverts.add(advertAuto);
    }

    public void removeAuto(AdvertAuto advertAuto) {
        adverts.remove(advertAuto);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AdvertAuto> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<AdvertAuto> adverts) {
        this.adverts = adverts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Seller seller = (Seller) o;
        return id == seller.id
                &&
                Objects.equals(name, seller.name)
                &&
                Objects.equals(login, seller.login)
                &&
                Objects.equals(password, seller.password)
                &&
                Objects.equals(adverts, seller.adverts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, adverts);
    }

    @Override
    public final String toString() {
        return "Seller{"
                +
                "id=" + id
                +
                ", name='" + name + '\''
                +
                ", login='" + login + '\''
                +
                ", password='" + password + '\''
                +
                '}';
    }
}
