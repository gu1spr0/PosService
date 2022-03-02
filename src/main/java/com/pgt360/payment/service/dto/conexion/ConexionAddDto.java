package com.pgt360.payment.service.dto.conexion;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class ConexionAddDto {
    private String idCanal;
    private int idDispositivo;
    private Date fechaConexion;
    private Date horaConexion;
    private Date fechaDesconexion;
    private Date horaDesconexion;
    private String estado;
}
