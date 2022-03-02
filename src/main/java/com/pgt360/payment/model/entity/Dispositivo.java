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
@Table(name = "pg_dispositivos")
public class Dispositivo extends Base{
    @ApiModelProperty(notes = "Contiene descripción del nombre de dispositivo")
    @NotNull(message = "El nombre de dispositivo no puede ser nulo")
    @Column(name = "nombre", length = 100)
    private String nombre;

    @ApiModelProperty(notes = "Contiene descripción del modelo de dispositivo")
    @NotNull(message = "La descripcion de modelo no puede ser nulo")
    @Column(name = "modelo", length = 60)
    private String modelo;

    @ApiModelProperty(notes = "Contiene la dirección ip del dispositivo")
    @Column(name = "ip", length = 15)
    private String ip;

    @ApiModelProperty(notes = "Contiene el codigo unico de identificacion de dispositivo")
    @Column(name = "mac", length = 30)
    private String mac;

    @ApiModelProperty(notes = "Contiene codigo mpk del dispositivo")
    @Column(name = "mpk")
    private int mpk;

    @ApiModelProperty(notes = "Contiene codigo pnr del dispositivo")
    @Column(name = "pnr", length = 20)
    private String pnr;

    @ApiModelProperty(notes = "Describe el tipo de dispositivo")
    @Column(name = "multi")
    private boolean multi;

    @OneToMany(mappedBy = "dispositivo",fetch = FetchType.LAZY)
    private List<Conexion> conexionList;

    @OneToMany(mappedBy = "dispositivo",fetch = FetchType.LAZY)
    private List<Transaccion> transaccionList;

    @OneToOne(mappedBy = "dispositivo")
    private Caja caja;
}
