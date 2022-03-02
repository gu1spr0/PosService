package com.pgt360.payment.server.handler;

import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.service.ConexionService;
import com.pgt360.payment.service.DispositivoService;
import com.pgt360.payment.service.dto.conexion.ConexionAddDto;
import com.pgt360.payment.service.dto.conexion.ConexionQueryDto;
import com.pgt360.payment.service.dto.dispositivo.DispositivoQueryDto;
import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.service.dto.netty.ResponseDto;
import com.pgt360.payment.util.*;
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

import java.net.SocketAddress;
import java.util.Date;

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
    public static boolean comPos = false;

    private final ConexionService conexionService;
    private final DispositivoService dispositivoService;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        ServerHandler.ctx = ctx;
        clients.add(incoming);
        SocketAddress vSocketAddress = incoming.remoteAddress();
        log.info("[SERVER]-"+vSocketAddress.toString()+" SE CONECTÓ DISPOSITIVO CON EL ID:"+incoming.id());
        ServerHandler.statePos = Constants.STATE_PENDIENTE;
        /*String ip = vSocketAddress.toString().substring(1,vSocketAddress.toString().length()-1).split(":")[0];
        DispositivoQueryDto vDispositivoQueryDto = dispositivoService.buscarDispositivoIp(ip);
        if(vDispositivoQueryDto == null){
            log.error("Dispositivo no encontrado!");
            ctx.close();
        }else{
            log.info(vDispositivoQueryDto.toString());
            conexionService.verificarConexiones(vDispositivoQueryDto.getIp());
            ConexionAddDto vConexionAddDto = new ConexionAddDto();
            vConexionAddDto.setIdDispositivo(vDispositivoQueryDto.getId());
            vConexionAddDto.setFechaConexion(new Date());
            vConexionAddDto.setHoraConexion(new Date());
            vConexionAddDto.setIdCanal(incoming.id().toString());
            vConexionAddDto.setEstado(Constants.STATE_ACTIVE);
            ConexionQueryDto vConexionQueryDto = conexionService.agregarConexion(vConexionAddDto);
            if(vConexionQueryDto == null){
                Object[] obj = {vConexionQueryDto};
                throw Message.GetBadRequest(MessageDescription.notExists, obj);
            }
            log.info("Conexion registrada id:"+vConexionQueryDto.getId());
        }*/
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        ServerHandler.ctx = null;
        clients.remove(incoming);
        SocketAddress vSocketAddress = incoming.remoteAddress();
        log.info("[SERVER] - "+vSocketAddress.toString()+ " SE DESCONECTÓ DISPOSITIVO CON EL ID:"+incoming.id()+"\n");
        ServerHandler.statePos = Constants.STATE_REALIZADO;
        /*ConexionQueryDto vConexionQueryDto = conexionService.modificarConexionPorCanal(incoming.id().toString());
        log.info(vConexionQueryDto.toString());*/
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        for (Channel vChannel : clients){
            if(vChannel.id().equals(ctx.channel().id())){
                //vChannel.writeAndFlush()
                log.info("Encontrado");
            }
        }
        try{
            log.info("[POS]:"+msg);
            if(ProcesoUtil.isAck(msg.toString())){
                log.info("POS:OK!");
                comPos = true;
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
        if(comPos){
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
        } else {
            log.error("PROCESO NO REALIZADO!");
        }

    }
}
