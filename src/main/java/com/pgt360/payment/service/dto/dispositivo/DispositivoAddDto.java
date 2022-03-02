package com.pgt360.payment.service.dto.dispositivo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DispositivoAddDto {
    private String name;
    private String modelo;
    private String ip;
    private int mpk;
    private String pnr;
    private String idTerminal;
    private int caja;
    private String estado;
}
