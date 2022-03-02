package com.pgt360.payment.service.dto.transaccion;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransaccionUpdateDto {
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
    private String estado;
}
