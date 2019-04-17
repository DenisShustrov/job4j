package ru.job4j.autostorage;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class EngineCar.
 *
 * @author dshustrov
 * @version 1
 * @since 16.04.2019
 */
@Entity
@Table(name = "engine_car")
public class EngineCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "volume")
    private double volume;
    @Column(name = "power")
    private int power;

    public EngineCar(double volume, int power) {
        this.volume = volume;
        this.power = power;
    }

    public EngineCar() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EngineCar engineCar = (EngineCar) o;
        return id == engineCar.id
                &&
                Double.compare(engineCar.volume, volume) == 0
                &&
                power == engineCar.power;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, volume, power);
    }
}

