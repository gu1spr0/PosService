package com.pgt360.payment.service.dto.netty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class VentaDto {
    private String codAutorizacion;
    private float montoCompra;
    private String montoCompraTransaccion;
    private String numeroRecibo;
    private String rrn;
    private String terminalId;
    private String fechaTransaccion;
    private String horaTransaccion;
    private Date fechaLocal;
    private String codigoRespuesta;
    private String tipoCuenta;
    private String numeroCuotasTransaccion;
    private String tipoCuentaTransaccion;
    private int ultimosDigitos;
    private String ultimosDigitosTransasccion;
    private String mensajeError;
    private String binTarjetaTransaccion;
    private int binTarjeta;
}
