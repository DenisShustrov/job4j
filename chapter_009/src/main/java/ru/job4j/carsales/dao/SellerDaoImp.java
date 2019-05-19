package ru.job4j.carsales.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import ru.job4j.carsales.model.Seller;
import ru.job4j.carsales.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class SellerDaoImp implements SellerDao {

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    private final Logger logger = LogManager.getLogger(SellerDaoImp.class);

    @Override
    public int addSeller(Seller seller) {
        logger.info("New seller: " + seller);
        return (int) WrapperDao.tx(session -> session.save(seller), sessionFactory);

    }

    @Override
    public void updateSeller(Seller seller) {
        logger.info("Update seller: " + seller);
        WrapperDao.txNotResult(session -> session.update(seller), sessionFactory);
    }

    @Override
    public void deleteSeller(Seller seller) {
        logger.info("Delete seller: " + seller);
        WrapperDao.txNotResult(session -> session.delete(seller), sessionFactory);
    }

    @Override
    public List<Seller> findAllSellers() {
        return WrapperDao.tx(session -> (List<Seller>) session.createQuery("from Seller").list(),
                sessionFactory);
    }

    @Override
    public Seller findSellerById(int id) {
        return WrapperDao.tx(session -> session.get(Seller.class, id), sessionFactory);
    }

    @Override
    public Seller findSeller(String login, String password) {
        return (Seller) WrapperDao.tx(session -> session.createQuery("from Seller where login =:paramLogin AND password =:paramPassword")
                .setParameter("paramLogin", login)
                .setParameter("paramPassword", password)
                .list().iterator().next(), sessionFactory);
    }
}
