package com.pgt360.payment.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "pg_dominios_valores")
public class DominioValor extends Base{
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dva_domcodigo")
    private Dominio dominio;

    @NotNull(message = "El codigo valor no puede ser nulo")
    @Column(name = "dva_codigo_valor", length = 100)
    private String codigoValor;

    @Column(name = "dva_titulo_descripcion", length = 300)
    private String tituloDescripcion;

    @Column(name = "dva_valor_caracter", length = 300)
    private String valorCaracter;

    @Column(name = "dva_valor_numerico")
    private int valorNumerico;

    @Column(name = "dva_valor_caracter_extra", length = 300)
    private String valorCaracterExtra;

    @Column(name = "dva_valor_numerico_extra")
    private int valorNumericoExtra;
}
