package com.pgt360.payment.service.dto.dispositivo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class DispositivoQueryDto {
    private int id;
    private String nombre;
    private String modelo;
    private String ip;
    private String mac;
    private int mpk;
    private String pnr;
    private boolean multi;
    private Date fechaAlta;
    private int usuarioAlta;
    private String estado;
}
