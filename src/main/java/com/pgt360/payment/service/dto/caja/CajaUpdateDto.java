package com.pgt360.payment.service.dto.caja;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CajaUpdateDto {
    private String estado;
    private int numeroCaja;
    private int comercio;
    private int dispositivo;
}
