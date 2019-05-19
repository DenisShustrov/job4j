package ru.job4j.carsales.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
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
        logger.info("New advertAuto: " + advertAuto);
        return (int) WrapperDao.tx(session -> session.save(advertAuto), sessionFactory);
    }

    @Override
    public void updateAdvertAuto(AdvertAuto advertAuto) {
        logger.info("Update advertAuto: " + advertAuto);
        WrapperDao.txNotResult(session -> session.update(advertAuto), sessionFactory);
    }

    @Override
    public void deleteAdvertAuto(AdvertAuto advertAuto) {
        logger.info("Delete advertAuto: " + advertAuto);
        WrapperDao.txNotResult(session -> session.delete(advertAuto), sessionFactory);
    }

    @Override
    public List<AdvertAuto> findAllAdvertAuto() {
        return WrapperDao.tx(session -> session.createQuery("from AdvertAuto").list(),
                sessionFactory);
    }

    @Override
    public AdvertAuto findAdvertAutoById(int id) {
        return WrapperDao.tx(session -> session.get(AdvertAuto.class, id), sessionFactory);
    }

    @Override
    public List<AdvertAuto> findAdvertAutoBySellerId(int id) {
        return WrapperDao.tx(session -> session.createQuery("from AdvertAuto where seller =:paramId")
                .setInteger("paramId", id).list(), sessionFactory);
    }

    @Override
    public void changeStatus(int id) {
        WrapperDao.txNotResult(session -> session.createQuery("update AdvertAuto set saleStatus=:statusParam where id=:idParam")
                        .setParameter("statusParam", true)
                        .setInteger("idParam", id)
                        .executeUpdate(),
                sessionFactory);
    }

    @Override
    public List<AdvertAuto> findAdvertAutoAddLastDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(new Date());
        Date simpleDateFormat = null;
        try {
            simpleDateFormat = formatter.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date finalSimpleDateFormat = simpleDateFormat;
        return WrapperDao.tx(session -> session.createQuery("from AdvertAuto where createdate_a =?1")
                .setParameter(1, finalSimpleDateFormat, TemporalType.DATE)
                .list(), sessionFactory);
    }

    @Override
    public List<AdvertAuto> findAdvertAutoWithPhoto() {
        return WrapperDao.tx(session -> session.createQuery("from AdvertAuto where photoPath != ''").list(),
                sessionFactory);
    }

    @Override
    public List<AdvertAuto> findAdvertAutoAddByMark(String mark) {
        return WrapperDao.tx(session -> session.createQuery("from AdvertAuto where mark =:markParam")
                .setParameter("markParam", mark).list(), sessionFactory);
    }
}
