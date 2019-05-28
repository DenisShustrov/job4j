package ru.job4j.newcarsales.repository;

        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;
        import ru.job4j.newcarsales.model.Seller;

@Repository
public interface SellerCrudRepository extends CrudRepository<Seller, Integer> {

    Seller findSellerByLoginAndPassword(String login, String password);
}
