package com.pgt360.payment.model.repository;

import com.pgt360.payment.model.entity.Conexion;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("ConexionRepository")
public interface ConexionRepository extends CrudRepository<Conexion, Long> {
    @Query(value = "select count(c) from Conexion c where c.estado=?1")
    Long getCountConexionByState(String pEstado);

    @Query(value = "select c from Conexion c where c.estado=?1")
    List<Conexion> getConexionPageableByState(String pEstado, Pageable pPageable);

    @Query(value = "select c from Conexion c where c.estado=?1")
    List<Conexion> getConexionByState(String pEstado);

    @Query(value = "select c from Conexion c where c.id = ?1 and c.estado=?2")
    Optional<Conexion> getConexionByIdAndState(long pConexionId, String pEstado);

    @Query(value = "select c from Conexion c where c.idCanal = ?1 and c.estado=?2")
    Optional<Conexion> getConexioByIdCanalAndEstado(String pCanalId, String pEstado);

    @Query(value = "select c from Conexion c where c.id = ?1")
    Optional<Conexion> getConexionById(long pConexionId);
}
