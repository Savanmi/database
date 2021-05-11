package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.repository.BalanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalancesService {

    private BalanceRepository balanceRepository;

    public BalancesService(BalanceRepository balanceRepository){
        this.balanceRepository = balanceRepository;
    }

    public List<Balances> findAll() {
        return (List<Balances>) balanceRepository.findAll();
    }

    public long count() {
        return balanceRepository.count();
    }

    public void delete(Balances balances) {
        balanceRepository.delete(balances);
    }

    public void save(Balances balances) {
        if (balances == null) {
            System.out.println("You try to save null object");
            return;
        }
        balanceRepository.save(balances);

    }
}
