package com.pgt360.payment.util;

import com.pgt360.payment.server.handler.ServerHandler;
import com.pgt360.payment.service.dto.netty.ResponseDto;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcesoUtil {
    public static Logger log = LoggerFactory.getLogger(ProcesoUtil.class);
    public static String ack = "06";
    public static String respHost = "";
    public static boolean isAck1 = false;
    public static boolean isAck2 = false;
    public static boolean isAck3 = false;
    public static boolean isAck4 = false;
    public static boolean isAck5 = false;

    public static ResponseDto flujoChip(String pStrReply, ChannelHandlerContext pCtx) {
        ResponseDto vResponseDto = new ResponseDto();
        switch (ServerHandler.vRequestDto.getPaso()){
            case 1:{
                if(isAck1 && ServerHandler.vRequestDto.getTamaño() == 40){
                    ServerCommunication.sendAck(pCtx);
                    ServerCommunication.sendTransRevNo(pCtx);
                    ServerHandler.vRequestDto.setPaso(2);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if(isAck(pStrReply)){
                    isAck1 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                }
                vResponseDto.setEstado(false);
                vResponseDto.setMensaje(Constants.RES_1);
                vResponseDto.setData(null);
            }
            case 2: {
                if (isAck2 && ServerHandler.vRequestDto.getTamaño() == 36) {
                    ServerCommunication.sendAck(pCtx);
                    ServerHandler.vRequestDto.setPaso(3);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck2 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                }
                vResponseDto.setEstado(false);
                vResponseDto.setMensaje(Constants.RES_2);
                vResponseDto.setData(null);
            }
            case 3: {
                if (ServerHandler.vRequestDto.getTamaño() == 29) {
                    ServerCommunication.sendAck(pCtx);
                    String tramaFinal = ServerCommunication.sendDataToPos(ServerHandler.vRequestDto.getMonto(), pCtx);
                    ServerHandler.vRequestDto.setPaso(4);
                    ServerHandler.vRequestDto.setTamaño(0);
                }
                vResponseDto.setEstado(false);
                vResponseDto.setMensaje(Constants.RES_3);
                vResponseDto.setData(null);
            }
            break;
            case 4: {
                if (isAck4 && ServerHandler.vRequestDto.getTamaño() == 36) {
                    ServerCommunication.sendAck(pCtx);
                    ServerHandler.vRequestDto.setPaso(5);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck4 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                }
                vResponseDto.setEstado(false);
                vResponseDto.setMensaje(Constants.RES_4);
                vResponseDto.setData(null);
            }
            case 5: {
                if (ServerHandler.vRequestDto.getTamaño() >= 223) {
                    ServerCommunication.sendAck(pCtx);
                    vResponseDto.setData(ResponseUtil.getRespuestaHostVenta(pStrReply));
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_FINAL);
                    ServerHandler.vRequestDto.setPaso(1);
                    ServerHandler.vRequestDto.setTamaño(0);
                    isAck1 = false;
                    isAck2 = false;
                    isAck3 = false;
                    isAck4 = false;
                    ServerHandler.vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    ServerHandler.vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    break;
                } else {
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    break;
                }
            }
            default:
                vResponseDto.setData(pStrReply);
                vResponseDto.setEstado(false);
                vResponseDto.setMensaje(Constants.RES_NOT_VALID);
                break;

        }
        return vResponseDto;
    }

    public static ResponseDto flujoChipMulti(String pStrReply, ChannelHandlerContext pCtx) {
        return null;
    }
    public static ResponseDto flujoCtl(String pStrReply, ChannelHandlerContext pCtx) {
        return null;
    }

    public static ResponseDto flujoCtlMulti(String pStrReply, ChannelHandlerContext pCtx) {
        return null;
    }

    public static ResponseDto flujoAnulacion(String pStrReply, ChannelHandlerContext pCtx) {
        return null;
    }

    public static ResponseDto flujoAnulacionMulti(String pStrReply, ChannelHandlerContext pCtx) {
        return null;
    }

    public static ResponseDto flujoCierre(String pStrReply, ChannelHandlerContext pCtx) {
        return null;
    }

    public static ResponseDto flujoCierreMulti(String pStrReply, ChannelHandlerContext pCtx) {
        return null;
    }

    public static ResponseDto flujoInicializar(String pStrReply, ChannelHandlerContext pCtx) {
        ResponseDto vResponseDto = new ResponseDto();
        switch (ServerHandler.vRequestDto.getPaso()){
            case 1:{
                if(isAck1 && ServerHandler.vRequestDto.getTamaño()==29){
                    ServerCommunication.sendAck(pCtx);
                    vResponseDto.setMensaje(Constants.RES_FINAL);
                    vResponseDto.setEstado(true);
                    vResponseDto.setData(ResponseUtil.getRespuestaInicializacion(pStrReply));
                    ServerHandler.vRequestDto.setTamaño(0);
                    ServerHandler.vRequestDto.setPaso(-1);
                    isAck1 = false;
                    isAck2 = false;
                    ServerHandler.vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    ServerHandler.vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    break;
                } else if(isAck(pStrReply)){
                    isAck1 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setEstado(false);
                    vResponseDto.setData(null);
                    break;
                } else {
                    vResponseDto.setMensaje(Constants.RES_NOT_VALID);
                    vResponseDto.setEstado(false);
                    vResponseDto.setData(null);
                    break;
                }
            }
            default:{
                vResponseDto.setMensaje(Constants.RES_NOT_VALID);
                vResponseDto.setEstado(false);
                vResponseDto.setData(null);
                break;
            }
        }
        return vResponseDto;
    }

    public static boolean isAck(String pStr){
        return pStr.equals(ack);
    }
}
