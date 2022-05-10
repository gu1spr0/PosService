package com.pgt360.payment.server.handler;

import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.service.dto.netty.ResponseDto;
import com.pgt360.payment.util.*;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    public static Logger log = LoggerFactory.getLogger(ServerHandler.class);

    public static RequestDto vRequestDto = null;
    public static ResponseDto vResponseDto = null;
    public static ChannelHandlerContext vCtx;
    public static boolean statePos = Constants.STATE_PENDIENTE;

    private static ServerCom vServerCom;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        vCtx = ctx;
        log.info("###################[OK] CANAL CONECTADO!!#######################");
        log.info("[CLIENT] CANAL IP:"+ NettyUtil.getChannelAddress(ctx));
        log.info("[CLIENT] CANAL ID:"+ NettyUtil.getChannelId(ctx));
        log.info("################################################################");
        ServerHandler.statePos = Constants.STATE_PENDIENTE;
        vServerCom = new ServerCom(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        vCtx = ctx;
        log.info("#################[NOK] CANAL DESCONECTADO!!#################");
        log.info("[CLIENT] CANAL IP:"+ NettyUtil.getChannelAddress(ctx));
        log.info("[CLIENT] CANAL ID:"+ NettyUtil.getChannelId(ctx));
        log.info("############################################################");
        ServerHandler.statePos = Constants.STATE_REALIZADO;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        try{
            log.info("[POS]:"+msg);
            if(vServerCom.isAck(msg.toString())) log.info("POS:OK!");
            else ServerCom.vRequestDto.setTamaño(ServerCom.vRequestDto.getTamaño()+ NettyUtil.hex2a(msg.toString()).length());

            switch (ServerCom.vRequestDto.getFlujo()){
                case Constants.NUMBER_FLOW_INIT: vServerCom.flujoInicializar(msg.toString()); break;
                case Constants.NUMBER_FLOW_CHIP: vServerCom.flujoChip(msg.toString()); break;
                case Constants.NUMBER_FLOW_CHIP_MULTI: vServerCom.flujoChipMulti(msg.toString()); break;
                case Constants.NUMBER_FLOW_CTL: vServerCom.flujoCtl(msg.toString()); break;
                case Constants.NUMBER_FLOW_CTL_MULTI: vServerCom.flujoCtlMulti(msg.toString()); break;
                case Constants.NUMBER_FLOW_DELETED: vServerCom.flujoAnulacion(msg.toString()); break;
                case Constants.NUMBER_FLOW_DELETED_MULTI: vServerCom.flujoAnulacionMulti(msg.toString()); break;
                case Constants.NUMBER_FLOW_CLOSE: vServerCom.flujoCierre(msg.toString()); break;
                case Constants.NUMBER_FLOW_CLOSE_MULTI: vServerCom.flujoCierreMulti(msg.toString()); break;
                default: vServerCom.flujoNone(); break;
            }
        }catch (StringIndexOutOfBoundsException ex){
            log.error("[ERROR] Error en la copia de datos:"+ex.getMessage());
            statePos = Constants.STATE_REALIZADO;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(cause instanceof ReadTimeoutException){
            log.error("[CLIENT] TIMEOUT:"+ NettyUtil.getChannelId(ctx));
            statePos = Constants.STATE_REALIZADO;
        } else {
            super.exceptionCaught(ctx, cause);
            log.error("#################[NOK] EXCEPCION!!#################");
            log.error("[CLIENT] CANAL IP:"+ NettyUtil.getChannelAddress(ctx));
            log.error("[CLIENT] CANAL ID:"+ NettyUtil.getChannelId(ctx));
            log.error("[CLIENT] CAUSA:"+ cause.getCause());
            log.error("############################################################");
        }
    }
}
