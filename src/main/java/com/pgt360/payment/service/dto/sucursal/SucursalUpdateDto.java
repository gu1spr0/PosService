package com.pgt360.payment.service.dto.sucursal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SucursalUpdateDto {
    private String nit;
    private Integer codigo;
    private String direccion;
    private String municipio;
    private String telefono;
    private Integer idComercio;
    private String estado;
}
