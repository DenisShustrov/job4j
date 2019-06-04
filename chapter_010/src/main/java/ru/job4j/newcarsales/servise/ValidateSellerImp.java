package ru.job4j.newcarsales.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.newcarsales.model.Seller;
import ru.job4j.newcarsales.repository.SellerCrudRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ValidateSellerImp implements ValidateSeller {


    private final SellerCrudRepository sellerCrudRepository;

    @Autowired
    public ValidateSellerImp(final SellerCrudRepository sellerCrudRepository) {
        this.sellerCrudRepository = sellerCrudRepository;
    }

    @Override
    public int addSeller(Seller seller) {
        return sellerCrudRepository.save(seller).getId();
    }

    @Override
    @Transactional
    public void updateSeller(Seller seller) {
        Optional<Seller> findSeller = sellerCrudRepository.findById(seller.getId());
        if (findSeller.isPresent()) {
            sellerCrudRepository.save(seller);
        }
    }

    @Override
    public void deleteSeller(Seller seller) {
        sellerCrudRepository.delete(seller);
    }

    @Override
    public List<Seller> findAllSellers() {
        return (List<Seller>) sellerCrudRepository.findAll();
    }

    @Override
    public Seller findSellerById(int id) {
        Seller seller = null;
        Optional<Seller> findSeller = sellerCrudRepository.findById(id);
        if (findSeller.isPresent()) {
            seller = findSeller.get();
        }
        return seller;
    }

    @Override
    public Seller findSeller(String login, String password) {
        return sellerCrudRepository.findSellerByLoginAndPassword(login, password);
    }

    @Override
    public Seller findSellerByName(String name) {
        return sellerCrudRepository.findSellerByName(name);
    }
}
