package ru.job4j.bootcarsales.servise;

import ru.job4j.bootcarsales.domain.Seller;

import java.util.List;

public interface ValidateSeller {

    int addSeller(Seller seller);

    void updateSeller(Seller seller);

    void deleteSeller(Seller seller);

    List<Seller> findAllSellers();

    Seller findSellerById(int id);

    Seller findSeller(String name, String login);

    Seller findSellerByName(String name);
}
