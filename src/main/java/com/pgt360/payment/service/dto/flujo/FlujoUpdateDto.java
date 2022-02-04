package com.pgt360.payment.service.dto.flujo;

import com.pgt360.payment.model.entity.Caja;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlujoUpdateDto {
    private int codigoFlujo;
    private String descripcion;
    private int paso;
    private int tamaño;
    private String respuesta;
    private int caja;
    private String estado;
}
