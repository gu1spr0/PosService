package com.pgt360.payment.server.handler;

import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.util.ConstantsUtil;
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
        log.info("Mensaje recibido desde Read-Handler:"+msg.toString());
        if(vRequestDto==null){
            log.error("Request nulo!");
        }else{
            log.info("Continuando flujo..");
            vRequestDto.setTamaño(msg.toString().length());
            if(vRequestDto.getStrFlujo().equals(ConstantsUtil.FLOW_CHIP)){
                log.info("Flujo:"+vRequestDto.getStrFlujo());
                this.flujoChip(msg.toString(), ctx);
            }
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

    public static void selectProcess(RequestDto vRequestDto){
        System.out.println("Flujo:"+vRequestDto.getStrFlujo());
        switch (vRequestDto.getFlujo()){
            case ConstantsUtil.NUMBER_FLOW_INIT:
                ServerCommunication.sendAck(ctx);
                break;
            case ConstantsUtil.NUMBER_FLOW_CHIP:
                ServerCommunication.sendConnectionChip(ctx);
                ServerHandler.vRequestDto = vRequestDto;
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
        switch (this.paso){
            case 1: System.out.println("Paso 1");
                if(isAck1 && this.tam == 40){
                    reply = ServerCommunication.sendAck(ctx);
                    reply = ServerCommunication.sendTransRevNo(ctx);
                    vRequestDto.setPaso(2);
                    vRequestDto.setTamaño(0);
                    break;
            } else if(this.isAck(resp)){
                    this.isAck1 = true;
                    vRequestDto.setTamaño(0);
                    break;
            }
            case 2: System.out.println("Paso 2");
                if(isAck2 && vRequestDto.getTamaño()== 36){
                reply = ServerCommunication.sendAck(ctx);
                vRequestDto.setPaso(3);
                vRequestDto.setTamaño(0);
                break;
            } else if (this.isAck(resp)){
                this.isAck2 = true;
                vRequestDto.setTamaño(0);
                break;
            }
            case 3: System.out.println("Paso 3");
                if(vRequestDto.getTamaño() == 29){
                    reply = ServerCommunication.sendAck(ctx);
                    String tramaf = ServerCommunication.sendDataToPos(vRequestDto.getMonto(), ctx);
                    vRequestDto.setPaso(4);
                    vRequestDto.setTamaño(0);
                }
                break;
            case 4: System.out.println("Paso 4");
                if(this.isAck4 && vRequestDto.getTamaño() == 36){
                    reply = ServerCommunication.sendAck(ctx);
                    vRequestDto.setPaso(5);
                    vRequestDto.setTamaño(0);
                    break;
                } else if(this.isAck(resp)){
                this.isAck4 = true;
                vRequestDto.setTamaño(0);
                break;
            }
            case 5: System.out.println("Paso 5");
                if(vRequestDto.getTamaño() >= 223){
                    reply = ServerCommunication.sendAck(ctx);
                    respHost = respHost + resp;
                    vRequestDto.setPaso(-1);
                    vRequestDto.setTamaño(0);
                    this.isAck1 = false;
                    this.isAck2 = false;
                    this.isAck3 = false;
                    this.isAck4 = false;
                    vRequestDto.setFlujo(ConstantsUtil.NUMBER_FLOW_NONE);
                    vRequestDto.setStrFlujo(ConstantsUtil.FLOW_NONE);
                    break;
            }else{
                log.info(resp);
            }
            default:
                break;

        }
    }
    public boolean isAck(String pStr){
        if(pStr == this.ack){
            return true;
        } else {
            return  false;
        }
    }

}
