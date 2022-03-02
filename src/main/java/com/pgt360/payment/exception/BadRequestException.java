package com.pgt360.payment.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    @Getter
    private String codigo;

    public BadRequestException(String pCodigo){
        super("Error en la solicitud");
        this.codigo = pCodigo;
    }
    public BadRequestException(String pCodigo, String pMensaje){
        super(pMensaje);
        this.codigo = pCodigo;
    }

}
