package com.pgt360.payment.service.dto.dispositivo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DispositivoAddDto {
    private String nombre;
    private String modelo;
    private String ip;
    private String mac;
    private int mpk;
    private String pnr;
    private boolean multi;
}
