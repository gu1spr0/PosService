package com.pgt360.payment.service.dto.sucursal;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SucursalQueryDto {
    private int id;
    private String nit;
    private Integer codigo;
    private String direccion;
    private String municipio;
    private String telefono;
    private Integer idComercio;
    private Date fechaAlta;
    private int usuarioAlta;
    private String estado;
}
