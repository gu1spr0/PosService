package com.pgt360.payment.server.handler;

import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.util.Constants;
import com.pgt360.payment.util.NettyUtil;
import com.pgt360.payment.util.ServerCommunication;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    public static RequestDto vRequestDto=null;

    ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static  ChannelHandlerContext ctx;
    String ack = "06";
    String respHost = "";
    boolean isAck1 = false;
    boolean isAck2 = false;
    boolean isAck3 = false;
    boolean isAck4 = false;
    boolean isAck5 = false;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        ServerHandler.ctx = ctx;
        clients.add(incoming);
        log.info("[SERVER]-"+incoming.remoteAddress()+" SE CONECTÓ DISPOSITIVO CON EL ID:"+incoming.id());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        ServerHandler.ctx = null;
        clients.remove(incoming);
        log.info("[SERVER] - "+incoming.remoteAddress() + " SE DESCONECTÓ DISPOSITIVO CON EL ID:"+incoming.id()+"\n");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        try{
            System.out.println("RECIBIDO DEL DECODIFICADOR:"+msg);
            if(this.isAck(msg.toString())){
                System.out.println("POS:OK!");
            }else{
                ServerHandler.vRequestDto.setTamaño(ServerHandler.vRequestDto.getTamaño()+NettyUtil.hex2a(msg.toString()).length());
            }
            this.flujoChip(msg.toString(),ctx);
            System.out.println("TAMAÑO:"+NettyUtil.hex2a(msg.toString()).length());
            System.out.println("ACK1:"+this.isAck1);
        }catch (StringIndexOutOfBoundsException ex){
            ex.getMessage();
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
        System.out.println("**************************************");
        System.out.println("********GENERANDO FLUJO********");
        System.out.println("**************************************");
        System.out.println("FLUJO STR:"+vRequestDto.getStrFlujo());
        System.out.println("FLUJO NUM:"+vRequestDto.getFlujo());
        switch (vRequestDto.getFlujo()){
            case Constants.NUMBER_FLOW_INIT:
                ServerCommunication.sendSolicitudInicializar(ctx);
                ServerHandler.vRequestDto = vRequestDto;
                break;
            case Constants.NUMBER_FLOW_CHIP:
                ServerHandler.vRequestDto = vRequestDto;
                ServerCommunication.sendConnectionChip(ctx);
                break;
            case Constants.NUMBER_FLOW_CHIP_MULTI: break;
            case Constants.NUMBER_FLOW_CTL:
                ServerHandler.vRequestDto = vRequestDto;
                ServerCommunication.sendConnectionCtl(ctx);
                break;
            case Constants.NUMBER_FLOW_CTL_MULTI: break;
            case Constants.NUMBER_FLOW_DELETED: break;
            case Constants.NUMBER_FLOW_DELETED_MULTI: break;
            case Constants.NUMBER_FLOW_CLOSE: break;
            case Constants.NUMBER_FLOW_CLOSE_MULTI: break;
        }
    }

    public void flujoChip(String resp, ChannelHandlerContext ctx){
        String reply = "";
        switch (ServerHandler.vRequestDto.getPaso()){
            case 1:{
                System.out.println("Paso 1");
                if(isAck1 && ServerHandler.vRequestDto.getTamaño() == 40){
                    reply = ServerCommunication.sendAck(ctx);
                    reply = ServerCommunication.sendTransRevNo(ctx);
                    ServerHandler.vRequestDto.setPaso(2);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if(this.isAck(resp)){
                    this.isAck1 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                }
            }
            case 2: {
                System.out.println("Paso 2");
                if (isAck2 && ServerHandler.vRequestDto.getTamaño() == 36) {
                    reply = ServerCommunication.sendAck(ctx);
                    ServerHandler.vRequestDto.setPaso(3);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if (this.isAck(resp)) {
                    this.isAck2 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                }
            }
            case 3: {
                System.out.println("Paso 3");
                if (ServerHandler.vRequestDto.getTamaño() == 29) {
                    reply = ServerCommunication.sendAck(ctx);
                    String tramaf = ServerCommunication.sendDataToPos(ServerHandler.vRequestDto.getMonto(), ctx);
                    ServerHandler.vRequestDto.setPaso(4);
                    ServerHandler.vRequestDto.setTamaño(0);
                }
            }
                break;
            case 4: {
                System.out.println("Paso 4");
                if (this.isAck4 && ServerHandler.vRequestDto.getTamaño() == 36) {
                    reply = ServerCommunication.sendAck(ctx);
                    ServerHandler.vRequestDto.setPaso(5);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if (this.isAck(resp)) {
                    this.isAck4 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                }
            }
            case 5: {
                System.out.println("Paso 5");
                if (ServerHandler.vRequestDto.getTamaño() >= 223) {
                    reply = ServerCommunication.sendAck(ctx);
                    respHost = respHost + resp;
                    ServerHandler.vRequestDto.setPaso(-1);
                    ServerHandler.vRequestDto.setTamaño(0);
                    this.isAck1 = false;
                    this.isAck2 = false;
                    this.isAck3 = false;
                    this.isAck4 = false;
                    ServerHandler.vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    ServerHandler.vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    ServerHandler.vRequestDto.setRespuesta(respHost);
                    break;
                } else {
                    log.info(resp);
                    break;
                }
            }
            default:
                break;

        }
    }
    public void flujoChipMulti(String pStrReply, ChannelHandlerContext pCtx){
        String resp = "";
        switch (ServerHandler.vRequestDto.getPaso()){
            case 1:{
                System.out.println("Paso 1");
                if(this.isAck1 && ServerHandler.vRequestDto.getTamaño()==81){
                    resp = ServerCommunication.sendAck(pCtx);
                    resp = ServerCommunication.sendTransRevNo(pCtx);
                    ServerHandler.vRequestDto.setPaso(2);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if(isAck(pStrReply)){
                    isAck1 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                }
            }
            case 2:{
                System.out.println("Paso 2");
                if(this.isAck2 && ServerHandler.vRequestDto.getTamaño()==36){
                    resp = ServerCommunication.sendAck(pCtx);
                    ServerHandler.vRequestDto.setPaso(3);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if(isAck(pStrReply)){
                    this.isAck2 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                }
            }
            case 3:{
                System.out.println("Paso 3");
                if(ServerHandler.vRequestDto.getTamaño()==29){
                    resp = ServerCommunication.sendAck(pCtx);
                    ServerHandler.vRequestDto.setPaso(4);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else{
                    break;
                }
            }
            case 4:{
                System.out.println("Paso 4");
                if(this.isAck4 && ServerHandler.vRequestDto.getTamaño()==36){
                    resp = ServerCommunication.sendAck(pCtx);
                    ServerHandler.vRequestDto.setPaso(5);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if(isAck(pStrReply)){
                        this.isAck4 = true;
                        ServerHandler.vRequestDto.setTamaño(0);
                }
            }
            case 5:{
                System.out.println("Paso 5");
                if(ServerHandler.vRequestDto.getTamaño() >= 223){
                    resp = ServerCommunication.sendAck(pCtx);
                    ServerHandler.vRequestDto.setRespuesta(ServerHandler.vRequestDto.getRespuesta()+pStrReply);
                    ServerHandler.vRequestDto.setPaso(-1);
                    ServerHandler.vRequestDto.setTamaño(0);
                    this.isAck1 = false;
                    this.isAck2 = false;
                    this.isAck3 = false;
                    this.isAck4 = false;
                    ServerHandler.vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    ServerHandler.vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    break;
                } else {
                    resp = pStrReply;
                    break;
                }
            }
            default:
                break;

        }
    }
    public void flujoCtl(String pStrReply, ChannelHandlerContext pCtx){
        String resp = "";
        switch (ServerHandler.vRequestDto.getPaso()){
            case 1:{
                System.out.println("Paso 1");
                if(this.isAck1 && ServerHandler.vRequestDto.getTamaño()==40){
                    resp = ServerCommunication.sendAck(pCtx);
                    resp = ServerCommunication.sendTransRevNo(pCtx);
                    ServerHandler.vRequestDto.setPaso(2);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if (isAck(pStrReply)){
                    this.isAck1 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                }
            }
            case 2:{
                System.out.println("Paso 2");
                if(this.isAck2 && ServerHandler.vRequestDto.getTamaño() == 29){
                    resp = ServerCommunication.sendAck(pCtx);
                    String tramaf = ServerCommunication.sendDataToPos(ServerHandler.vRequestDto.getMonto(), pCtx);
                    ServerHandler.vRequestDto.setPaso(3);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if(isAck(pStrReply)){
                    this.isAck2 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                }
            }
            case 3:{
                System.out.println("Paso 3");
                if(this.isAck3 && ServerHandler.vRequestDto.getTamaño()==36){
                    resp = ServerCommunication.sendAck(pCtx);
                    resp = ServerCommunication.sendTipoTarjetaCtl(pCtx);
                    ServerHandler.vRequestDto.setPaso(4);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if (isAck(pStrReply)){
                    this.isAck3 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                }
            }
            case 4:{
                System.out.println("Paso 4");
                if(isAck4 && ServerHandler.vRequestDto.getTamaño() == 36){
                    resp = ServerCommunication.sendAck(pCtx);
                    ServerHandler.vRequestDto.setPaso(5);
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                } else if (isAck(pStrReply)){
                    this.isAck4 = true;
                    ServerHandler.vRequestDto.setTamaño(0);
                    break;
                }
            }
            case 5:{
                System.out.println("Paso 5");
                if(ServerHandler.vRequestDto.getTamaño() >= 222){
                    resp = ServerCommunication.sendAck(pCtx);
                    ServerHandler.vRequestDto.setRespuesta(ServerHandler.vRequestDto.getRespuesta()+pStrReply);
                    ServerHandler.vRequestDto.setPaso(-1);
                    ServerHandler.vRequestDto.setTamaño(0);
                    this.isAck1 = false;
                    this.isAck2 = false;
                    this.isAck3 = false;
                    this.isAck4 = false;
                    ServerHandler.vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    ServerHandler.vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    break;
                } else {
                    resp = pStrReply;
                }
            }

        }
    }
    public boolean isAck(String pStr){
        if(pStr.equals(this.ack)){
            return true;
        } else {
            return  false;
        }
    }

}
