package ru.job4j.carsales.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.carsales.model.AdvertAuto;
import ru.job4j.carsales.utils.HibernateSessionFactoryUtil;

import javax.persistence.TemporalType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdvertAutoDaoImp implements AdvertAutoDao {

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    private final Logger logger = LogManager.getLogger(AdvertAutoDaoImp.class);

    @Override
    public int addAdvertAuto(AdvertAuto advertAuto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(advertAuto);
        logger.info("New advertAuto: " + advertAuto);
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public void updateAdvertAuto(AdvertAuto advertAuto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(advertAuto);
        logger.info("Update advertAuto: " + advertAuto);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteAdvertAuto(AdvertAuto advertAuto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(advertAuto);
        logger.info("Delete advertAuto: " + advertAuto);
        transaction.commit();
        session.close();
    }

    @Override
    public List<AdvertAuto> findAllAdvertAuto() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<AdvertAuto> list = session.createQuery("from AdvertAuto").list();
        logger.info("All adverts: " + list);
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public AdvertAuto findAdvertAutoById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AdvertAuto advertAuto = session.get(AdvertAuto.class, id);
        logger.info("Find advert: " + advertAuto);
        transaction.commit();
        session.close();
        return advertAuto;
    }

    @Override
    public List<AdvertAuto> findAdvertAutoBySellerId(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from AdvertAuto where seller =:paramId");
        query.setInteger("paramId", id);
        List<AdvertAuto> list = query.list();
        logger.info("Find adverts: " + list);
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public void changeStatus(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update AdvertAuto set saleStatus=:statusParam where id=:idParam");
        query.setParameter("statusParam", true);
        query.setInteger("idParam", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<AdvertAuto> findAdvertAutoAddLastDay() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(new Date());
        Query query = session.createQuery("from AdvertAuto where createdate_a =?1");
        try {
            query.setParameter(1, new SimpleDateFormat("yyyy-MM-dd").parse(currentDate), TemporalType.DATE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<AdvertAuto> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<AdvertAuto> findAdvertAutoWithPhoto() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from AdvertAuto where photoPath != ''");
        List<AdvertAuto> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<AdvertAuto> findAdvertAutoAddByMark(String mark) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from AdvertAuto where mark =:markParam");
        query.setParameter("markParam", mark);
        List<AdvertAuto> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public static void main(String[] args) {
        AdvertAutoDaoImp ad = new AdvertAutoDaoImp();
        List<AdvertAuto> list = ad.findAdvertAutoAddByMark("ниссан");
        System.out.println(list);


    }
}
