package ru.job4j.todolist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.function.Function;

public class TodolistDao {

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public TodolistDao() {
    }

    private <T> T tx(final Function<Session, T> command) {
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

    public void save(Item item) {
        this.tx(session -> session.save(item));
    }

    public List<Item> findAll() {
        return this.tx(session -> session.createQuery("from Item").list());
    }

    public void changeStatus(List<String> list) {
        for (String x : list) {
            Session session = sessionFactory.openSession();
            Transaction tx1 = session.beginTransaction();
            Query query = session.createQuery("update Item set done=:doneParam where id=:idParam");
            query.setParameter("doneParam", false);
            query.setParameter("idParam", Integer.parseInt(x));
            query.executeUpdate();
            tx1.commit();
            session.close();
        }
    }

    public List<Item> findAllItemWhenDoneTrue() {
        return this.tx(session -> session.createQuery("from Item where done = true").list());
    }
}
