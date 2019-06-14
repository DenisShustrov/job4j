package ru.job4j.bootcarsales.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.bootcarsales.domain.Seller;


@Repository
public interface SellerCrudRepository extends CrudRepository<Seller, Integer> {

    Seller findSellerByNameAndLogin(String name, String login);

    Seller findSellerByName(String name);
}
