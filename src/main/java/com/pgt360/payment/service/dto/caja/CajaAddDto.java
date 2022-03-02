package com.pgt360.payment.service.dto.caja;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CajaAddDto {
    private int numero;
    private String descripcion;
    private Integer idDispositivo;
    private Integer idSucursal;
    private String estado;
}
