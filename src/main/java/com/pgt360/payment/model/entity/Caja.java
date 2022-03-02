package com.pgt360.payment.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pg_cajas")
public class Caja extends Base{
    @ApiModelProperty(notes = "Describe el numero de caja")
    @NotNull(message = "El numero de caja no puede ser nulo")
    @Column(name = "numero")
    private int numero;

    @ApiModelProperty(notes = "Descripcion de la caja registrada")
    @Column(name = "descripcion", length = 300)
    private String descripcion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dispositivo", referencedColumnName = "id")
    private Dispositivo dispositivo;

    @OneToMany(mappedBy = "caja",fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;
}