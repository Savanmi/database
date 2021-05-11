package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Address;
import com.vaadin.database.data.entity.Balances;
import org.springframework.data.repository.CrudRepository;

public interface BalanceRepository extends CrudRepository<Balances, Integer> {
}
