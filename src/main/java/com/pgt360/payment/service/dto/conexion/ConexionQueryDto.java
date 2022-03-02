package com.pgt360.payment.service.dto.conexion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class ConexionQueryDto {
    private int id;
    private String idCanal;
    private Integer idDispositivo;
    private Date fechaConexion;
    private Date fechaDesconexion;
    private String estado;
}
