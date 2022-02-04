package com.pgt360.payment.model.repository;

import com.pgt360.payment.model.entity.Conexion;
import com.pgt360.payment.model.entity.Dispositivo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("DispositivoRepository")
public interface DispositivoRepository extends CrudRepository<Dispositivo, Long> {
    @Query(value = "select count(d) from Dispositivo d where d.estado=?1")
    Long getCountDispositivoByState(String pEstado);

    @Query(value = "select d from Dispositivo d where d.estado=?1")
    List<Dispositivo> getDispositivoPageableByState(String pEstado, Pageable pPageable);

    @Query(value = "select d from Dispositivo d where d.estado=?1")
    List<Dispositivo> getDospositivoByState(String pEstado);

    @Query(value = "select d from Dispositivo d where d.id = ?1 and d.estado=?2")
    Optional<Dispositivo> getDispositivoByIdAndState(long pDispositivoId, String pEstado);

    @Query(value = "select d from Dispositivo d where d.id = ?1")
    Optional<Dispositivo> getDispositivoById(long pDispositivoId);
}
