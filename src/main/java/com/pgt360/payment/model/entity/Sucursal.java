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
@Table(name = "pg_sucursales")
public class Sucursal extends Base{
    @ApiModelProperty(notes = "Describe el nit de la sucursal")
    @NotNull(message = "El nit no puede ser nulo")
    @Column(name = "nit", length = 20)
    private String nit;

    @ApiModelProperty(notes = "Describe codigo de la sucursal")
    @NotNull(message = "El codigo de sucursal no puede ser nulo")
    @Column(name = "codigo")
    private Integer codigo;

    @ApiModelProperty(notes = "Contiene la direccion de la sucursal")
    @Column(name = "direccion")
    private String direccion;

    @ApiModelProperty(notes = "Describe el nombre de municipio")
    @Column(name = "municipio", length = 100)
    private String municipio;

    @ApiModelProperty(notes = "Describe el número de teléfono de la sucursal")
    @NotNull(message = "El nit no puede ser nulo")
    @Column(name = "telefono", length = 20)
    private String telefono;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_comercio")
    private Comercio comercio;

    @OneToMany(mappedBy = "sucursal",fetch = FetchType.LAZY)
    private List<Caja> cajaList;
}
