package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Telephone_exchanges;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Telephone_exchangesRepository extends CrudRepository<Telephone_exchanges, Integer> {

    @Query(value = "SELECT TELEPHONE_EXCHANGE_ID,\n" +
            "    GET_DEBTOR_COUNT(TELEPHONE_EXCHANGE_ID) AS DEBTOR_COUNT\n" +
            "    FROM TELEPHONE_EXCHANGES\n", nativeQuery = true)
    List<Object[]> findTelephoneExchangesByDebtors();

    @Query(value = "    SELECT TELEPHONE_EXCHANGE_ID,\n" +
            "    GET_MAX_DEBT(TELEPHONE_EXCHANGE_ID) AS MAX_DEBT\n" +
            "    FROM TELEPHONE_EXCHANGES\n", nativeQuery = true)
    List<Object[]> findTelephoneExchangesByDebts();
}
