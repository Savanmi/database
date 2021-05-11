package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.entity.Phone_types;
import com.vaadin.database.data.repository.Phone_typesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Phone_typesService {

    private Phone_typesRepository phone_typesRepository;

    public Phone_typesService(Phone_typesRepository phone_typesRepository){
        this.phone_typesRepository = phone_typesRepository;
    }

    public List<Phone_types> findAll() {
        return (List<Phone_types>) phone_typesRepository.findAll();
    }

    public long count() {
        return phone_typesRepository.count();
    }

    public void delete(Phone_types phone_types) {
        phone_typesRepository.delete(phone_types);
    }

    public void save(Phone_types phone_types) {
        if (phone_types == null) {
            System.out.println("You try to save null object");
            return;
        }
        phone_typesRepository.save(phone_types);
    }
}
