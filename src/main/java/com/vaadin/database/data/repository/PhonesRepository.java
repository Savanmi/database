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

    @Query(value = "SELECT\n" +
            "    DISTINCT\n" +
            "    P.PHONE_NUMBER_ID,\n" +
            "    PN.PHONE_NUMBER,\n" +
            "    CALLER_ID,\n" +
            "    PN.TELEPHONE_EXCHANGE_ID,\n" +
            "    P.ADDRESS_ID\n" +
            "FROM\n" +
            "    PHONES P\n" +
            "    JOIN PHONE_TYPES PT USING (PHONE_TYPE_ID)\n" +
            "    JOIN PHONE_NUMBERS PN on PN.PHONE_NUMBER_ID = P.PHONE_NUMBER_ID\n" +
            "    JOIN FREE_PHONES_POSSIBILITIES FREE_PH ON (P.ADDRESS_ID = FREE_PH.ADDRESS_ID\n" +
            "                                                  AND PN.TELEPHONE_EXCHANGE_ID = FREE_PH.TELEPHONE_EXCHANGE_ID)\n" +
            "WHERE\n" +
            "    TYPE_NAME = 'Спаренный'", nativeQuery = true)
    List<Object[]> findPairedPhones();
}
