package com.pgt360.payment.service.dto.comercio;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class ComercioQueryDto {
    private int id;
    private String nit;
    private String razonSocial;
    private String municipio;
    private String direccion;
    private String telefonoFijo;
    private String telefonoMovil;
    private String email;
    private String sitioWeb;
    private Date fechaAlta;
    private int usuarioAlta;
    private String estado;
}
