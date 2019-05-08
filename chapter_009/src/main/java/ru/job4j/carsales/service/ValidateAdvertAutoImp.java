package ru.job4j.carsales.service;

import ru.job4j.carsales.dao.AdvertAutoDaoImp;
import ru.job4j.carsales.model.AdvertAuto;

import java.util.List;

public class ValidateAdvertAutoImp implements ValidateAdvertAuto {

    private final AdvertAutoDaoImp advertAutoDaoImp = new AdvertAutoDaoImp();

    private final static ValidateAdvertAutoImp VALIDATE_ADVERT_AUTO_IMP = new ValidateAdvertAutoImp();

    private ValidateAdvertAutoImp() {

    }

    public static ValidateAdvertAutoImp getInstance() {
        return VALIDATE_ADVERT_AUTO_IMP;
    }

    @Override
    public int addAdvertAuto(AdvertAuto advertAuto) {
        return advertAutoDaoImp.addAdvertAuto(advertAuto);
    }

    @Override
    public void updateAdvertAuto(AdvertAuto advertAuto) {
        advertAutoDaoImp.updateAdvertAuto(advertAuto);
    }

    @Override
    public void deleteAdvertAuto(AdvertAuto advertAuto) {
        advertAutoDaoImp.deleteAdvertAuto(advertAuto);
    }

    @Override
    public List<AdvertAuto> findAllAdvertAuto() {
        return advertAutoDaoImp.findAllAdvertAuto();
    }

    @Override
    public AdvertAuto findAdvertAutoById(int id) {
        return advertAutoDaoImp.findAdvertAutoById(id);
    }

    @Override
    public List<AdvertAuto> findAdvertAutoBySellerId(int id) {
        return advertAutoDaoImp.findAdvertAutoBySellerId(id);
    }

    @Override
    public void changeStatus(int id) {
        advertAutoDaoImp.changeStatus(id);
    }
}
