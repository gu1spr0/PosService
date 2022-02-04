package com.pgt360.payment.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "pg_cajas")
public class Caja extends Base{
    @NotNull(message = "El número de caja no puede ser nulo")
    @Column(name = "numero_caja")
    private int numeroCaja;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_comercio")
    private Comercio comercio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dispositivo", referencedColumnName = "id")
    private Dispositivo dispositivo;

    @OneToMany(mappedBy = "caja",fetch = FetchType.LAZY)
    private List<Flujo> flujoList;
}
