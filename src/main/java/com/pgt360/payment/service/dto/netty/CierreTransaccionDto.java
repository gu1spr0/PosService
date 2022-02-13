package com.pgt360.payment.service.dto.netty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CierreTransaccionDto {
    private String codAutorizacion;
    private String codigoRespuesta;
}
