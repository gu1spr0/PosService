package com.pgt360.payment.model.entity;

import com.pgt360.payment.util.Constants;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "Contiene identificador unico del registro")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ApiModelProperty(notes = "Contiene la fecha de alta del registro")
    @PastOrPresent(message = "La fecha de alta del registro debe ser actual")
    @NotNull(message = "La fecha de alta del registro no debe ser nula")
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @ApiModelProperty(notes = "Contiene id del usuario que dio de alta al registro")
    @NotNull(message = "El usuario que dió de alta el registro no debe ser nula")
    @Column(name = "usuario_alta")
    private int usuarioAlta;

    @ApiModelProperty(notes = "Contiene la fecha de baja del registro")
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;

    @ApiModelProperty(notes = "Contiene id del usuario que dio de baja el registro")
    @Column(name = "usuario_baja")
    private Integer usuarioBaja;

    @ApiModelProperty(notes = "Contiene la fecha de modificacion del registro")
    @Column(name = "fecha_modificacion", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @ApiModelProperty(notes = "Contiene id del usuario que modificó el registro")
    @Column(name = "usuario_modificacion", nullable = true)
    private Integer usuarioModificacion;

    @ApiModelProperty(notes = "Describe el estado actual del registro")
    @NotNull(message = "El estado no puede ser nulo")
    @Column(name = "estado")
    private String estado;

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        if (fechaAlta == null) {
            fechaAlta = now;
            usuarioAlta = 0;
            estado = Constants.STATE_ACTIVE;
        }
    }

    @PreUpdate
    public void preUpdate(){
        //lastModifiedBy = Security.getUserOfAuthenticatedUser();
        usuarioModificacion = 0;
        fechaModificacion = new Date();
        if  (fechaBaja != null){
            usuarioBaja = 0;
            estado = Constants.STATE_DELETED;
        }
    }
}
