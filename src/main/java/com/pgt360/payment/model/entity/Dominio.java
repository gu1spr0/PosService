package com.pgt360.payment.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "pg_dominios")
public class Dominio extends Base {
    @NotNull(message = "El tipo de dominio no debe ser nulo")
    @Column(name = "dom_tipo_dominio", length = 30)
    private String tipoDominio;

    @NotNull(message = "El codigo de dominio no debe ser nulo")
    @Column(name = "dom_codigo_dominio", length = 100)
    private String codigoDominio;

    @NotNull(message = "El nombre de dominio no debe ser nulo")
    @Column(name = "dom_nombre_dominio", length = 100)
    private String nombreDominio;

    @NotNull(message = "La descripcion del dominio no debe ser nulo")
    @Column(name = "dom_descripcion", length = 300)
    private String descripcionDominio;

    @OneToMany(mappedBy = "dominio",fetch = FetchType.LAZY)
    private List<DominioValor> dominioValorList;
}
