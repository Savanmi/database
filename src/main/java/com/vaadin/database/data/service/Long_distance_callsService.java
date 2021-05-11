package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.entity.Long_distance_calls;
import com.vaadin.database.data.repository.Long_distance_callsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Long_distance_callsService {

    private Long_distance_callsRepository long_distance_callsRepository;

    public Long_distance_callsService(Long_distance_callsRepository long_distance_callsRepository){
        this.long_distance_callsRepository = long_distance_callsRepository;
    }

    public List<Long_distance_calls> findAll() {
        return (List<Long_distance_calls>) long_distance_callsRepository.findAll();
    }

    public long count() {
        return long_distance_callsRepository.count();
    }

    public void delete(Long_distance_calls long_distance_calls) {
        long_distance_callsRepository.delete(long_distance_calls);
    }

    public void save(Long_distance_calls long_distance_calls) {
        if (long_distance_calls == null) {
            System.out.println("You try to save null object");
            return;
        }
        long_distance_callsRepository.save(long_distance_calls);

    }
}
