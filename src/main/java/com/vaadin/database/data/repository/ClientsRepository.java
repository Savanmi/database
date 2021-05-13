package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Clients;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientsRepository extends CrudRepository<Clients, Integer> {
    @Query("select c from Clients c " +
            "where lower(c.first_name) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.second_name) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(c.middle_name) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(c.gender) like lower(concat('%', :searchTerm, '%'))")
    List<Clients> search(@Param("searchTerm") String searchTerm);
}
