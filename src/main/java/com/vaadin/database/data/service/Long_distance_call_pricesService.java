package com.vaadin.database.data.service;
import com.vaadin.database.data.entity.Long_distance_call_prices;
import com.vaadin.database.data.repository.Long_distance_call_pricesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Long_distance_call_pricesService {

    private Long_distance_call_pricesRepository long_distance_call_pricesRepository;

    public Long_distance_call_pricesService(Long_distance_call_pricesRepository long_distance_call_pricesRepository){
        this.long_distance_call_pricesRepository = long_distance_call_pricesRepository;
    }

    public List<Long_distance_call_prices> findAll() {
        return (List<Long_distance_call_prices>) long_distance_call_pricesRepository.findAll();
    }

    public long count() {
        return long_distance_call_pricesRepository.count();
    }

    public void delete(Long_distance_call_prices ldc_prices) {
        long_distance_call_pricesRepository.delete(ldc_prices);
    }

    public void save(Long_distance_call_prices ldc_prices) {
        if (ldc_prices == null) {
            System.out.println("You try to save null object");
            return;
        }
        long_distance_call_pricesRepository.save(ldc_prices);

    }
}
