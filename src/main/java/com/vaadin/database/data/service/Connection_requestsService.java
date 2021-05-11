package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.entity.Connection_requests;
import com.vaadin.database.data.repository.Connection_requestsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Connection_requestsService {

    private Connection_requestsRepository connection_requestsRepository;

    public Connection_requestsService(Connection_requestsRepository connection_requestsRepository){
        this.connection_requestsRepository = connection_requestsRepository;
    }
    public List<Connection_requests> findAll() {
        return (List<Connection_requests>) connection_requestsRepository.findAll();
    }

    public long count() {
        return connection_requestsRepository.count();
    }

    public void delete(Connection_requests connection_requests) {
        connection_requestsRepository.delete(connection_requests);
    }

    public void save(Connection_requests connection_requests) {
        if (connection_requests == null) {
            System.out.println("You try to save null object");
            return;
        }
        connection_requestsRepository.save(connection_requests);

    }
}
