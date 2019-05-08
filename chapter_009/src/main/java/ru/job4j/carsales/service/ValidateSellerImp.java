package ru.job4j.carsales.service;

import ru.job4j.carsales.dao.SellerDaoImp;
import ru.job4j.carsales.model.Seller;

import java.util.List;

public class ValidateSellerImp implements ValidateSeller {

    private final SellerDaoImp sellerDaoImp = new SellerDaoImp();

    private final static ValidateSellerImp VALIDATE_SELLER_IMP = new ValidateSellerImp();

    private ValidateSellerImp() {

    }

    public static ValidateSellerImp getInstance() {
        return VALIDATE_SELLER_IMP;
    }

    @Override
    public int addSeller(Seller seller) {
        return sellerDaoImp.addSeller(seller);
    }

    @Override
    public void updateSeller(Seller seller) {
        sellerDaoImp.updateSeller(seller);
    }

    @Override
    public void deleteSeller(Seller seller) {
        sellerDaoImp.deleteSeller(seller);
    }

    @Override
    public List<Seller> findAllSellers() {
        return sellerDaoImp.findAllSellers();
    }

    @Override
    public Seller findSellerById(int id) {
        return sellerDaoImp.findSellerById(id);
    }

    @Override
    public Seller findSeller(String login, String password) {
        return sellerDaoImp.findSeller(login, password);
    }
}
