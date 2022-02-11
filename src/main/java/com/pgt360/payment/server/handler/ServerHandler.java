package com.pgt360.payment.server.handler;

import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.util.ConstantsUtil;
import com.pgt360.payment.util.NettyUtil;
import com.pgt360.payment.util.ServerCommunication;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static RequestDto vRequestDto=null;

    ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static  ChannelHandlerContext ctx;
    int paso = 1;
    int tam = 0;
    String flujo = "";
    String ack = "06";
    Double montoBOB = 0.00;
    String reciboTRA = "";
    String respHost = "";
    String respHostJson = "";
    boolean pagoChip = false;
    boolean pagoChipMulti = false;
    boolean pagoCtl = false;
    boolean pagoCtlMulti = false;
    boolean anulacionTrans = false;
    boolean anulacionTransMulti = false;
    boolean cierrePos = false;
    boolean cierrePosMulti = false;
    boolean inicializarPos = false;
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
            case ConstantsUtil.NUMBER_FLOW_INIT:
                ServerCommunication.sendSolicitudInicializar(ctx);
                ServerHandler.vRequestDto = vRequestDto;
                break;
            case ConstantsUtil.NUMBER_FLOW_CHIP:
                ServerHandler.vRequestDto = vRequestDto;
                ServerCommunication.sendConnectionChip(ctx);
                break;
            case ConstantsUtil.NUMBER_FLOW_CHIP_MULTI: break;
            case ConstantsUtil.NUMBER_FLOW_CTL: break;
            case ConstantsUtil.NUMBER_FLOW_CTL_MULTI: break;
            case ConstantsUtil.NUMBER_FLOW_DELETED: break;
            case ConstantsUtil.NUMBER_FLOW_DELETED_MULTI: break;
            case ConstantsUtil.NUMBER_FLOW_CLOSE: break;
            case ConstantsUtil.NUMBER_FLOW_CLOSE_MULTI: break;
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
                    ServerHandler.vRequestDto.setFlujo(ConstantsUtil.NUMBER_FLOW_NONE);
                    ServerHandler.vRequestDto.setStrFlujo(ConstantsUtil.FLOW_NONE);
                    break;
                } else {
                    log.info(resp);
                }
            }
            default:
                break;

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
