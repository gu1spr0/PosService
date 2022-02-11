package com.pgt360.payment.model.entity;

import com.pgt360.payment.util.ConstantsUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @PastOrPresent(message = "La fecha de alta del registro debe ser actual")
    @NotNull(message = "La fecha de alta del registro no debe ser nula")
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @NotNull(message = "El usuario que dió de alta el registro no debe ser nula")
    @Column(name = "usuario_alta")
    private Integer usuarioAlta;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;

    @Column(name = "usuario_baja")
    private Integer usuarioBaja;

    @Column(name = "fecha_modificacion", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Column(name = "usuario_modificacion", nullable = true)
    private Integer usuarioModificacion;

    @NotNull(message = "El estado no puede ser nulo")
    @Column(name = "estado")
    private String estado;

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        if (fechaAlta == null) {
            fechaAlta = now;
            usuarioAlta = 0;
            estado = ConstantsUtil.STATE_ACTIVE;
        }
    }

    @PreUpdate
    public void preUpdate(){
        //lastModifiedBy = Security.getUserOfAuthenticatedUser();
        usuarioModificacion = 0;
        fechaModificacion = new Date();
        if  (fechaBaja != null){
            usuarioBaja = 0;
            estado = ConstantsUtil.STATE_DELETED;
        }
    }
}
