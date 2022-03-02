package com.pgt360.payment.model.repository;

import com.pgt360.payment.model.entity.Conexion;
import com.pgt360.payment.model.entity.Transaccion;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("TransaccionRepository")
public interface TransaccionRepository extends CrudRepository<Transaccion, Long> {
    @Query(value = "select count(t) from Transaccion t where t.estado=?1")
    Long getCountTransaccionByState(String pEstado);

    @Query(value = "select t from Transaccion t where t.estado=?1")
    List<Transaccion> getTransaccionPageableByState(String pEstado, Pageable pPageable);

    @Query(value = "select t from Transaccion t where t.estado=?1")
    List<Transaccion> getTransaccionByState(String pEstado);

    @Query(value = "select t from Transaccion t where t.id = ?1 and t.estado=?2")
    Optional<Transaccion> getTransaccionByIdAndState(long pConexionId, String pEstado);
}
