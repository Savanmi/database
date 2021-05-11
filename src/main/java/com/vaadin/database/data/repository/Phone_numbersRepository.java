package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Phone_numbers;
import com.vaadin.database.data.entity.Phone_types;
import org.springframework.data.repository.CrudRepository;

public interface Phone_numbersRepository extends CrudRepository<Phone_numbers, Integer> {
}
