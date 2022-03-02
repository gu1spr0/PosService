package com.pgt360.payment.service.dto.conexion;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ConexionUpdateDto {
    private String idCanal;
    private Integer idDispositivo;
    private Date fechaConexion;
    private Date fechaDesconexion;
    private String estado;
}
