package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.entity.Telephone_exchanges;
import com.vaadin.database.data.repository.Connection_pricesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Connection_pricesService {
    private Connection_pricesRepository connection_pricesRepository;

    public Connection_pricesService (Connection_pricesRepository connection_pricesRepository){
        this.connection_pricesRepository = connection_pricesRepository;
    }

    public List<Connection_prices> findAll() {
        return (List<Connection_prices>) connection_pricesRepository.findAll();
    }

    public long count() {
        return connection_pricesRepository.count();
    }

    public void delete(Connection_prices connection_prices) {
        connection_pricesRepository.delete(connection_prices);
    }

    public void save(Connection_prices connection_prices) {
        if (connection_prices == null) {
            System.out.println("You try to save null object");
            return;
        }
        connection_pricesRepository.save(connection_prices);

    }
}
