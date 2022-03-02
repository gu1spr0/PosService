package com.pgt360.payment.service.dto.dominio;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DominioUpdateDto {
    private String tipoDominio;
    private String codigoDominio;
    private String nombreDominio;
    private String descripcionDominio;
    private String estado;
}
