package com.pgt360.payment.service.dto.caja;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class CajaAddDto {
    private int correlativo;
    private String descripcion;
    private String estado;
}
