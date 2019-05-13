package ru.job4j.carsales;

import org.junit.Test;
import ru.job4j.carsales.dao.AdvertAutoDao;
import ru.job4j.carsales.dao.AdvertAutoDaoImp;
import ru.job4j.carsales.dao.SellerDao;
import ru.job4j.carsales.dao.SellerDaoImp;
import ru.job4j.carsales.model.AdvertAuto;
import ru.job4j.carsales.model.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class CarSalesTest {

    private final AdvertAutoDao ad = new AdvertAutoDaoImp();

    private final SellerDao sd = new SellerDaoImp();

    @Test
    public void whenAddAdvertAutoThenGetMarkAuto() {
        Seller seller = new Seller("Denis", "denis123123", "123");
        int id = sd.addSeller(seller);
        Seller denis = sd.findSellerById(id);
        assertThat(denis.getName(), is("Denis"));
        AdvertAuto auto = new AdvertAuto("1", "2", "3", "4", "5", "6", "7", 8, "9", "10", 12, "13", "14");
        auto.setSeller(denis);
        int idA = ad.addAdvertAuto(auto);
        AdvertAuto getAuto = ad.findAdvertAutoById(idA);
        assertThat(getAuto.getMark(), is("1"));
    }

    @Test
    public void whenUpdateAdvertAutoThenGetMarkAuto() {
        Seller seller = new Seller("Denis", "denis123123", "123");
        sd.addSeller(seller);
        AdvertAuto auto = new AdvertAuto("1", "2", "3", "4", "5", "6", "7", 8, "9", "10", 12, "13", "14");
        auto.setSeller(seller);
        int idA = ad.addAdvertAuto(auto);
        auto.setMark("Nissan");
        ad.updateAdvertAuto(auto);
        AdvertAuto getAuto = ad.findAdvertAutoById(idA);
        assertThat(getAuto.getMark(), is("Nissan"));
    }

    @Test
    public void whenDeleteAdvertAutoThenSizeChange() {
        Seller seller = new Seller("Denis", "denis123123", "123");
        sd.addSeller(seller);
        AdvertAuto auto1 = new AdvertAuto("Lada", "2", "3", "4", "5", "6", "7", 8, "9", "10", 12, "13", "14");
        AdvertAuto auto2 = new AdvertAuto("Toyota", "2", "3", "4", "5", "6", "7", 8, "9", "10", 12, "13", "14");
        auto1.setSeller(seller);
        auto2.setSeller(seller);
        ad.addAdvertAuto(auto1);
        ad.addAdvertAuto(auto2);
        List<AdvertAuto> list = ad.findAllAdvertAuto();
        int count = list.size();
        assertThat(list.size(), is(count));
        ad.deleteAdvertAuto(auto1);
        List<AdvertAuto> list2 = ad.findAllAdvertAuto();
        assertThat(list2.size(), is(count - 1));
    }

    @Test
    public void whenAddAdvertAutoThenFindAddLastDay() throws ParseException {
        Seller seller = new Seller("Denis", "denis123123", "123");
        sd.addSeller(seller);
        AdvertAuto auto1 = new AdvertAuto("Nissan", "2", "3", "4", "5", "6", "7", 8, "9", "10", 12, "13", "14");
        AdvertAuto auto2 = new AdvertAuto("Opel", "2", "3", "4", "5", "6", "7", 8, "9", "10", 12, "13", "14");
        auto1.setSeller(seller);
        auto2.setSeller(seller);
        ad.addAdvertAuto(auto1);
        ad.addAdvertAuto(auto2);
        List<AdvertAuto> list1 = ad.findAdvertAutoAddLastDay();
        assertThat(list1.size(), is(2));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(new Date(351422554));
        auto1.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse(currentDate));
        ad.updateAdvertAuto(auto1);
        List<AdvertAuto> list2 = ad.findAdvertAutoAddLastDay();
        assertThat(list2.size(), is(1));
    }

    @Test
    public void whenAddAdvertAutoThenFindWithPhoto() {
        Seller seller = new Seller("Denis", "denis123123", "123");
        sd.addSeller(seller);
        AdvertAuto auto1 = new AdvertAuto("Nissan", "2", "3", "4", "5", "6", "7", 8, "9", "10", 12, "13", "14");
        AdvertAuto auto2 = new AdvertAuto("Opel", "2", "3", "4", "5", "6", "7", 8, "9", "10", 12, "13", "14");
        auto1.setSeller(seller);
        auto2.setSeller(seller);
        ad.addAdvertAuto(auto1);
        ad.addAdvertAuto(auto2);
        List<AdvertAuto> list1 = ad.findAdvertAutoWithPhoto();
        assertThat(list1.size(), is(0));
        auto1.setPhotoPath("/..");
        ad.updateAdvertAuto(auto1);
        List<AdvertAuto> list2 = ad.findAdvertAutoWithPhoto();
        assertThat(list2.size(), is(1));
    }

    @Test
    public void whenAddAdvertAutoThenFindByMark() {
        Seller seller = new Seller("Denis", "denis123123", "123");
        sd.addSeller(seller);
        AdvertAuto auto1 = new AdvertAuto("Volvo", "2", "3", "4", "5", "6", "7", 8, "9", "10", 12, "13", "14");
        AdvertAuto auto2 = new AdvertAuto("Volvo", "2", "3", "4", "5", "6", "7", 8, "9", "10", 12, "13", "14");
        auto1.setSeller(seller);
        auto2.setSeller(seller);
        ad.addAdvertAuto(auto1);
        ad.addAdvertAuto(auto2);
        List<AdvertAuto> list1 = ad.findAdvertAutoAddByMark("Volvo");
        assertThat(list1.size(), is(2));
        auto1.setMark("Opel");
        ad.updateAdvertAuto(auto1);
        List<AdvertAuto> list2 = ad.findAdvertAutoAddByMark("Volvo");
        assertThat(list2.size(), is(1));
    }

}
