package com.pgt360.payment.service.dto.netty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestDto {
    private int idComercio;
    private int flujo;
    private String strFlujo;
    private int confirm;
    private String montoTransaccion;
    private float monto;
    private int paso;
    private int tamaño;
    private String referenciaAnulacion;
}
