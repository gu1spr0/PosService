package com.pgt360.payment.service.dto.dominio;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DominioAddDto {
    private String tipoDominio;
    private String codigoDominio;
    private String nombreDominio;
    private String descripcionDominio;
    private String estado;
}
