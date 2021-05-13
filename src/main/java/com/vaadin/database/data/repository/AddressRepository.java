package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Address;
import com.vaadin.database.data.entity.Clients;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    @Query("select c from Address c " +
            "where lower(c.city) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.region) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(c.street) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(c.Zip_code) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(c.building_number) like lower(concat('%', :searchTerm, '%'))")
    List<Address> search(@Param("searchTerm") String searchTerm);
}
