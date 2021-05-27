package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.entity.Phones;
import com.vaadin.database.data.repository.PhonesRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhonesService {
    private PhonesRepository phonesRepository;

    public List<Object[]> findPhonesList(String street,boolean blocked){
        return phonesRepository.findPhonesList(street, blocked);
    }

    public PhonesService(PhonesRepository phonesRepository){
        this.phonesRepository = phonesRepository;
    }

    public List<Phones> findAll() {
        return (List<Phones>) phonesRepository.findAll();
    }

    public long count() {
        return phonesRepository.count();
    }

    public void delete(Phones phones) {
        phonesRepository.delete(phones);
    }

    public void save(Phones phones) {
        if (phones == null) {
            System.out.println("You try to save null object");
            return;
        }
        phonesRepository.save(phones);

    }
}
