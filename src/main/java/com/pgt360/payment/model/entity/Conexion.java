package com.pgt360.payment.model.entity;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "Describe el codigo único de identificación de registro")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ApiModelProperty(notes = "Describe el id del canal de conexion")
    @NotNull(message = "El id de canal no puede ser nulo")
    @Column(name = "idcanal", length = 20)
    private String idCanal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_dispositivo")
    private Dispositivo dispositivo;

    @ApiModelProperty(notes = "Describe la fecha de conexion del dispositivo")
    @Column(name = "fecha_conexion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConexion;

    @ApiModelProperty(notes = "Describe la fecha de desconexion del dispositivo")
    @Column(name = "fecha_desconexion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesconexion;

    @ApiModelProperty(notes = "Describe el estado actual del registro")
    @NotNull(message = "El estado no puede ser nulo")
    @Column(name = "estado", length = 10)
    private String estado;
}
