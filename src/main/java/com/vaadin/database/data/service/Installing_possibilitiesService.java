package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.entity.Installing_possibilities;
import com.vaadin.database.data.repository.Installing_possibilitiesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Installing_possibilitiesService {

    private Installing_possibilitiesRepository installing_possibilitiesRepository;

    public Installing_possibilitiesService(Installing_possibilitiesRepository installing_possibilitiesRepository){
        this.installing_possibilitiesRepository = installing_possibilitiesRepository;
    }

    public List<Installing_possibilities> findAll() {
        return (List<Installing_possibilities>) installing_possibilitiesRepository.findAll();
    }

    public long count() {
        return installing_possibilitiesRepository.count();
    }

    public void delete(Installing_possibilities installing_possibilities) {
        installing_possibilitiesRepository.delete(installing_possibilities);
    }

    public void save(Installing_possibilities installing_possibilities) {
        if (installing_possibilities == null) {
            System.out.println("You try to save null object");
            return;
        }
        installing_possibilitiesRepository.save(installing_possibilities);

    }
}
