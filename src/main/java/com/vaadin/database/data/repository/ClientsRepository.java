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

    @Query(value = "WITH CLIENT_SELECTION AS(\n" +
            "    SELECT\n" +
            "        DISTINCT\n" +
            "        CALLER_ID,\n" +
            "        IS_DEADHEAD\n" +
            "    FROM\n" +
            "        CLIENTS CL\n" +
            "        JOIN CALLERS CA USING (CLIENT_ID)\n" +
            "        JOIN CALLER_ADDRESSES CADDR USING (CALLER_ID)\n" +
            "        JOIN telephone_exchanges ET USING (TELEPHONE_EXCHANGE_ID)\n" +
            "    WHERE\n" +
            "        TELEPHONE_EXCHANGE_ID = :tex AND\n" +
            "        CADDR.REGION = :region\n" +
            "),\n" +
            "CLIENT_COUNT AS (\n" +
            "    SELECT\n" +
            "        COUNT(*) AS COUNT\n" +
            "    FROM\n" +
            "        CLIENT_SELECTION\n" +
            "),\n" +
            "DEADHEAD_COUNT AS(\n" +
            "    SELECT\n" +
            "        COUNT(*) AS COUNT\n" +
            "    FROM\n" +
            "        CLIENT_SELECTION\n" +
            "    WHERE\n" +
            "        IS_DEADHEAD = 1\n" +
            ")\n" +
            "\n" +
            "SELECT\n" +
            "    CASE\n" +
            "        WHEN CC.COUNT = 0 THEN 0\n" +
            "        ELSE ROUND((DC.COUNT / CC.COUNT) * 100, 0)\n" +
            "    END as DEADHEAD_PERCENTAGE,\n" +
            "    CASE\n" +
            "        WHEN CC.COUNT = 0 THEN 0\n" +
            "        ELSE ROUND(((CC.COUNT - DC.COUNT) / CC.COUNT) * 100, 0)\n" +
            "    END as NOT_DEADHEAD_PERCENTAGE\n" +
            "FROM\n" +
            "    DEADHEAD_COUNT DC\n" +
            "    CROSS JOIN CLIENT_COUNT CC\n", nativeQuery = true)
    List<Object[]>findDeadheadPercentage(@Param("tex") Integer tex, @Param("region") String region);
}
