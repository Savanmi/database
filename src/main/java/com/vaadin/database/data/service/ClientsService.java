package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Clients;
import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.repository.ClientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientsService {

    private ClientsRepository clientsRepository;

    public ClientsService(ClientsRepository clientsRepository){
        this.clientsRepository = clientsRepository;
    }

    public List<Clients> findAll() {
        return (List<Clients>) clientsRepository.findAll();
    }

    public long count() {
        return clientsRepository.count();
    }

    public void delete(Clients clients) {
        clientsRepository.delete(clients);
    }

    public void save(Clients clients) {
        if (clients == null) {
            System.out.println("You try to save null object");
            return;
        }
        clientsRepository.save(clients);

    }
}
