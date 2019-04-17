package ru.job4j.autostorage;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class Machine.
 *
 * @author dshustrov
 * @version 1
 * @since 16.04.2019
 */
@Entity
@Table(name = "machine")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "marca_car")
    private String marca;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "body_car_id")
    private BodyCar body;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_car_id")
    private EngineCar engine;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transmission_car_id")
    private TransmissionCar transmission;

    public Machine() {

    }

    public Machine(String marca, BodyCar body, EngineCar engine, TransmissionCar transmission) {
        this.marca = marca;
        this.body = body;
        this.engine = engine;
        this.transmission = transmission;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BodyCar getBody() {
        return body;
    }

    public void setBody(BodyCar body) {
        this.body = body;
    }

    public EngineCar getEngine() {
        return engine;
    }

    public void setEngine(EngineCar engine) {
        this.engine = engine;
    }

    public TransmissionCar getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionCar transmission) {
        this.transmission = transmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Machine machine = (Machine) o;
        return id == machine.id
                &&
                Objects.equals(marca, machine.marca)
                &&
                Objects.equals(body, machine.body)
                &&
                Objects.equals(engine, machine.engine)
                &&
                Objects.equals(transmission, machine.transmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marca, body, engine, transmission);
    }
}
