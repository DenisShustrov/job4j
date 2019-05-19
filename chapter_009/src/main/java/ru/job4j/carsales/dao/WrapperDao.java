package ru.job4j.carsales.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Consumer;
import java.util.function.Function;

public class WrapperDao {

    public static <T> T tx(final Function<Session, T> command, final SessionFactory sessionFactory) {
        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static void txNotResult(final Consumer<Session> command, final SessionFactory sessionFactory) {
        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
