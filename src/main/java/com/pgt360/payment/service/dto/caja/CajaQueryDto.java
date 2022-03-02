package com.pgt360.payment.service.dto.caja;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CajaQueryDto {
    private int id;
    private int correlativo;
    private String descripcion;
    private Date fechaAlta;
    private Integer usuarioAlta;
    private String estado;
}
