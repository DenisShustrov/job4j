package ru.job4j.carsales.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.carsales.model.Seller;
import ru.job4j.carsales.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class SellerDaoImp implements SellerDao {

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    private final Logger logger = LogManager.getLogger(SellerDaoImp.class);

    @Override
    public int addSeller(Seller seller) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(seller);
        logger.info("New seller: " + seller);
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public void updateSeller(Seller seller) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(seller);
        logger.info("Update seller: " + seller);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteSeller(Seller seller) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(seller);
        logger.info("Delete seller: " + seller);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Seller> findAllSellers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Seller> list = (List<Seller>) session.createQuery("from Seller").list();
        logger.info("All sellers: " + list);
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public Seller findSellerById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Seller seller = session.get(Seller.class, id);
        logger.info("Find seller: " + seller);
        transaction.commit();
        session.close();
        return seller;
    }

    @Override
    public Seller findSeller(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Seller where login =:paramLogin AND password =:paramPassword");
        query.setParameter("paramLogin", login);
        query.setParameter("paramPassword", password);
        List<Seller> list = query.list();
        Seller seller = null;
        for (Seller s : list) {
            seller = s;
        }
        logger.info("Find seller: " + seller);
        transaction.commit();
        session.close();
        return seller;
    }

}
