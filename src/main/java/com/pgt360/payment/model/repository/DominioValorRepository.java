package com.pgt360.payment.model.repository;

import com.pgt360.payment.model.entity.DominioValor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("DominioValorRepository")
public interface DominioValorRepository extends CrudRepository<DominioValor, Long> {
    @Query(value = "select count(d) from DominioValor d where d.dominio.id=?1 AND d.estado=?2")
    Long getCountDomainsValuesByIdAndState(Long pDominioId, String pEstado);

    @Query(value = "select d from DominioValor d where d.dominio.id=?1 AND d.estado=?2")
    List<DominioValor> getDomainsValuesPageableByIdAndState(Long pDominioId, String pEstado, Pageable pPageable);

    @Query(value = "select d from DominioValor d where d.id = ?1")
    Optional<DominioValor> getDomainValueById(long pDominioValorId);

    @Query(value = "select d from DominioValor d where d.dominio.id = ?1")
    List<DominioValor> getDomainValueByDomainId(long pDominioId);

    @Query(value = "select d from DominioValor d where d.dominio.codigoDominio = ?1 and d.estado = ?2")
    List<DominioValor> getDomainValueByDomainCodeAndState(String pDominioCodigo, String pEstado);

    @Query(value = "select d from DominioValor d where d.dominio.codigoDominio = ?1 and d.valorCaracterExtra=?2 and d.estado = ?3")
    List<DominioValor> getDomainValueByDomainCodeAndCharValueExtraAndState(String pDominioCodigo, String pValorCaracterExtra, String pEstado);

    @Query(value = "select distinct d from DominioValor  d where d.dominio.id = ?1 and d.codigoValor = ?2")
    Optional<DominioValor> getDomainValueByDomainIdAndCodeValue(long pDominioId, String pCodigoValor);

    @Query(value = "select d from DominioValor d where d.dominio.codigoDominio=?1 and d.codigoValor=?2 and d.estado=?3")
    Optional<DominioValor> getDomainValueByDomainCodeAndCodeValue(String pDominioCodigo, String pCodigoValor, String pEstado);

    @Query(value = "select d from DominioValor d where d.dominio.codigoDominio=?1 and d.valorCaracter=?2 and d.estado=?3")
    Optional<DominioValor> getDomainValueByDomainCodeAndCharValue(String pDominioCodigo, String pCodigoValor, String pEstado);

    @Query(value = "select d from DominioValor d where d.dominio.codigoDominio = ?1 and d.valorCaracter=?2 and d.estado = ?3")
    List<DominioValor> getDomainValueByDomainCodeAndCharValueAndState(String pDominioCodigo, String pValorCaracter, String pEstado);

    @Query(value = "select d from DominioValor d where d.dominio.tipoDominio=?1 and d.estado = ?2")
    Optional<List<DominioValor>> getDomainValueByDomainAndState(String pTipoDominio, String pEstado);

    @Query(value = "select count(d) from DominioValor d where d.dominio.codigoDominio=?1 and d.estado=?2")
    Long getCountDomainsValuesByCodeValue(String pDominioCodigo, String pEstado);

    @Query(value = "select d from DominioValor d where d.id in ?1 order by d.valorCaracter")
    List<DominioValor> getDomainValuesByIds(Long[] pDominioValoIds);

    @Query(value = "select d from DominioValor d where d.dominio.codigoDominio=?1 and d.codigoValor in ?2 and d.estado=?3 order by d.valorCaracter")
    List<DominioValor> getDomainValuesByDomainAndCodeValues(String pDominioCodigo, String[] pCodigoValores, String pEstado);
}
