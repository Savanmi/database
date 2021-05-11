package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.entity.Public_phones;
import com.vaadin.database.data.repository.Public_phonesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Public_phonesService {

    private Public_phonesRepository public_phonesRepository;

    public Public_phonesService(Public_phonesRepository public_phonesRepository){
        this.public_phonesRepository = public_phonesRepository;
    }

    public List<Public_phones> findAll() {
        return (List<Public_phones>) public_phonesRepository.findAll();
    }

    public long count() {
        return public_phonesRepository.count();
    }

    public void delete(Public_phones public_phones) {
        public_phonesRepository.delete(public_phones);
    }

    public void save(Public_phones public_phones) {
        if (public_phones == null) {
            System.out.println("You try to save null object");
            return;
        }
        public_phonesRepository.save(public_phones);

    }
}
