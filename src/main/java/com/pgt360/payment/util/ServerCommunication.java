package com.pgt360.payment.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServerCommunication {
    private static final Logger log = LoggerFactory.getLogger(ServerCommunication.class);
    public static String sendAck(ChannelHandlerContext ctx){
        log.info("*******************");
        log.info("***ENVIANDO ACK***");
        log.info("*******************");
        String msg = "06";
        sendMessageToPOS(ctx, msg);
        return msg;
    }
    public static String sendConnectionChip(ChannelHandlerContext pCtx){
        log.info("*******************");
        log.info("***ENVIANDO CHIP***");
        log.info("*******************");
        String msg = "02001736303030303030303030313030303030300323";
        sendMessageToPOS(pCtx, msg);
        return msg;
    }
    public static String sendConnectionChipMulticom(ChannelHandlerContext pCtx, String pComercioId){
        log.info("****************************");
        log.info("***ENVIANDO CHIP MULTICOM***");
        log.info("****************************");
        String vComercioId = "00" + pComercioId;
        vComercioId = vComercioId.substring(vComercioId.length()-2);
        String inicio = "002436303030303030303030313030303030301C";
        String comercio = "37390002" + NettyUtil.asciiToHex(vComercioId);
        String trama = inicio + comercio + "03";
        String xo = NettyUtil.xor(NettyUtil.hex2a(trama));
        String tramaFinal = "02" + trama + xo;
        sendMessageToPOS(pCtx, tramaFinal);
        return tramaFinal;
    }
    public static String sendConnectionCtl(ChannelHandlerContext pCtx){
        log.info("**************************");
        log.info("***ENVIANDO CONTACTLESS***");
        log.info("**************************");
        String msg = "02001736303030303030303030313030363030300325";
        sendMessageToPOS(pCtx, msg);
        return msg;
    }
    public static String sendConnectionCtlMulticom(ChannelHandlerContext pCtx, String pComercioId){
        log.info("***********************************");
        log.info("***ENVIANDO CONTACTLESS MULTICOM***");
        log.info("***********************************");
        String vComercioId = "00" + pComercioId;
        vComercioId = vComercioId.substring(vComercioId.length() - 2);
        String inicio = "002436303030303030303030313030363030301C";
        String comercio = "37390002" + NettyUtil.asciiToHex(vComercioId);
        String trama = inicio + comercio + "03";
        String xo = NettyUtil.xor(NettyUtil.hex2a(trama));
        String tramaFinal = "02" + trama + xo;
        sendMessageToPOS(pCtx, tramaFinal);
        return tramaFinal;
    }
    public static String sendSolicitudAnulacionMulticom(ChannelHandlerContext pCtx, String pComercioId){
        log.info("*********************************");
        log.info("***ENVIANDO ANULACION MULTICOM***");
        log.info("*********************************");
        String vComercioId = "00" + pComercioId;
        vComercioId = vComercioId.substring(vComercioId.length() - 2);
        String inicio = "002436303030303030303030313030353030301C";
        String comercio = "37390002" + NettyUtil.asciiToHex(vComercioId);
        String trama = inicio + comercio + "03";
        String xo = NettyUtil.xor(NettyUtil.hex2a(trama));
        String tramaFinal = "02" + trama + xo;
        sendMessageToPOS(pCtx, tramaFinal);
        return tramaFinal;
    }
    public static String sendSolicitudCierreMulticom(ChannelHandlerContext pCtx, String pComercioId){
        log.info("******************************");
        log.info("***ENVIANDO CIERRE MULTICOM***");
        log.info("******************************");
        String vComercioId = "00" + pComercioId;
        vComercioId = vComercioId.substring(vComercioId.length() - 2);
        String inicio = "002436303030303030303030313030313030301C";
        String comercio = "37390002" + NettyUtil.asciiToHex(vComercioId);
        String trama = inicio + comercio + "03";
        String xo = NettyUtil.xor(NettyUtil.hex2a(trama));
        String tramaFinal = "02" + trama + xo;
        sendMessageToPOS(pCtx, tramaFinal);
        return tramaFinal;

    }
    public static String sendTransRevNo(ChannelHandlerContext pCtx){
        log.info("***************************");
        log.info("***ENVIANDO TRANS.REV.NO***");
        log.info("***************************");
        String msg = "02002436303030303030303030313030303030311C3438000258580303";
        sendMessageToPOS(pCtx, msg);
        return msg;
    }
    public static String sendTipoTarjetaCtl(ChannelHandlerContext pCtx){
        log.info("**************************");
        log.info("***ENVIANDO CONTACTLESS***");
        log.info("**************************");
        String msg = "02001736303030303030303030313030363030310324";
        sendMessageToPOS(pCtx, msg);
        return msg;
    }
    public static String sendSolicitudCierre(ChannelHandlerContext pCtx){
        log.info("*******************************");
        log.info("***ENVIANDO SOLICITUD CIERRE***");
        log.info("*******************************");
        String msg = "02001736303030303030303030313030313030300322";
        sendMessageToPOS(pCtx, msg);
        return msg;
    }
    public static String sendSolicitudAnulacion(ChannelHandlerContext pCtx){
        log.info("**********************************");
        log.info("***ENVIANDO SOLICITUD ANULACION***");
        log.info("**********************************");
        String msg = "02001736303030303030303030313030353030300326";
        sendMessageToPOS(pCtx, msg);
        return msg;
    }
    public static String sendConfirmarAnulacion(ChannelHandlerContext pCtx){
        log.info("**********************************");
        log.info("***ENVIANDO SOLICITUD ANULACION***");
        log.info("**********************************");
        String msg = "02002436303030303030303030313030353030321C3438000230300305";
        sendMessageToPOS(pCtx, msg);
        return msg;
    }
    public static String sendSolicitudInicializar(ChannelHandlerContext pCtx){
        log.info("**************************");
        log.info("***ENVIANDO INICIALIZAR***");
        log.info("**************************");
        String msg = "02001736303030303030303030313030323030300321";
        sendMessageToPOS(pCtx, msg);
        return msg;
    }
    public static String sendDataToPos(String pMontoBob, ChannelHandlerContext pCtx){
        log.info("[KIOSCO]:Enviando datos de venta");
        String inicio = "007736303030303030303030313030303030321C";
        String monto = "3430000C" + NettyUtil.asciiToHex(pMontoBob)+"1C";
        String mpkh = "720"+"          ";
        mpkh = mpkh.substring(0,10);
        String numCaja = "3432000A"+NettyUtil.asciiToHex(mpkh)+"1C";
        String codresp = "34380002"+"2020"+"1C";
        String pnrh = "028510"+"          ";
        pnrh = pnrh.substring(0,10);
        String numTransac = "3533000A"+ NettyUtil.asciiToHex(pnrh)+"1C";
        String tipoCuenta = "38380001" + "31" + "03";
        String trama = inicio+monto+numCaja+codresp+numTransac+tipoCuenta;
        String xo = NettyUtil.xor(NettyUtil.hex2a(trama));
        String tramaFinal = "02"+trama+xo;
        sendMessageToPOS(pCtx, tramaFinal);
        return tramaFinal;

    }
    public static String sendAnulacionToPos(String pId, ChannelHandlerContext pCtx){
        log.info("[KIOSCO]:Enviando datos para anulación");
        String inicio = "003536303030303030303030313030353030311C";
        String recibo = "34330006" + NettyUtil.asciiToHex(pId) + "1C";
        String codresp = "34380002" + "2020" + "03";
        String trama = inicio + recibo + codresp;
        String xo = NettyUtil.xor(NettyUtil.hex2a(trama));
        String tramaFinal = "02" + trama + xo;
        sendMessageToPOS(pCtx, tramaFinal);
        return  tramaFinal;
    }
    public static void sendMessageToPOS(ChannelHandlerContext ctx, String msg) {
        if (ctx == null)
            return;
        ByteBuf buf = ctx.alloc().buffer();
        String data = NettyUtil.hex2a(msg);
        buf.writeCharSequence(data, CharsetUtil.UTF_8);
        ctx.write(buf);
        ctx.flush();
        log.info("[KIOSCO - SEND OK]:"+msg);
    }
}
