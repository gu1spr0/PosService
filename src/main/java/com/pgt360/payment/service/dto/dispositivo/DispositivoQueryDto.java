package com.pgt360.payment.service.dto.dispositivo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DispositivoQueryDto {
    private int id;
    private String name;
    private String modelo;
    private String ip;
    private String mac;
    private int mpk;
    private String pnr;
    private String idTerminal;
    private int caja;
    private String estado;
}
