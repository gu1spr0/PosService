package com.pgt360.payment.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private String codigo;
    private String mensaje;
    private String detalle;
    public ExceptionResponse(String pCodigo, String pMensaje, String pDetalle){
        super();
        this.codigo = pCodigo;
        this.mensaje = pMensaje;
        this.detalle = pDetalle;
    }
}
