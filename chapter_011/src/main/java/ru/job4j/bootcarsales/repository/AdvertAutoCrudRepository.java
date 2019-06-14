package ru.job4j.bootcarsales.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.bootcarsales.domain.AdvertAuto;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface AdvertAutoCrudRepository extends CrudRepository<AdvertAuto, Integer> {

    List<AdvertAuto> findAdvertAutosBySellerId(int id);

    @Modifying
    @Transactional
    @Query("update AdvertAuto a set a.saleStatus=TRUE where id=:idParam")
    void changeStatus(@Param("idParam") int id);

    @Query(value = "SELECT * FROM ADVERTAUTO WHERE createdate_a =:dateParam", nativeQuery = true)
    List<AdvertAuto> findAdvertAutoAddLastDay(@Param("dateParam") Date date);

    @Query(value = "SELECT * FROM ADVERTAUTO WHERE photo_path_a != ''", nativeQuery = true)
    List<AdvertAuto> findAdvertAutoWithPhoto();

    List<AdvertAuto> findAdvertAutoByMark(String mark);
}
