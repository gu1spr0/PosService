package com.pgt360.payment.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "pg_dispositivos")
public class Dispositivo extends Base{
    @NotNull(message = "El nombre de dispositivo no puede ser nulo")
    @Column(name = "nombre", length = 100)
    private String name;

    @NotNull(message = "La descripcion de modelo no puede ser nulo")
    @Column(name = "modelo", length = 60)
    private String modelo;

    @Column(name = "ip", length = 15)
    private String ip;

    @Column(name = "mpk")
    private int mpk;

    @Column(name = "pnr", length = 20)
    private String pnr;

    @NotNull(message = "El código de flujo no puede ser nulo")
    @Column(name = "id_terminal", length = 20)
    private String idTerminal;

    @OneToOne(mappedBy = "dispositivo")
    private Caja caja;

    @OneToMany(mappedBy = "dispositivo",fetch = FetchType.LAZY)
    private List<Conexion> conexionList;
}
