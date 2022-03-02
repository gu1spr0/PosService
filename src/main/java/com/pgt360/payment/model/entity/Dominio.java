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
@Table(name = "pg_dominios")
public class Dominio extends Base {

    @ApiModelProperty(notes = "Describe el tipo de dominio")
    @NotNull(message = "El tipo de dominio no debe ser nulo")
    @Column(name = "dom_tipo_dominio", length = 30)
    private String tipoDominio;

    @ApiModelProperty(notes = "Describe el codigo asignado al Dominio")
    @NotNull(message = "El codigo de dominio no debe ser nulo")
    @Column(name = "dom_codigo_dominio", length = 100)
    private String codigoDominio;

    @ApiModelProperty(notes = "Describe el nombre del dominio")
    @NotNull(message = "El nombre de dominio no debe ser nulo")
    @Column(name = "dom_nombre_dominio", length = 100)
    private String nombreDominio;

    @ApiModelProperty(notes = "Descripcion detallada del dominio")
    @NotNull(message = "La descripcion del dominio no debe ser nulo")
    @Column(name = "dom_descripcion", length = 300)
    private String descripcionDominio;

    @OneToMany(mappedBy = "dominio",fetch = FetchType.LAZY)
    private List<DominioValor> dominioValorList;
}
