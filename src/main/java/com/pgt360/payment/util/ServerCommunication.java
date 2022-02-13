package com.pgt360.payment.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServerCommunication {
    private static Logger log = LoggerFactory.getLogger(ServerCommunication.class);
    public static String sendAck(ChannelHandlerContext ctx){
        String msg = "06";
        SendMessageToPOS(ctx, msg);
        return msg;
    }
    public static String sendConnectionChip(ChannelHandlerContext ctx){
        log.info("*******************");
        log.info("***ENVIANDO CHIP***");
        log.info("*******************");
        String msg = "02001736303030303030303030313030303030300323";
        log.info("[KIOSCO]:"+msg);
        SendMessageToPOS(ctx, msg);
        return msg;
    }
    public static String sendConnectionCtl(ChannelHandlerContext ctx){
        String msg = "02001736303030303030303030313030363030300325";
        SendMessageToPOS(ctx, msg);
        return msg;
    }
    public static String sendTransRevNo(ChannelHandlerContext ctx){
        String msg = "02002436303030303030303030313030303030311C3438000258580303";
        SendMessageToPOS(ctx, msg);
        return msg;
    }
    public static String sendTipoTarjetaCtl(ChannelHandlerContext ctx){
        String msg = "02001736303030303030303030313030363030310324";
        SendMessageToPOS(ctx, msg);
        return msg;
    }
    public static String sendSolicitudCierre(ChannelHandlerContext ctx){
        String msg = "02001736303030303030303030313030313030300322";
        SendMessageToPOS(ctx, msg);
        return msg;
    }
    public static String sendSolicitudAnulacion(ChannelHandlerContext ctx){
        String msg = "02001736303030303030303030313030353030300326";
        SendMessageToPOS(ctx, msg);
        return msg;
    }
    public static String sendConfirmarAnulacion(ChannelHandlerContext ctx){
        String msg = "02002436303030303030303030313030353030321C3438000230300305";
        SendMessageToPOS(ctx, msg);
        return msg;
    }
    public static String sendSolicitudInicializar(ChannelHandlerContext ctx){
        String msg = "02001736303030303030303030313030323030300321";
        SendMessageToPOS(ctx, msg);
        System.out.println("Inicializacion completa");
        return msg;
    }
    public static String sendDataToPos(String montobob, ChannelHandlerContext ctx){
        log.info("[KIOSCO]:Enviando datos de venta");
        String inicio = "007736303030303030303030313030303030321C";
        String monto = "3430000C" + NettyUtil.asciiToHex(montobob)+"1C";
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
        log.info("<<<<<<"+tramaFinal+">>>>>>");
        SendMessageToPOS(ctx, tramaFinal);
        return tramaFinal;

    }
    public static void SendMessageToPOS(ChannelHandlerContext ctx, String msg) {
        if (ctx == null)
            return;
        ByteBuf buf = ctx.alloc().buffer();
        String data = NettyUtil.hex2a(msg);
        buf.writeCharSequence(data, CharsetUtil.UTF_8);
        ctx.write(buf);
        ctx.flush();
        log.info("[MENSAJE:]<"+data+"> Enviado con exito!!");
    }
}
