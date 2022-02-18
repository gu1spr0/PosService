package com.pgt360.payment.server.handler;

import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.service.dto.netty.ResponseDto;
import com.pgt360.payment.util.Constants;
import com.pgt360.payment.util.NettyUtil;
import com.pgt360.payment.util.ProcesoUtil;
import com.pgt360.payment.util.ServerCommunication;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    public static Logger log = LoggerFactory.getLogger(ServerHandler.class);
    public static RequestDto vRequestDto=null;
    public static ResponseDto vResponseDto = null;
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static ChannelHandlerContext ctx;
    public static boolean statePos = Constants.STATE_PENDIENTE;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        ServerHandler.ctx = ctx;
        clients.add(incoming);
        log.info("[SERVER]-"+incoming.remoteAddress()+" SE CONECTÓ DISPOSITIVO CON EL ID:"+incoming.id());
        ServerCommunication.sendEstadoPos(ServerHandler.ctx);
        ServerHandler.statePos = Constants.STATE_PENDIENTE;

    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        ServerHandler.ctx = null;
        clients.remove(incoming);
        log.info("[SERVER] - "+incoming.localAddress() + " SE DESCONECTÓ DISPOSITIVO CON EL ID:"+incoming.id()+"\n");
        ServerHandler.statePos = Constants.STATE_REALIZADO;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        try{
            log.info("[POS]:"+msg);
            if(ProcesoUtil.isAck(msg.toString())){
                log.info("POS:OK!");
            }else{
                ServerHandler.vRequestDto.setTamaño(ServerHandler.vRequestDto.getTamaño()+ NettyUtil.hex2a(msg.toString()).length());
            }
            if(ServerHandler.vRequestDto.getFlujo() == Constants.NUMBER_FLOW_INIT){
                ServerHandler.vResponseDto = ProcesoUtil.flujoInicializar(msg.toString(), ServerHandler.ctx);
            } else if(ServerHandler.vRequestDto.getFlujo() == Constants.NUMBER_FLOW_CHIP){
                ServerHandler.vResponseDto = ProcesoUtil.flujoChip(msg.toString(), ServerHandler.ctx);
            } else if(ServerHandler.vRequestDto.getFlujo() == Constants.NUMBER_FLOW_CHIP_MULTI){
                ServerHandler.vResponseDto = ProcesoUtil.flujoChipMulti(msg.toString(), ServerHandler.ctx);
            } else if(ServerHandler.vRequestDto.getFlujo() == Constants.NUMBER_FLOW_CTL){
                ServerHandler.vResponseDto = ProcesoUtil.flujoCtl(msg.toString(), ServerHandler.ctx);
            } else if(ServerHandler.vRequestDto.getFlujo() == Constants.NUMBER_FLOW_CTL_MULTI){
                ServerHandler.vResponseDto = ProcesoUtil.flujoCtlMulti(msg.toString(), ServerHandler.ctx);
            } else if(ServerHandler.vRequestDto.getFlujo() == Constants.NUMBER_FLOW_DELETED){
                ServerHandler.vResponseDto = ProcesoUtil.flujoAnulacion(msg.toString(), ServerHandler.ctx);
            } else if(ServerHandler.vRequestDto.getFlujo() == Constants.NUMBER_FLOW_DELETED_MULTI){
                ServerHandler.vResponseDto = ProcesoUtil.flujoAnulacionMulti(msg.toString(), ServerHandler.ctx);
            } else if(ServerHandler.vRequestDto.getFlujo() == Constants.NUMBER_FLOW_CLOSE){
                ServerHandler.vResponseDto = ProcesoUtil.flujoCierre(msg.toString(), ServerHandler.ctx);
            } else if(ServerHandler.vRequestDto.getFlujo() == Constants.NUMBER_FLOW_CLOSE_MULTI){
                ServerHandler.vResponseDto = ProcesoUtil.flujoCierreMulti(msg.toString(), ServerHandler.ctx);
            } else if(ServerHandler.vRequestDto.getFlujo() == Constants.NUMBER_FLOW_NONE){
                ServerHandler.vResponseDto = new ResponseDto();
                ServerHandler.vResponseDto.setEstado(false);
                ServerHandler.vResponseDto.setMensaje("Sin tipo de flujo definido");
                ServerHandler.vResponseDto.setData(null);
            }
        }catch (StringIndexOutOfBoundsException ex){
            log.error("ERROR EN LA COPIA DE DATOS:"+ex.getMessage());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Canal con id:"+ctx.channel().id()+" activo");
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Canal con id:"+ctx.channel().id()+" inactivo");
    }

    public static void selectProcess(RequestDto vRequestDto) {
        log.info("*******************************");
        log.info("********GENERANDO FLUJO********");
        log.info("*******************************");
        log.info("FLUJO STR:"+vRequestDto.getStrFlujo());
        log.info("FLUJO NUM:"+vRequestDto.getFlujo());
        ServerHandler.vRequestDto = vRequestDto;
        switch (vRequestDto.getFlujo()){
            case Constants.NUMBER_FLOW_INIT:
                ServerCommunication.sendSolicitudInicializar(ServerHandler.ctx);
                break;
            case Constants.NUMBER_FLOW_CHIP:
                ServerCommunication.sendConnectionChip(ServerHandler.ctx);
                break;
            case Constants.NUMBER_FLOW_CHIP_MULTI:
                ServerCommunication.sendConnectionChipMulticom(ServerHandler.ctx, ServerHandler.vRequestDto.getIdComercio());
                break;
            case Constants.NUMBER_FLOW_CTL:
                ServerCommunication.sendConnectionCtl(ServerHandler.ctx);
                break;
            case Constants.NUMBER_FLOW_CTL_MULTI:
                ServerCommunication.sendConnectionCtlMulticom(ServerHandler.ctx, ServerHandler.vRequestDto.getIdComercio());
                break;
            case Constants.NUMBER_FLOW_DELETED:
                ServerCommunication.sendSolicitudAnulacion(ServerHandler.ctx);
                break;
            case Constants.NUMBER_FLOW_DELETED_MULTI:
                ServerCommunication.sendSolicitudAnulacionMulticom(ServerHandler.ctx, ServerHandler.vRequestDto.getIdComercio());
                break;
            case Constants.NUMBER_FLOW_CLOSE:
                ServerCommunication.sendSolicitudCierre(ServerHandler.ctx);
                break;
            case Constants.NUMBER_FLOW_CLOSE_MULTI:
                ServerCommunication.sendSolicitudCierreMulticom(ServerHandler.ctx, ServerHandler.vRequestDto.getIdComercio());
                break;
            default:
                log.info("[KIOSKO]: Flujo no definido!");
                break;
        }
    }
}
