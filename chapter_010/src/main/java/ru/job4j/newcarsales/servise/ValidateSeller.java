package ru.job4j.newcarsales.servise;

import ru.job4j.newcarsales.model.Seller;

import java.util.List;

public interface ValidateSeller {

    int addSeller(Seller seller);

    void updateSeller(Seller seller);

    void deleteSeller(Seller seller);

    List<Seller> findAllSellers();

    Seller findSellerById(int id);

    Seller findSeller(String login, String password);
}
