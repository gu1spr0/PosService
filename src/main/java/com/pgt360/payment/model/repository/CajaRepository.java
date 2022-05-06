package com.pgt360.payment.model.repository;

import com.pgt360.payment.model.entity.Caja;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("CajaRepository")
public interface CajaRepository extends CrudRepository<Caja, Long> {
    @Query(value = "select count(c) from Caja c where c.estado=?1")
    Long getCountCajaByState(String pEstado);

    @Query(value = "select c from Caja c where c.estado=?1")
    List<Caja> getCajaPageableByState(String pEstado, Pageable pPageable);

    @Query(value = "select c from Caja c where c.estado=?1 and c.sucursal.id=?2")
    List<Caja> getCajaByStateAndSucursal(String pEstado, int pSucursalId, Pageable pPageable);

    @Query(value = "select c from Caja c where c.estado=?1 and c.sucursal.comercio=?2")
    List<Caja> getCajaPageableByStateAndComercio(String pEstado, int pComercioId, Pageable pPageable);

    @Query(value = "select c from Caja c where c.estado=?1")
    List<Caja> getCajaByState(String pEstado);

    @Query(value = "select c from Caja c where c.id = ?1 and c.estado=?2")
    Optional<Caja> getCajaByIdAndState(long pCajaId, String pEstado);
}
