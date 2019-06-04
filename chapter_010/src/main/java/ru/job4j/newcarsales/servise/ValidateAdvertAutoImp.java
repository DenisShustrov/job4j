package ru.job4j.newcarsales.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.newcarsales.model.AdvertAuto;
import ru.job4j.newcarsales.repository.AdvertAutoCrudRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ValidateAdvertAutoImp implements ValidateAdvertAuto {


    private final AdvertAutoCrudRepository advertAutoCrudRepository;

    @Autowired
    public ValidateAdvertAutoImp(final AdvertAutoCrudRepository advertAutoCrudRepository) {
        this.advertAutoCrudRepository = advertAutoCrudRepository;
    }


    @Override
    public int addAdvertAuto(AdvertAuto advertAuto) {
        return advertAutoCrudRepository.save(advertAuto).getId();
    }

    @Override
    @Transactional
    public void updateAdvertAuto(AdvertAuto advertAuto) {
        Optional<AdvertAuto> optionalAdvertAuto = advertAutoCrudRepository.findById(advertAuto.getId());
        if (optionalAdvertAuto.isPresent()) {
            advertAutoCrudRepository.save(advertAuto);
        }
    }

    @Override
    public void deleteAdvertAuto(AdvertAuto advertAuto) {
        advertAutoCrudRepository.delete(advertAuto);
    }

    @Override
    public List<AdvertAuto> findAllAdvertAuto() {
        return (List<AdvertAuto>) advertAutoCrudRepository.findAll();
    }

    @Override
    public AdvertAuto findAdvertAutoById(int id) {
        AdvertAuto advertAuto = null;
        Optional<AdvertAuto> findAdvertAuto = advertAutoCrudRepository.findById(id);
        if (findAdvertAuto.isPresent()) {
            advertAuto = findAdvertAuto.get();
        }
        return advertAuto;
    }

    @Override
    public List<AdvertAuto> findAdvertAutoBySellerId(int id) {
        return advertAutoCrudRepository.findAdvertAutosBySellerId(id);
    }

    @Override
    @Transactional
    public void changeStatus(int id) {
        advertAutoCrudRepository.changeStatus(id);
    }

    @Override
    public List<AdvertAuto> findAdvertAutoAddLastDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(new Date());
        Date simpleDateFormat = null;
        try {
            simpleDateFormat = formatter.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date finalSimpleDateFormat = simpleDateFormat;
        return advertAutoCrudRepository.findAdvertAutoAddLastDay(finalSimpleDateFormat);
    }

    @Override
    public List<AdvertAuto> findAdvertAutoWithPhoto() {
        return advertAutoCrudRepository.findAdvertAutoWithPhoto();
    }

    @Override
    public List<AdvertAuto> findAdvertAutoAddByMark(String mark) {
        return advertAutoCrudRepository.findAdvertAutoByMark(mark);
    }

}
