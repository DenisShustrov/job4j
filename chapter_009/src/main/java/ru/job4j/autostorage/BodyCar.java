package ru.job4j.autostorage;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class BodyCar.
 *
 * @author dshustrov
 * @version 1
 * @since 16.04.2019
 */
@Entity
@Table(name = "body_car")
public class BodyCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tupe_body")
    private String typeBody;

    public BodyCar() {

    }

    public BodyCar(String typeBody) {
        this.typeBody = typeBody;
    }

     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeBody() {
        return typeBody;
    }

    public void setTypeBody(String typeBody) {
        this.typeBody = typeBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BodyCar bodyCar = (BodyCar) o;
        return id == bodyCar.id
                &&
                Objects.equals(typeBody, bodyCar.typeBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeBody);
    }
}
