package ru.job4j.todolist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class TodolistDao {

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public TodolistDao() {
    }

    public void save(Item item) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(item);
        tx1.commit();
        session.close();
    }

    public List<Item> findAll() {
        Session session = sessionFactory.openSession();
        List<Item> items = (List<Item>) session.createQuery("from Item").list();
        session.close();
        return items;
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
        Session session = sessionFactory.openSession();
        List<Item> items = (List<Item>) session.createQuery("from Item where done = true").list();
        session.close();
        return items;
    }
}
