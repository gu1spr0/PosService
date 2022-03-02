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
@Table(name = "pg_comercios")
public class Comercio extends Base{

    @ApiModelProperty(notes = "Describe el nit del comercio")
    @NotNull(message = "El nit no puede ser nulo")
    @Column(name = "nit", length = 20)
    private String nit;


    @ApiModelProperty(notes = "Describe la razon social del comercio")
    @NotNull(message = "La razon social no puede ser nulo")
    @Column(name = "razon_social", length = 200)
    private String razonSocial;

    @ApiModelProperty(notes = "Describe el nombre del municipio")
    @Column(name = "municipio", length = 100)
    private String municipio;


    @ApiModelProperty(notes = "Describe la direccion del comercio")
    @NotNull(message = "La direccioin no puede ser nulo")
    @Column(name = "direccion", length = 150)
    private String direccion;


    @ApiModelProperty(notes = "Describe el número de telefono fijo del comercio")
    @NotNull(message = "El celular no puede ser nulo")
    @Column(name = "telefono_fijo", length = 15)
    private String telefonoFijo;

    @ApiModelProperty(notes = "Describe el número de telefono movil del comercio")
    @Column(name = "telefono_movil", length = 15)
    private String telefonoMovil;

    @ApiModelProperty(notes = "Describe el correo electronico del comercio")
    @Column(name = "email", length = 150)
    private String email;

    @OneToMany(mappedBy = "comercio",fetch = FetchType.LAZY)
    private List<Sucursal> sucursalList;
}
