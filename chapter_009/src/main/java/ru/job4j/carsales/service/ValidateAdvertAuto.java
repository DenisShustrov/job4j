package ru.job4j.carsales.service;

import ru.job4j.carsales.model.AdvertAuto;

import java.util.List;

public interface ValidateAdvertAuto {

    int addAdvertAuto(AdvertAuto advertAuto);

    void updateAdvertAuto(AdvertAuto advertAuto);

    void deleteAdvertAuto(AdvertAuto advertAuto);

    List<AdvertAuto> findAllAdvertAuto();

    AdvertAuto findAdvertAutoById(int id);

    List<AdvertAuto> findAdvertAutoBySellerId(int id);

    void changeStatus(int id);

    List<AdvertAuto> findAdvertAutoAddLastDay();

    List<AdvertAuto> findAdvertAutoWithPhoto();

    List<AdvertAuto> findAdvertAutoAddByMark(String mark);
}
