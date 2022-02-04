package com.pgt360.payment.model.repository;

import com.pgt360.payment.model.entity.Flujo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("FlujoRepository")
public interface FlujoRepository extends CrudRepository<Flujo, Long> {
    @Query(value = "select count(f) from Flujo f where f.estado=?1")
    Long getCountFlujoByState(String pEstado);

    @Query(value = "select f from Flujo f where f.estado=?1")
    List<Flujo> getFlujoPageableByState(String pEstado, Pageable pPageable);

    @Query(value = "select f from Flujo f where f.estado=?1")
    List<Flujo> getFlujoByState(String pEstado);

    @Query(value = "select f from Flujo f where f.id = ?1 and f.estado=?2")
    Optional<Flujo> getFlujoByIdAndState(long pFlujoId, String pEstado);

    @Query(value = "select f from Flujo f where f.id = ?1")
    Optional<Flujo> getFlujoById(long pFlujoId);
}
