package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.entity.Connection_requests;
import org.springframework.data.repository.CrudRepository;

public interface Connection_requestsRepository extends CrudRepository<Connection_requests, Integer> {
}
