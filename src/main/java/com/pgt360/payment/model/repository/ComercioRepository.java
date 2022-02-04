package com.pgt360.payment.model.repository;

import com.pgt360.payment.model.entity.Comercio;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("ComercioRepository")
public interface ComercioRepository extends CrudRepository<Comercio, Long> {
    @Query(value = "select count(c) from Comercio c where c.estado=?1")
    Long getCountComercioByState(String pEstado);

    @Query(value = "select c from Comercio c where c.estado=?1")
    List<Comercio> getComercioPageableByState(String pEstado, Pageable pPageable);

    @Query(value = "select c from Comercio c where c.estado=?1")
    List<Comercio> getComercioByState(String pEstado);

    @Query(value = "select c from Comercio c where c.id = ?1 and c.estado=?2")
    Optional<Comercio> getComercioByIdAndState(long pComercioId, String pEstado);

    @Query(value = "select c from Comercio c where c.id = ?1")
    Optional<Comercio> getComercioById(long pComercioId);
}
