package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.entity.Phone_numbers;
import com.vaadin.database.data.entity.Subscription_fees;
import com.vaadin.database.data.repository.Subscription_feesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Subscription_feesService {

    private Subscription_feesRepository subscription_feesRepository;

    public Subscription_feesService(Subscription_feesRepository subscription_feesRepository){
        this.subscription_feesRepository = subscription_feesRepository;
    }

    public List<Subscription_fees> findAll() {
        return (List<Subscription_fees>) subscription_feesRepository.findAll();
    }

    public long count() {
        return subscription_feesRepository.count();
    }

    public void delete(Subscription_fees subscription_fees) {
        subscription_feesRepository.delete(subscription_fees);
    }

    public void save(Subscription_fees subscription_fees) {
        if (subscription_fees == null) {
            System.out.println("You try to save null object");
            return;
        }
        subscription_feesRepository.save(subscription_fees);

    }
}
