package com.pgt360.payment.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pg_dominios_valores")
public class DominioValor extends Base{
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dva_domcodigo")
    private Dominio dominio;

    @ApiModelProperty(notes = "Contiene descripción del codigo del Dominio valor")
    @NotNull(message = "El codigo valor no puede ser nulo")
    @Column(name = "dva_codigo_valor", length = 100)
    private String codigoValor;

    @ApiModelProperty(notes = "Describe el titulo del Dominio valor")
    @Column(name = "dva_titulo_descripcion", length = 300)
    private String tituloDescripcion;

    @ApiModelProperty(notes = "Contiene el valor caracter asignado al Dominio valor")
    @Column(name = "dva_valor_caracter", length = 300)
    private String valorCaracter;

    @ApiModelProperty(notes = "Contiene el valor numérico asignado al Dominio valor")
    @Column(name = "dva_valor_numerico")
    private int valorNumerico;

    @ApiModelProperty(notes = "Contiene el valor caracter extra asignado al Dominio valor")
    @Column(name = "dva_valor_caracter_extra", length = 300)
    private String valorCaracterExtra;

    @ApiModelProperty(notes = "Contiene el valor numérico extra asignado al Dominio Valor")
    @Column(name = "dva_valor_numerico_extra")
    private int valorNumericoExtra;
}
