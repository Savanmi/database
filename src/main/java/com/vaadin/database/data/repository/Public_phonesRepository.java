package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Public_phones;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Public_phonesRepository extends CrudRepository<Public_phones, Integer> {
    @Query(value = "SELECT\n" +
            "    PUBLIC_PHONE_ID,\n" +
            "    TELEPHONE_EXCHANGE_ID,\n" +
            "    CITY,\n" +
            "    REGION,\n" +
            "    STREET,\n" +
            "    BUILDING_NUMBER\n" +
            "FROM\n" +
            "    bd_gts.PUBLIC_PHONES PP\n" +
            "    JOIN bd_gts.address ADDR ON PP.ADDRESS_ID = ADDR.Address_ID\n" +
            "WHERE\n" +
            "    PP.TELEPHONE_EXCHANGE_ID = :exchange_id AND\n" +
            "    ADDR.REGION = :region\n" +
            "ORDER BY\n" +
            "    PP.PUBLIC_PHONE_ID\n", nativeQuery = true)
    List<Object[]> findPublicPhonesList(@Param("region")String region, @Param("exchange_id") Integer exchange_id);

}
