package ru.job4j.carsales.dao;

import ru.job4j.carsales.model.Seller;

import java.util.List;

public interface SellerDao {
    int addSeller(Seller seller);

    void updateSeller(Seller seller);

    void deleteSeller(Seller seller);

    List<Seller> findAllSellers();

    Seller findSellerById(int id);

    Seller findSeller(String login, String password);
}
