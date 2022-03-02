package com.pgt360.payment.service.dto.comercio;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ComercioUpdateDto {
    private String nit;
    private String razonSocial;
    private String municipio;
    private String direccion;
    private String telefonoFijo;
    private String telefonoMovil;
    private String email;
    private String estado;
}
