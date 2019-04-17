package ru.job4j.autostorage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MachineTest {

    private final SessionFactory factory = new Configuration().configure("avto.cfg.xml").buildSessionFactory();

    private final SessionFactory sessionFactory = getSessionFactory();

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure("avtoannotation.cfg.xml");
        configuration.addAnnotatedClass(BodyCar.class);
        configuration.addAnnotatedClass(EngineCar.class);
        configuration.addAnnotatedClass(TransmissionCar.class);
        configuration.addAnnotatedClass(Machine.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }


    @Test
    public void whenCreateMachineThenGetMachine() {
        Machine getMachine;
        Session session = factory.openSession();
        BodyCar bodyCar = new BodyCar("Sedan");
        EngineCar engineCar = new EngineCar(1.4, 120);
        TransmissionCar transmissionCar = new TransmissionCar("AKPP");
        Machine machine = new Machine("Lada", bodyCar, engineCar, transmissionCar);
        Transaction transaction = session.beginTransaction();
        session.save(bodyCar);
        session.save(engineCar);
        session.save(transmissionCar);
        int id = (int) session.save(machine);
        getMachine = session.get(Machine.class, id);
        transaction.commit();
        session.close();
        assertThat(getMachine.getId(), is(id));
    }

    @Test
    public void whenUpdateMachineThenGetNewDate() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        BodyCar bodyCar = new BodyCar("Cupe");
        EngineCar engineCar = new EngineCar(1.8, 160);
        TransmissionCar transmissionCar = new TransmissionCar("ROBOT");
        Machine machine = new Machine("Lada-Xrey", bodyCar, engineCar, transmissionCar);
        session.save(bodyCar);
        session.save(engineCar);
        session.save(transmissionCar);
        session.save(machine);
        machine.setMarca("Moscvich");
        session.update(machine);
        transaction.commit();
        session.close();
        assertThat(machine.getMarca(), is("Moscvich"));
    }

    @Test
    public void whenDeleteMachineThenGetNull() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        BodyCar bodyCar = new BodyCar("Picap");
        EngineCar engineCar = new EngineCar(1.6, 140);
        TransmissionCar transmissionCar = new TransmissionCar("MKPP");
        session.save(bodyCar);
        session.save(engineCar);
        session.save(transmissionCar);
        Machine machine = new Machine("Nissan", bodyCar, engineCar, transmissionCar);
        int id = (int) session.save(machine);
        session.delete(machine);
        Machine delMachine = session.get(Machine.class, id);
        transaction.commit();
        session.close();
        Assert.assertNull(delMachine);
    }

    @Test
    public void whenCreateMachineThenGetMachineByAnnotation() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        BodyCar bodyCar = new BodyCar("Universal");
        EngineCar engineCar = new EngineCar(2.0, 200);
        TransmissionCar transmissionCar = new TransmissionCar("MKPP");
        Machine machine = new Machine("Opel", bodyCar, engineCar, transmissionCar);
        session.save(bodyCar);
        session.save(engineCar);
        session.save(transmissionCar);
        int id = (int) session.save(machine);
        Machine getMachine = session.get(Machine.class, id);
        transaction.commit();
        session.close();
        assertThat(getMachine.getId(), is(id));
    }

    @Test
    public void whenUpdateMachineThenGetNewDateByAnnotation() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        BodyCar bodyCar = new BodyCar("CUV");
        EngineCar engineCar = new EngineCar(1.2, 97);
        TransmissionCar transmissionCar = new TransmissionCar("ROBOT");
        Machine machine = new Machine("HUNDAI", bodyCar, engineCar, transmissionCar);
        session.save(bodyCar);
        session.save(engineCar);
        session.save(transmissionCar);
        session.save(machine);
        machine.setMarca("TOYOTA");
        session.update(machine);
        transaction.commit();
        session.close();
        assertThat(machine.getMarca(), is("TOYOTA"));
    }

    @Test
    public void whenDeleteMachineThenGetNullByAnnotation() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        BodyCar bodyCar = new BodyCar("CROSSOVER");
        EngineCar engineCar = new EngineCar(3.0, 240);
        TransmissionCar transmissionCar = new TransmissionCar("AKPP");
        session.save(bodyCar);
        session.save(engineCar);
        session.save(transmissionCar);
        Machine machine = new Machine("Nissan", bodyCar, engineCar, transmissionCar);
        int id = (int) session.save(machine);
        session.delete(machine);
        Machine delMachine = session.get(Machine.class, id);
        transaction.commit();
        session.close();
        Assert.assertNull(delMachine);
    }
}
