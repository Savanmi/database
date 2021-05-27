package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Phone_numbers;
import com.vaadin.database.data.entity.Phone_types;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Phone_numbersRepository extends CrudRepository<Phone_numbers, Integer> {

    @Query(value = "    SELECT\n" +
            "        DISTINCT\n" +
            "        PN.PHONE_NUMBER_ID,\n" +
            "        PN.PHONE_NUMBER,\n" +
            "        IP.TELEPHONE_EXCHANGE_ID,\n" +
            "        IP.ADDRESS_ID\n" +
            "        \n" +
            "        \n" +
            "    FROM\n" +
            "        PHONE_NUMBERS PN\n" +
            "        INNER JOIN INSTALLING_POSSIBILITIES IP ON IP.TELEPHONE_EXCHANGE_ID = PN.TELEPHONE_EXCHANGE_ID\n" +
            "             WHERE\n" +
            "              (PN.TELEPHONE_EXCHANGE_ID = :tex) AND\n" +
            "                (ADDRESS_ID = :address)", nativeQuery = true)
    List<Object[]> findFreePhonesList(@Param("tex") Integer texID, @Param("address") Integer address );



}
