package com.pgt360.payment.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pg_transacciones")
public class Transaccion extends Base{

    @ApiModelProperty(notes = "Describe el código de autorización de la transacción")
    @Column(name = "codigo_autorizacion", length = 40)
    private String codigoAutorizacion;

    @ApiModelProperty(notes = "Detalla en monto de compara de la transacción")
    @Column(name = "monto_compra",precision = 2)
    private Float montoCompra;

    @ApiModelProperty(notes = "Describe el número de recibo del pago")
    @Column(name = "numero_recibo")
    private Integer numeroRecivo;

    @ApiModelProperty(notes = "Describe el valor rrn de la transacción")
    @Column(name = "rrn", length = 20)
    private String rrn;

    @ApiModelProperty(notes = "Contiene el id de terminal")
    @Column(name = "id_terminal", length = 20)
    private String idTerminal;

    @ApiModelProperty(notes = "Describe la fecha de transacción")
    @Column(name = "fecha_transaccion", length = 5)
    private String fechaTransaccion;

    @ApiModelProperty(notes = "Describe la hora de transacción")
    @Column(name = "hora_transaccion", length = 5)
    private String horaTransaccion;

    @ApiModelProperty(notes = "Contiene el código de respuesta de la transacción")
    @Column(name = "codigo_respuesta", length = 20)
    private String codigoRespuesta;

    @ApiModelProperty(notes = "Describe el tipo de cuenta de la transacción")
    @Column(name = "tipo_cuenta", length = 10)
    private String tipoCuenta;

    @ApiModelProperty(notes = "Contiene el número de cuotas para la transacción")
    @Column(name = "numero_cuotas")
    private int numeroCuotas;

    @ApiModelProperty(notes = "Describe los ultimos 4 digitos de la tarjeta")
    @Column(name = "ultimos_digitos")
    private int ultimosDigitos;

    @ApiModelProperty(notes = "Contiene el mensaje de error correspondiente a la transacción")
    @Column(name = "mensaje_error", length = 300)
    private String mensajeError;

    @ApiModelProperty(notes = "Describe el valor bin de la tarjeta para la transacción")
    @Column(name = "bin_tarjeta")
    private int binTarjeta;

    @ApiModelProperty(notes = "Describe el tipo de transacción realizado")
    @Column(name = "tipo")
    private Integer tipo;

    @ApiModelProperty(notes = "Contiene el estadoa actual de la transacción")
    @Column(name = "estado_transaccion", length = 10)
    private String estadoTransaccion;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dispositivo")
    private Dispositivo dispositivo;
}
