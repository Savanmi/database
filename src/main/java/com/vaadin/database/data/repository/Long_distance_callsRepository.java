package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Long_distance_calls;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface Long_distance_callsRepository extends CrudRepository<Long_distance_calls, Integer> {
    @Query(value = "WITH\n" +
            "    OUTGOING_CALLS AS(\n" +
            "        SELECT\n" +
            "            LONG_DISTANCE_CALL_ID,\n" +
            "            SOURCE_PHONE_ID,\n" +
            "            ADDRESS_ID,\n" +
            "            CITY\n" +
            "        FROM\n" +
            "            LONG_DISTANCE_CALLS CALLS\n" +
            "            JOIN PHONES P ON (CALLS.SOURCE_PHONE_ID = P.PHONE_ID)\n" +
            "            JOIN ADDRESS ADDR USING (ADDRESS_ID)),\n" +
            "\n" +
            "    OUTGOING_CALLS_COUNT AS(\n" +
            "        SELECT\n" +
            "            CITY,\n" +
            "            COUNT(*) AS OUTGOING_CALLS_COUNT\n" +
            "        FROM\n" +
            "            OUTGOING_CALLS\n" +
            "        GROUP BY\n" +
            "            CITY),\n" +
            "\n" +
            "    INCOMING_CALLS AS(\n" +
            "        SELECT\n" +
            "            LONG_DISTANCE_CALL_ID,\n" +
            "            DESTINATION_PHONE_ID,\n" +
            "            ADDRESS_ID,\n" +
            "            CITY\n" +
            "        FROM\n" +
            "            LONG_DISTANCE_CALLS CALLS\n" +
            "            JOIN PHONES P ON (CALLS.DESTINATION_PHONE_ID = P.PHONE_ID)\n" +
            "            JOIN ADDRESS ADDR USING (ADDRESS_ID)),\n" +
            "\n" +
            "    INCOMING_CALLS_COUNT AS(\n" +
            "        SELECT\n" +
            "            CITY,\n" +
            "            COUNT(*) AS INCOMING_CALLS_COUNT\n" +
            "        FROM\n" +
            "            INCOMING_CALLS\n" +
            "        GROUP BY\n" +
            "            CITY),\n" +
            "\n" +
            "     CALLS_COUNT AS(\n" +
            "         SELECT\n" +
            "            CITY,\n" +
            "            INCOMING_CALLS_COUNT + OUTGOING_CALLS_COUNT AS COUNT\n" +
            "        FROM\n" +
            "            OUTGOING_CALLS_COUNT JOIN INCOMING_CALLS_COUNT USING (CITY))\n" +
            "\n" +
            "SELECT\n" +
            "    CITY,\n" +
            "    COUNT AS CALLS_COUNT\n" +
            "FROM\n" +
            "    CALLS_COUNT\n" +
            "WHERE\n" +
            "    COUNT = (SELECT MAX(COUNT) FROM CALLS_COUNT)", nativeQuery = true)
    List<Object[]> findTownWithMaxCalls();

    @Query(value = "WITH\n" +
            "    FILTERED_CALLS AS (\n" +
            "        SELECT\n" +
            "            TELEPHONE_EXCHANGE_ID,\n" +
            "            LONG_DISTANCE_CALL_ID,\n" +
            "            SOURCE_PHONE_ID,\n" +
            "            START_DATE\n" +
            "        FROM\n" +
            "            LONG_DISTANCE_CALLS CALLS\n" +
            "            JOIN PHONES P ON CALLS.SOURCE_PHONE_ID = P.PHONE_ID\n" +
            "            JOIN PHONE_NUMBERS PN USING (PHONE_NUMBER_ID)\n" +
            "        WHERE\n" +
            "            TELEPHONE_EXCHANGE_ID = 1\n" +
            "            AND START_DATE BETWEEN Cast(:firstDate as datetime) AND cast(:secondDate as datetime)\n" +
            "),\n" +
            "    CALLS_COUNT AS(\n" +
            "        SELECT\n" +
            "            SOURCE_PHONE_ID,\n" +
            "            COUNT(*) AS CALLS_COUNT\n" +
            "        FROM\n" +
            "            FILTERED_CALLS\n" +
            "        GROUP BY\n" +
            "            SOURCE_PHONE_ID\n" +
            ")\n" +
            "\n" +
            "SELECT\n" +
            "    *\n" +
            "FROM\n" +
            "    CALLS_COUNT\n" +
            "WHERE\n" +
            "    CALLS_COUNT <= :callsCount", nativeQuery = true)
    List<Object[]> FindPhonesList(@Param("callsCount") Integer callsCount, @Param("firstDate")LocalDateTime firstDate,  @Param("secondDate")LocalDateTime secondDate);
}
