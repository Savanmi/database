package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Clients;
import org.springframework.data.repository.CrudRepository;

public interface ClientsRepository extends CrudRepository<Clients, Integer> {
}
