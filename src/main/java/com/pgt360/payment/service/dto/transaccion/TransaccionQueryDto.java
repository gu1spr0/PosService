package com.pgt360.payment.service.dto.transaccion;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TransaccionQueryDto {
    private int id;
    private String codigoAutorizacion;
    private Float montoCompra;
    private Integer numeroRecivo;
    private String rrn;
    private String idTerminal;
    private String fechaTransaccion;
    private String horaTransaccion;
    private String codigoRespuesta;
    private String tipoCuenta;
    private int numeroCuotas;
    private int ultimosDigitos;
    private String mensajeError;
    private int binTarjeta;
    private Integer tipo;
    private String estadoTransaccion;
    private Integer idDispositivo;
    private Date fechaAlta;
    private int usuarioAlta;
    private String estado;
}
