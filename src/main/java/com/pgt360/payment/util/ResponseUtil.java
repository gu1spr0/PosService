package com.pgt360.payment.util;

import com.pgt360.payment.service.dto.netty.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseUtil {
    public static VentaDto getRespuestaHostVenta(String pRespuestaHost){
        VentaDto vVentaDto = new VentaDto();
        String vCodigoAutorizacion = pRespuestaHost.substring(Constants.BEGIN_COD_AUTORIZACION, Constants.BEGIN_COD_AUTORIZACION+Constants.TAM_COD_AUTORIZACION);
        String vMontoCompra = pRespuestaHost.substring(Constants.BEGIN_MONTO_COMPRA, Constants.BEGIN_MONTO_COMPRA+Constants.TAM_MONTO_COMPRA);
        String vNumeroRecibo = pRespuestaHost.substring(Constants.BEGIN_NUM_RECIBO, Constants.BEGIN_NUM_RECIBO+Constants.TAM_NUM_RECIBO);
        String vRrn = pRespuestaHost.substring(Constants.BEGIN_RRN, Constants.BEGIN_RRN+Constants.TAM_RRN);
        String vTerminalId = pRespuestaHost.substring(Constants.BEGIN_TERMINAL_ID, Constants.BEGIN_TERMINAL_ID+Constants.TAM_TERMINAL_ID);
        String vFechaTransaccion = pRespuestaHost.substring(Constants.BEGIN_FECHA_TRANSAC, Constants.BEGIN_FECHA_TRANSAC+Constants.TAM_FECHA_TRANSAC);
        String vHoraTransaccion = pRespuestaHost.substring(Constants.BEGIN_HORA_TRANSAC, Constants.BEGIN_HORA_TRANSAC+Constants.TAM_HORA_TRANSAC);
        String vCodigoRespuesta = pRespuestaHost.substring(Constants.BEGIN_COD_RESPUESTA, Constants.BEGIN_COD_RESPUESTA+Constants.TAM_COD_RESPUESTA);
        String vTipoCuenta = pRespuestaHost.substring(Constants.BEGIN_TIPO_CUENTA, Constants.BEGIN_TIPO_CUENTA+Constants.TAM_TIPO_CUENTA);
        String vNumeroCuotas = pRespuestaHost.substring(Constants.BEGIN_NUM_CUOTAS, Constants.BEGIN_NUM_CUOTAS+Constants.TAM_NUM_CUOTAS);
        String vUltimosDigitos = pRespuestaHost.substring(Constants.BEGIN_ULT_DIGITOS, Constants.BEGIN_ULT_DIGITOS+Constants.TAM_ULT_DIGITOS);
        String vMensajeError = pRespuestaHost.substring(Constants.BEGIN_MSG_ERROR, Constants.BEGIN_MSG_ERROR+Constants.TAM_MSG_ERROR);
        String vBinTarjeta = pRespuestaHost.substring(Constants.BEGIN_BIN_TARJETA, Constants.BEGIN_BIN_TARJETA+Constants.TAM_BIN_TARJETA);

        vVentaDto.setCodAutorizacion(NettyUtil.hex2a(vCodigoAutorizacion));
        vVentaDto.setMontoCompraTransaccion(NettyUtil.hex2a(vMontoCompra));
        vVentaDto.setNumeroRecibo(NettyUtil.hex2a(vNumeroRecibo));
        vVentaDto.setRrn(NettyUtil.hex2a(vRrn));
        vVentaDto.setTerminalId(NettyUtil.hex2a(vTerminalId));
        vVentaDto.setFechaTransaccion(NettyUtil.formatearFecha(NettyUtil.hex2a(vFechaTransaccion)));
        vVentaDto.setHoraTransaccion(NettyUtil.formatearHora(NettyUtil.hex2a(vHoraTransaccion)));
        //TODO: Cargar fecha actual sistema (VERIFICAR FECHA INEXACTA)
        vVentaDto.setCodigoRespuesta(NettyUtil.hex2a(vCodigoRespuesta));
        vVentaDto.setTipoCuenta(NettyUtil.hex2a(vTipoCuenta));
        vVentaDto.setUltimosDigitos(Integer.parseInt(NettyUtil.hex2a(vUltimosDigitos)));
        vVentaDto.setNumeroCuotasTransaccion(NettyUtil.hex2a(vNumeroCuotas));
        vVentaDto.setUltimosDigitosTransasccion(NettyUtil.hex2a(vUltimosDigitos));
        vVentaDto.setMensajeError(NettyUtil.cleanData(NettyUtil.hex2a(vMensajeError)));
        vVentaDto.setBinTarjeta(Integer.parseInt(NettyUtil.hex2a(vBinTarjeta)));
        vVentaDto.setBinTarjetaTransaccion(NettyUtil.hex2a(vBinTarjeta));

        return vVentaDto;
    }

    public static AnulacionDto getRespuestaAnulacion(String pRespuestaHost){
        AnulacionDto vAnulacionDto = new AnulacionDto();
        String vCodigoAutorizacion = pRespuestaHost.substring(Constants.AN_IAUTORIZACION, Constants.AN_IAUTORIZACION+Constants.AN_LAUTORIZACION);
        String vMontoCompra = pRespuestaHost.substring(Constants.AN_ICOMPRA, Constants.AN_ICOMPRA+Constants.AN_LCOMPRA);
        String vNumeroRecibo = pRespuestaHost.substring(Constants.AN_IRECIBO, Constants.AN_IRECIBO+Constants.AN_LRECIBO);
        String vRrn = pRespuestaHost.substring(Constants.AN_IRRN, Constants.AN_IRRN+Constants.AN_LRRN);
        String vTerminalId = pRespuestaHost.substring(Constants.AN_ITERMINAL, Constants.AN_ITERMINAL+Constants.AN_LTERMINAL);
        String vFechaTransaccion = pRespuestaHost.substring(Constants.AN_IFECHA, Constants.AN_IFECHA+Constants.AN_LFECHA);
        String vHoraTransaccion = pRespuestaHost.substring(Constants.AN_IHORA, Constants.AN_IHORA+Constants.AN_LHORA);
        String vCodigoRespuesta = pRespuestaHost.substring(Constants.AN_IRESPUESTA, Constants.AN_IRESPUESTA+Constants.AN_LRESPUESTA);
        String vUltimosDigitos = pRespuestaHost.substring(Constants.AN_IDIGITOS, Constants.AN_IDIGITOS+Constants.AN_LDIGITOS);
        String vMensajeError = pRespuestaHost.substring(Constants.AN_IERROR, Constants.AN_IERROR+Constants.AN_LERROR);
        String vBinTarjeta = pRespuestaHost.substring(Constants.AN_IBIN, Constants.AN_IBIN+Constants.AN_LBIN);

        vAnulacionDto.setCodAutorizacion(NettyUtil.hex2a(vCodigoAutorizacion));
        //vAnulacionDto.setMontoCompra(Float.parseFloat(NettyUtil.hex2a(vMontoCompra)));
        vAnulacionDto.setMontoCompra(NettyUtil.hex2a(vMontoCompra));
        vAnulacionDto.setNumeroRecibo(NettyUtil.hex2a(vNumeroRecibo));
        vAnulacionDto.setRrn(NettyUtil.hex2a(vRrn));
        vAnulacionDto.setTerminalId(NettyUtil.hex2a(vTerminalId));
        vAnulacionDto.setFechaTransaccion(NettyUtil.hex2a(vFechaTransaccion));
        vAnulacionDto.setHoraTransaccion(NettyUtil.hex2a(vHoraTransaccion));
        vAnulacionDto.setCodigoRespuesta(NettyUtil.hex2a(vCodigoRespuesta));
        vAnulacionDto.setUltimosDigitos(NettyUtil.hex2a(vUltimosDigitos));
        //vAnulacionDto.setUltimosDigitos(Integer.parseInt(NettyUtil.hex2a(vUltimosDigitos)));
        vAnulacionDto.setMensajeError(NettyUtil.cleanData(NettyUtil.hex2a(vMensajeError)));
        vAnulacionDto.setBinTarjeta(NettyUtil.hex2a(vBinTarjeta));
        //vAnulacionDto.setBinTarjeta(Integer.parseInt(NettyUtil.hex2a(vBinTarjeta)));

        return vAnulacionDto;
    }

    public static CierreTransaccionDto getRespuestaCierreTransaccion(String pRespuestaHost){
        CierreTransaccionDto vCierreTransaccionDto = new CierreTransaccionDto();
        String vCodigoAutorizacion = pRespuestaHost.substring(Constants.CT_IAUTORIZACION, Constants.CT_IAUTORIZACION+Constants.CT_LAUTORIZACION);
        String vCodigoRespuesta = pRespuestaHost.substring(Constants.CT_IRESPUESTA, Constants.CT_IRESPUESTA+Constants.CT_LRESPUESTA);

        vCierreTransaccionDto.setCodAutorizacion(NettyUtil.hex2a(vCodigoAutorizacion));
        if(!vCierreTransaccionDto.getCodAutorizacion().isEmpty()){
            vCierreTransaccionDto.setCodAutorizacion("-");
        }
        vCierreTransaccionDto.setCodigoRespuesta(NettyUtil.hex2a(vCodigoRespuesta));

        return vCierreTransaccionDto;
    }

    public static CierreDto getRespuestaCierre(String pRespuestaHost){
        CierreDto vCierreDto = new CierreDto();
        vCierreDto.setRespuest("Lote sin transacciones");
        return vCierreDto;
    }

    public static InicializacionDto getRespuestaInicializacion(String pRespuestaHost){
        InicializacionDto vInicializacionDto = new InicializacionDto();
        String vCodigoRespuesta = pRespuestaHost.substring(Constants.IN_IRESPUESTA, Constants.IN_IRESPUESTA+Constants.IN_LRESPUESTA);

        vInicializacionDto.setCodigoRespuesta(NettyUtil.hex2a(vCodigoRespuesta));

        return vInicializacionDto;
    }
}
