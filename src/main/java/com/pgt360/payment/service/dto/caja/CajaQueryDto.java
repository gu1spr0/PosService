package com.pgt360.payment.service.dto.caja;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CajaQueryDto {
    private int id;
    private int numero;
    private String descripcion;
    private Integer idDispositivo;
    private Integer idSucursal;
    private Date fechaAlta;
    private int usuarioAlta;
    private String estado;
}
