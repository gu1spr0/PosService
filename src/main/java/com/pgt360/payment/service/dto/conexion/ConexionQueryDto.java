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
    private int dispositivo;
    private Date fechaConexion;
    private Date horaConexion;
    private Date fechaDesconexion;
    private Date horaDesconexion;
    private String estado;
}
