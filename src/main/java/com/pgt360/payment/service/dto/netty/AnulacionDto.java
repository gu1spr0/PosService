package com.pgt360.payment.service.dto.netty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnulacionDto {
    /*private String codAutorizacion;
    private float montoCompra;
    private String numeroRecibo;
    private String rrn;
    private String terminalId;
    private String fechaTransaccion;
    private String horaTransaccion;
    private String codigoRespuesta;
    private int ultimosDigitos;
    private String mensajeError;
    private int binTarjeta;*/
    private String codAutorizacion;
    private String montoCompra;
    private String numeroRecibo;
    private String rrn;
    private String terminalId;
    private String fechaTransaccion;
    private String horaTransaccion;
    private String codigoRespuesta;
    private String ultimosDigitos;
    private String mensajeError;
    private String binTarjeta;
}
