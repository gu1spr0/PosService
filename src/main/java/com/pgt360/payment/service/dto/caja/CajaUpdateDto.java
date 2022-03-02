package com.pgt360.payment.service.dto.caja;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CajaUpdateDto {
    private int correlativo;
    private String descripcion;
    private String estado;
}
