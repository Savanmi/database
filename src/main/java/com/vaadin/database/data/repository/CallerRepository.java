package com.vaadin.database.data.repository;

import com.vaadin.database.data.entity.Callers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CallerRepository extends CrudRepository<Callers, Integer> {

    @Query(value = "SELECT\n" +
            "    TELEPHONE_EXCHANGE_ID,\n" +
            "    SECOND_NAME,\n" +
            "    FIRST_NAME,\n" +
            "    MIDDLE_NAME,\n" +
            "    IS_DEADHEAD,\n" +
            "    AGE\n" +
            "FROM\n" +
            "    bd_gts.CALLERS CA\n" +
            "    JOIN bd_gts.CLIENTS CL USING (CLIENT_ID)\n" +
            "    JOIN bd_gts.CLIENTS_AGES CLA USING (CLIENT_ID)\n" +
            "    JOIN bd_gts.TELEPHONE_EXCHANGES TE USING (TELEPHONE_EXCHANGE_ID)\n" +
            "WHERE\n" +
            "    TE.TELEPHONE_EXCHANGE_ID = :texID \n" +
            "    AND (CL.IS_DEADHEAD = :id_deadhead)\n" +
            "    AND (CLA.AGE BETWEEN :lowAge AND :upAge)\n" +
            "    AND (SECOND_NAME = :second )\n", nativeQuery = true)
    List<Object[]> findCallersList(@Param("texID") Integer texID, @Param("id_deadhead") boolean id_deadhead,@Param("lowAge") Integer lowAge, @Param("upAge") Integer upAge, @Param("second") String second );

    @Query(value = "SELECT\n" +
            "        DISTINCT\n" +
            "        TELEPHONE_EXCHANGE_ID,\n" +
            "        CALLER_ID,\n" +
            "        IS_BLOCKED,\n" +
            "        SECOND_NAME, FIRST_NAME, MIDDLE_NAME,\n" +
            "        COALESCE(SUBSCRIPTION_DEBT, 0) AS SUBSCRIPTION_DEBT,\n" +
            "        COALESCE(LONG_DISTANCE_CALLS_DEBT, 0) AS LD_CALLS_DEBT,\n" +
            "        GET_SUBSCRIPTION_DEBT_AGE(CALLER_ID)  AS SUBSCRIPTION_DEBT_AGE,\n" +
            "        GET_LONG_DISTANCE_DEBT_AGE(CALLER_ID) AS LONG_DISTANCE_DEBT_AGE\n" +
            "    FROM\n" +
            "        CALLERS CA\n" +
            "        JOIN CLIENTS CL USING (CLIENT_ID)\n" +
            "        LEFT JOIN BALANCES B USING (CALLER_ID)\n" +
            "WHERE\n"+
            "SUBSCRIPTION_DEBT > :min_subscription_debt", nativeQuery = true)
    List<Object[]> findDebtorsListbySubscriptionDebt(@Param("min_subscription_debt") Integer in_subscription_debt);

    @Query(value = "SELECT\n" +
            "        DISTINCT\n" +
            "        TELEPHONE_EXCHANGE_ID,\n" +
            "        CALLER_ID,\n" +
            "        IS_BLOCKED,\n" +
            "        SECOND_NAME, FIRST_NAME, MIDDLE_NAME,\n" +
            "        COALESCE(SUBSCRIPTION_DEBT, 0) AS SUBSCRIPTION_DEBT,\n" +
            "        COALESCE(LONG_DISTANCE_CALLS_DEBT, 0) AS LD_CALLS_DEBT,\n" +
            "        GET_SUBSCRIPTION_DEBT_AGE(CALLER_ID)  AS SUBSCRIPTION_DEBT_AGE,\n" +
            "        GET_LONG_DISTANCE_DEBT_AGE(CALLER_ID) AS LONG_DISTANCE_DEBT_AGE\n" +
            "    FROM\n" +
            "        CALLERS CA\n" +
            "        JOIN CLIENTS CL USING (CLIENT_ID)\n" +
            "        LEFT JOIN BALANCES B USING (CALLER_ID)\n" +
            "WHERE\n"+
            "LONG_DISTANCE_CALLS_DEBT > :min_long_distance_debt", nativeQuery = true)
    List<Object[]> findDebtorsListbyLDCDebt(@Param("min_long_distance_debt") Integer min_long_distance_debt);

    @Query(value = "SELECT\n" +
            "        DISTINCT\n" +
            "        TELEPHONE_EXCHANGE_ID,\n" +
            "        CALLER_ID,\n" +
            "        IS_BLOCKED,\n" +
            "        SECOND_NAME, FIRST_NAME, MIDDLE_NAME,\n" +
            "        COALESCE(SUBSCRIPTION_DEBT, 0) AS SUBSCRIPTION_DEBT,\n" +
            "        COALESCE(LONG_DISTANCE_CALLS_DEBT, 0) AS LD_CALLS_DEBT,\n" +
            "        GET_SUBSCRIPTION_DEBT_AGE(CALLER_ID)  AS SUBSCRIPTION_DEBT_AGE,\n" +
            "        GET_LONG_DISTANCE_DEBT_AGE(CALLER_ID) AS LONG_DISTANCE_DEBT_AGE\n" +
            "    FROM\n" +
            "        CALLERS CA\n" +
            "        JOIN CLIENTS CL USING (CLIENT_ID)\n" +
            "        LEFT JOIN BALANCES B USING (CALLER_ID)\n" +
            "WHERE\n"+
            "GET_LONG_DISTANCE_DEBT_AGE(CALLER_ID) > :min_long_distance_debt_age", nativeQuery = true)
    List<Object[]> findDebtorsListbyLDCDebtAge(@Param("min_long_distance_debt_age") Integer min_long_distance_debt_age);

    @Query(value = "SELECT\n" +
            "        DISTINCT\n" +
            "        TELEPHONE_EXCHANGE_ID,\n" +
            "        CALLER_ID,\n" +
            "        IS_BLOCKED,\n" +
            "        SECOND_NAME, FIRST_NAME, MIDDLE_NAME,\n" +
            "        COALESCE(SUBSCRIPTION_DEBT, 0) AS SUBSCRIPTION_DEBT,\n" +
            "        COALESCE(LONG_DISTANCE_CALLS_DEBT, 0) AS LD_CALLS_DEBT,\n" +
            "        GET_SUBSCRIPTION_DEBT_AGE(CALLER_ID)  AS SUBSCRIPTION_DEBT_AGE,\n" +
            "        GET_LONG_DISTANCE_DEBT_AGE(CALLER_ID) AS LONG_DISTANCE_DEBT_AGE\n" +
            "    FROM\n" +
            "        CALLERS CA\n" +
            "        JOIN CLIENTS CL USING (CLIENT_ID)\n" +
            "        LEFT JOIN BALANCES B USING (CALLER_ID)\n" +
            "WHERE\n"+
            "GET_SUBSCRIPTION_DEBT_AGE(CALLER_ID) > :min_subscription_debt_age", nativeQuery = true)
    List<Object[]> findDebtorsListbySubscriptionDebtAge(@Param("min_subscription_debt_age") Integer min_subscription_debt_age);

    @Query(value = "SELECT\n" +
            "    DISTINCT\n" +
            "    CALLER_ID,\n" +
            "    SECOND_NAME, FIRST_NAME, MIDDLE_NAME,\n" +
            "    TELEPHONE_EXCHANGE_ID,\n" +
            "    EXCHANGE_TYPE,\n" +
            "    IS_DEADHEAD,\n" +
            "    REGION\n" +
            "FROM\n" +
            "    CALLERS CA\n" +
            "    JOIN PHONES USING (CALLER_ID)\n" +
            "    JOIN PHONE_TYPES USING (PHONE_TYPE_ID)\n" +
            "    JOIN CLIENTS CL USING (CLIENT_ID)\n" +
            "    JOIN CALLER_ADDRESSES USING (CALLER_ID)\n" +
            "    JOIN telephone_exchanges USING (TELEPHONE_EXCHANGE_ID)\n" +
            "WHERE\n" +
            "    TYPE_NAME = 'Параллельный' AND\n" +
            "    TELEPHONE_EXCHANGE_ID = :tex AND\n" +
            "    CL.IS_DEADHEAD = :deadhead AND\n" +
            "    REGION = :region \n" +
            "    \n" +
            "ORDER BY\n" +
            "    CALLER_ID", nativeQuery = true)
    List<Object[]> findCallerswithParallelPhones(@Param("tex") Integer tex,@Param("deadhead") boolean deadhead,@Param("region") String region );

    @Query(value = "SELECT\n" +
            "        DISTINCT\n" +
            "        TELEPHONE_EXCHANGE_ID,\n" +
            "        CALLER_ID,\n" +
            "        IS_BLOCKED,\n" +
            "        SECOND_NAME, FIRST_NAME, MIDDLE_NAME,\n" +
            "        COALESCE(SUBSCRIPTION_DEBT, 0) AS SUBSCRIPTION_DEBT,\n" +
            "        COALESCE(LONG_DISTANCE_CALLS_DEBT, 0) AS LD_CALLS_DEBT,\n" +
            "        GET_SUBSCRIPTION_DEBT_AGE(CALLER_ID)  AS SUBSCRIPTION_DEBT_AGE,\n" +
            "        GET_LONG_DISTANCE_DEBT_AGE(CALLER_ID) AS LONG_DISTANCE_DEBT_AGE\n" +
            "    FROM\n" +
            "        CALLERS CA\n" +
            "        JOIN CLIENTS CL USING (CLIENT_ID)\n" +
            "        LEFT JOIN BALANCES B USING (CALLER_ID)\n" +
            "WHERE\n" +
            "    IS_BLOCKED = 0\n" +
            "    AND(GET_LONG_DISTANCE_DEBT_AGE(CALLER_ID) > 5\n" +
            "        OR (GET_SUBSCRIPTION_DEBT_AGE(CALLER_ID) > 5))", nativeQuery = true)
    List<Object[]> findDebtorsToDisable();

    @Query(value = "SELECT\n" +
            "        DISTINCT\n" +
            "        TELEPHONE_EXCHANGE_ID,\n" +
            "        CALLER_ID,\n" +
            "        IS_BLOCKED,\n" +
            "        SECOND_NAME, FIRST_NAME, MIDDLE_NAME,\n" +
            "        COALESCE(SUBSCRIPTION_DEBT, 0) AS SUBSCRIPTION_DEBT,\n" +
            "        COALESCE(LONG_DISTANCE_CALLS_DEBT, 0) AS LD_CALLS_DEBT,\n" +
            "        GET_SUBSCRIPTION_DEBT_AGE(CALLER_ID)  AS SUBSCRIPTION_DEBT_AGE,\n" +
            "        GET_LONG_DISTANCE_DEBT_AGE(CALLER_ID) AS LONG_DISTANCE_DEBT_AGE\n" +
            "    FROM\n" +
            "        CALLERS CA\n" +
            "        JOIN CLIENTS CL USING (CLIENT_ID)\n" +
            "        LEFT JOIN BALANCES B USING (CALLER_ID)\n" +
            "WHERE\n" +
            "    IS_BLOCKED = 0\n" +
            "    AND ((GET_SUBSCRIPTION_DEBT_AGE(CALLER_ID) >= 0 AND\n" +
            "                GET_SUBSCRIPTION_DEBT_AGE(CALLER_ID) <= 5)\n" +
            "        OR (GET_LONG_DISTANCE_DEBT_AGE(CALLER_ID) >= 0 AND\n" +
            "                GET_LONG_DISTANCE_DEBT_AGE(CALLER_ID) <= 5))", nativeQuery = true)
    List<Object[]> findDebtorsToNotify();
}
