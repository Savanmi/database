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

}
