package com.pgt360.payment.model.repository;

import com.pgt360.payment.model.entity.Sucursal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("SucursalRepository")
public interface SucursalRepository extends CrudRepository<Sucursal, Long> {
    @Query(value = "select count(s) from Sucursal s where s.estado=?1")
    Long getCountSucursalByState(String pEstado);

    @Query(value = "select s from Sucursal s where s.estado=?1")
    List<Sucursal> getSucursalPageableByState(String pEstado, Pageable pPageable);

    @Query(value = "select s from Sucursal s where s.estado=?1")
    List<Sucursal> getSucursalByState(String pEstado);

    @Query(value = "select s from Sucursal s where s.id = ?1 and s.estado=?2")
    Optional<Sucursal> getSucursalByIdAndState(long pConexionId, String pEstado);
}
