package com.pgt360.payment.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "pg_flujos")
public class Flujo extends Base{
    @NotNull(message = "El código de flujo no puede ser nulo")
    @Column(name = "codigo_flujo")
    private int codigoFlujo;

    @NotNull(message = "La descripción del flujo no puede ser nulo")
    @Column(name = "descripcion", length = 40)
    private String descripcion;

    @Column(name = "paso")
    private int paso;

    @Column(name = "tamaño")
    private int tamaño;

    @Column(name = "respuesta", length = 500)
    private String respuesta;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_caja")
    private Caja caja;
}
