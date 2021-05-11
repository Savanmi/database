package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
