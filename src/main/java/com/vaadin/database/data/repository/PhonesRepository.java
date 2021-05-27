package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Phones;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhonesRepository extends CrudRepository<Phones, Integer> {

    @Query(value = "SELECT\n" +
            "    PHONE_ID,\n" +
            "    ADDRESS_ID,\n" +
            "    CITY,\n" +
            "    REGION,\n" +
            "    STREET,\n" +
            "    BUILDING_NUMBER,\n" +
            "    IS_BLOCKED\n" +
            "FROM\n" +
            "    bd_gts.PHONES P\n" +
            "    JOIN bd_gts.ADDRESS ADDR USING (ADDRESS_ID)\n" +
            "    JOIN bd_gts.CALLERS CA on P.CALLER_ID = CA.CALLER_ID\n" +
            "WHERE\n" +
            "    (STREET = :street) AND\n" +
            "    (IS_BLOCKED = :blocked)\n" +
            "ORDER BY\n" +
            "    PHONE_ID", nativeQuery = true)
    List<Object[]> findPhonesList(@Param("street") String street, @Param("blocked") boolean blocked);
}
