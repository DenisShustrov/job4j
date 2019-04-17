package ru.job4j.autostorage;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class TransmissionCar.
 *
 * @author dshustrov
 * @version 1
 * @since 16.04.2019
 */
@Entity
@Table(name = "transmission_car")
public class TransmissionCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tupe_transmission")
    private String typeTransmission;

    public TransmissionCar() {

    }

    public TransmissionCar(String typeTransmission) {
        this.typeTransmission = typeTransmission;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeTransmission() {
        return typeTransmission;
    }

    public void setTypeTransmission(String typeTransmission) {
        this.typeTransmission = typeTransmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransmissionCar that = (TransmissionCar) o;
        return id == that.id
                &&
                Objects.equals(typeTransmission, that.typeTransmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeTransmission);
    }
}
