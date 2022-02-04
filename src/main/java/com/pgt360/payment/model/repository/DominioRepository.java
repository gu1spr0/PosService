package com.pgt360.payment.model.repository;

import com.pgt360.payment.model.entity.Dominio;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("DominioRepository")
public interface DominioRepository extends CrudRepository<Dominio, Long> {
    @Query(value = "select count(d) from Dominio d where d.estado=?1")
    Long getCountDomainsByState(String pEstado);

    @Query(value = "select d from Dominio d where d.estado=?1")
    List<Dominio> getDomainsPageableByState(String pEstado, Pageable pPageable);

    @Query(value = "select d from Dominio d where d.estado=?1")
    List<Dominio> getDomainsByState(String pEstado);

    @Query(value = "select d from Dominio d where d.id = ?1 and d.estado=?2")
    Optional<Dominio> getDomainByIdAndState(long pDominioId, String pEstado);

    @Query(value = "select d from Dominio d where d.id = ?1")
    Optional<Dominio> getDomainById(long pDominioId);

    @Query(value = "select d from Dominio d where d.codigoDominio like ?1")
    Optional<Dominio> getDomainByCodeDomain(String pCodigoDominio);
}
