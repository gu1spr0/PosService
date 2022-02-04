package com.pgt360.payment.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "pg_conexiones")
public class Conexion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "El id de canal no puede ser nulo")
    @Column(name = "idcanal", length = 20)
    private String idCanal;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dispositivo")
    private Dispositivo dispositivo;

    @Column(name = "fecha_conexion")
    @Temporal(TemporalType.DATE)
    private Date fechaConexion;

    @Column(name = "hora_conexion")
    @Temporal(TemporalType.TIME)
    private Date horaConexion;

    @Column(name = "fecha_desconexion")
    @Temporal(TemporalType.DATE)
    private Date fechaDesconexion;

    @Column(name = "hora_desconexion")
    @Temporal(TemporalType.TIME)
    private Date horaDesconexion;

    @NotNull(message = "El estado no puede ser nulo")
    @Column(name = "estado", length = 10)
    private String estado;
}
