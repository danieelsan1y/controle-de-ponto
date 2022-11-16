package com.controledeponto.application.repositories;

import com.controledeponto.application.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query("select r from Record r " +
            "where r.instantRecord " +
            "between :initialDate " +
            "and :finalDate " +
            "and r.person.id = :idPerson")
    public List<Record> findOneDay(@Param("initialDate") LocalDateTime initialDate,
                                   @Param("finalDate") LocalDateTime finalDate,
                                   @Param("idPerson") Long idPerson);
}
