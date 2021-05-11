package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Callers;
import com.vaadin.database.data.entity.Telephone_exchanges;
import com.vaadin.database.data.repository.Telephone_exchangesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Telephone_exchangesService {
    private Telephone_exchangesRepository telephone_exchangesRepository;

    public Telephone_exchangesService (Telephone_exchangesRepository telephone_exchangesRepository){
        this.telephone_exchangesRepository = telephone_exchangesRepository;
    }

    public List<Telephone_exchanges> findAll() {
        return (List<Telephone_exchanges>) telephone_exchangesRepository.findAll();
    }

    public long count() {
        return telephone_exchangesRepository.count();
    }

    public void delete(Telephone_exchanges tex) {
        telephone_exchangesRepository.delete(tex);
    }

    public void save(Telephone_exchanges tex) {
        if (tex == null) {
            System.out.println("You try to save null object");
            return;
        }
        telephone_exchangesRepository.save(tex);

    }
}
