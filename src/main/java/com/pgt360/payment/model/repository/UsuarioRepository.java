package com.pgt360.payment.model.repository;

import com.pgt360.payment.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("UsuarioRepository")
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    @Query(value = "select count(u) from Usuario u where u.estado=?1")
    Long getCountUsersByState(String pEstado);

    @Query(value = "select u from Usuario u where u.estado=?1")
    List<Usuario> getUsersByState(String pEstado);

    @Query(value = "select u from Usuario u where u.username=?1 and u.fechaBaja is null and u.estado <> 'BL'")
    Optional<Usuario> getUserByUsername(String pUsuario);

    @Query(value = "select u from Usuario u where u.id = ?1")
    Optional<Usuario> getUserById(long pUsuarioId);

}
