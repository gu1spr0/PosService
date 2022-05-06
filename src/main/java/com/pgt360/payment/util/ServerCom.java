package com.pgt360.payment.util;

import com.pgt360.payment.server.handler.ServerHandler;
import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.service.dto.netty.ResponseDto;
import com.pgt360.payment.service.dto.netty.VentaDto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServerCom {
    private final Logger log = LoggerFactory.getLogger(ServerCom.class);
    private static ChannelHandlerContext vCtx = null;
    public static String ack = "06";
    public static boolean isAck1 = false;
    public static boolean isAck2 = false;
    public static boolean isAck3 = false;
    public static boolean isAck4 = false;
    public static RequestDto vRequestDto;
    public static ResponseDto vResponseDto;
    public ServerCom(ChannelHandlerContext pCtx) {
        vCtx = pCtx;
    }

    public String sendAck() {
        log.info("*********************");
        log.info("*****ENVIANDO ACK****");
        log.info("*********************");
        String msg = "06";
        sendMessageToPOS(msg);
        return msg;
    }

    public String sendConnectionChip() {
        log.info("*******************");
        log.info("***ENVIANDO CHIP***");
        log.info("*******************");
        String msg = "02001736303030303030303030313030303030300323";
        sendMessageToPOS(msg);
        return msg;
    }

    public String sendConnectionCtl() {
        log.info("**************************");
        log.info("***ENVIANDO CONTACTLESS***");
        log.info("**************************");
        String msg = "02001736303030303030303030313030363030300325";
        sendMessageToPOS(msg);
        return msg;
    }

    public String sendTransRevNo() {
        log.info("***************************");
        log.info("***ENVIANDO TRANS.REV.NO***");
        log.info("***************************");
        String msg = "02002436303030303030303030313030303030311C3438000258580303";
        sendMessageToPOS(msg);
        return msg;
    }

    public String sendTipoTarjetaCtl() {
        log.info("**************************");
        log.info("***ENVIANDO CONTACTLESS***");
        log.info("**************************");
        String msg = "02001736303030303030303030313030363030310324";
        sendMessageToPOS(msg);
        return msg;
    }

    public String sendSolicitudCierre() {
        log.info("*******************************");
        log.info("***ENVIANDO SOLICITUD CIERRE***");
        log.info("*******************************");
        String msg = "02001736303030303030303030313030313030300322";
        sendMessageToPOS(msg);
        return msg;
    }

    public String sendSolicitudAnulacion() {
        log.info("**********************************");
        log.info("***ENVIANDO SOLICITUD ANULACION***");
        log.info("**********************************");
        String msg = "02001736303030303030303030313030353030300326";
        sendMessageToPOS(msg);
        return msg;
    }

    public String sendConfirmarAnulacion() {
        log.info("**********************************");
        log.info("***ENVIANDO SOLICITUD ANULACION***");
        log.info("**********************************");
        String msg = "02002436303030303030303030313030353030321C3438000230300305";
        sendMessageToPOS(msg);
        return msg;
    }

    public String sendSolicitudInicializar() {
        log.info("**************************");
        log.info("***ENVIANDO INICIALIZAR***");
        log.info("**************************");
        String msg = "02001736303030303030303030313030323030300321";
        sendMessageToPOS(msg);
        return msg;
    }

    public String sendConnectionChipMulticom(int pComercioId) {
        log.info("****************************");
        log.info("***ENVIANDO CHIP MULTICOM***");
        log.info("****************************");
        String vComercioId = "00" + pComercioId;
        log.info("[TAREA]: Id comercio = " + vComercioId);
        vComercioId = vComercioId.substring(vComercioId.length() - 2);
        log.info("[TAREA]: Id comercio completado = " + vComercioId);
        String inicio = "002436303030303030303030313030303030301C";
        String comercio = "37390002" + NettyUtil.asciiToHex(vComercioId);
        String trama = inicio + comercio + "03";
        String xo = NettyUtil.xor(NettyUtil.hex2a(trama));
        String tramaFinal = "02" + trama + xo;
        sendMessageToPOS(tramaFinal);
        return tramaFinal;
    }

    public String sendConnectionCtlMulticom(int pComercioId) {
        log.info("***********************************");
        log.info("***ENVIANDO CONTACTLESS MULTICOM***");
        log.info("***********************************");
        String vComercioId = "00" + pComercioId;
        ;
        vComercioId = vComercioId.substring(vComercioId.length() - 2);
        String inicio = "002436303030303030303030313030363030301C";
        String comercio = "37390002" + NettyUtil.asciiToHex(vComercioId);
        String trama = inicio + comercio + "03";
        String xo = NettyUtil.xor(NettyUtil.hex2a(trama));
        String tramaFinal = "02" + trama + xo;
        sendMessageToPOS(tramaFinal);
        return tramaFinal;
    }

    public String sendSolicitudAnulacionMulticom(int pComercioId) {
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
        sendMessageToPOS(tramaFinal);
        return tramaFinal;
    }

    public String sendSolicitudCierreMulticom(int pComercioId) {
        log.info("******************************");
        log.info("***ENVIANDO CIERRE MULTICOM***");
        log.info("******************************");
        String vComercioId = "00" + pComercioId;
        ;
        vComercioId = vComercioId.substring(vComercioId.length() - 2);
        String inicio = "002436303030303030303030313030313030301C";
        String comercio = "37390002" + NettyUtil.asciiToHex(vComercioId);
        String trama = inicio + comercio + "03";
        String xo = NettyUtil.xor(NettyUtil.hex2a(trama));
        String tramaFinal = "02" + trama + xo;
        sendMessageToPOS(tramaFinal);
        return tramaFinal;

    }

    public String sendDataToPos(String pMontoBob) {
        log.info("[KIOSCO]:Enviando datos de venta");
        String inicio = "007736303030303030303030313030303030321C";
        log.warn("El monto para enviar es: " + pMontoBob);
        String monto = "3430000C" + NettyUtil.asciiToHex(pMontoBob) + "1C";
        log.warn("El monto codificado de ascii es :" + monto);
        String mpkh = "720" + "          ";
        mpkh = mpkh.substring(0, 10);
        String numCaja = "3432000A" + NettyUtil.asciiToHex(mpkh) + "1C";
        String codresp = "34380002" + "2020" + "1C";
        String pnrh = "028510" + "          ";
        pnrh = pnrh.substring(0, 10);
        String numTransac = "3533000A" + NettyUtil.asciiToHex(pnrh) + "1C";
        String tipoCuenta = "38380001" + "31" + "03";
        String trama = inicio + monto + numCaja + codresp + numTransac + tipoCuenta;
        String xo = NettyUtil.xor(NettyUtil.hex2a(trama));
        String tramaFinal = "02" + trama + xo;
        sendMessageToPOS(tramaFinal);
        return tramaFinal;

    }

    public String sendAnulacionToPos(String pId) {
        log.info("*****************************");
        log.info("***ENVIANDO DATA ANULACION***");
        log.info("*****************************");
        String inicio = "003536303030303030303030313030353030311C";
        String recibo = "34330006" + NettyUtil.asciiToHex(pId) + "1C";
        String codresp = "34380002" + "2020" + "03";
        String trama = inicio + recibo + codresp;
        String xo = NettyUtil.xor(NettyUtil.hex2a(trama));
        String tramaFinal = "02" + trama + xo;
        sendMessageToPOS(tramaFinal);
        return tramaFinal;
    }

    public void sendMessageToPOS(String msg) {
        if (vCtx == null)
            return;
        ByteBuf buf = vCtx.alloc().buffer();
        String data = NettyUtil.hex2a(msg);
        buf.writeCharSequence(data, CharsetUtil.UTF_8);
        vCtx.write(buf);
        vCtx.flush();
        log.info("[KIOSCO]:" + msg);
    }

    public void selectProcess(RequestDto pRequestDto) {
        if(vCtx == null){
            log.warn("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            log.warn("XXXXX NINGÚN DISPOSITIVO ENCONTRADO XXXXX");
            log.warn("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        } else {
            log.info("*******************************");
            log.info("********GENERANDO FLUJO********");
            log.info("*******************************");
            log.info("FLUJO STR:" + pRequestDto.getStrFlujo());
            log.info("FLUJO NUM:" + pRequestDto.getFlujo());
            vRequestDto = pRequestDto;
            switch (pRequestDto.getFlujo()) {
                case Constants.NUMBER_FLOW_INIT:
                    sendSolicitudInicializar();
                    break;
                case Constants.NUMBER_FLOW_CHIP:
                    sendConnectionChip();
                    break;
                case Constants.NUMBER_FLOW_CHIP_MULTI:
                    sendConnectionChipMulticom(pRequestDto.getIdComercio());
                    break;
                case Constants.NUMBER_FLOW_CTL:
                    sendConnectionCtl();
                    break;
                case Constants.NUMBER_FLOW_CTL_MULTI:
                    sendConnectionCtlMulticom(pRequestDto.getIdComercio());
                    break;
                case Constants.NUMBER_FLOW_DELETED:
                    sendSolicitudAnulacion();
                    break;
                case Constants.NUMBER_FLOW_DELETED_MULTI:
                    sendSolicitudAnulacionMulticom(pRequestDto.getIdComercio());
                    break;
                case Constants.NUMBER_FLOW_CLOSE:
                    sendSolicitudCierre();
                    break;
                case Constants.NUMBER_FLOW_CLOSE_MULTI:
                    sendSolicitudCierreMulticom(pRequestDto.getIdComercio());
                    break;
                default:
                    log.info("[FLUJO]: Flujo no definido!");
                    break;
            }
        }
    }

    public ResponseDto flujoChip(String pStrReply) {
        vResponseDto = new ResponseDto();
        switch (vRequestDto.getPaso()) {
            case 1: {
                if (isAck1 && vRequestDto.getTamaño() == 40) {
                    sendAck();
                    sendTransRevNo();
                    vRequestDto.setPaso(2);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_1);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck1 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }
            case 2: {
                if (isAck2 && vRequestDto.getTamaño() == 36) {
                    sendAck();
                    vRequestDto.setPaso(3);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_2);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck2 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }
            case 3: {
                if (vRequestDto.getTamaño() == 29) {
                    sendAck();
                    String tramaFinal = sendDataToPos(vRequestDto.getMontoTransaccion());
                    vRequestDto.setPaso(4);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_3);
                    vResponseDto.setData(tramaFinal);
                    break;
                } else {
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                }

            }
            case 4: {
                if (isAck4 && vRequestDto.getTamaño() == 36) {
                    sendAck();
                    vRequestDto.setPaso(5);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_4);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck4 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }
            case 5: {
                if (vRequestDto.getTamaño() >= 223) {
                    sendAck();
                    VentaDto vVentaDto = ResponseUtil.getRespuestaHostVenta(pStrReply);
                    vVentaDto.setFechaLocal(NettyUtil.getGlobalDate(vVentaDto.getFechaTransaccion(), vVentaDto.getHoraTransaccion()));
                    vVentaDto.setMontoCompra(vRequestDto.getMonto());
                    vResponseDto.setData(vVentaDto);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_FINAL);
                    vRequestDto.setPaso(-1);
                    vRequestDto.setTamaño(0);
                    isAck1 = false;
                    isAck2 = false;
                    isAck3 = false;
                    isAck4 = false;
                    vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                } else {
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                }
            }
            default:
                vResponseDto.setData(pStrReply);
                vResponseDto.setEstado(false);
                vResponseDto.setMensaje("Ningun paso válido");
                ServerHandler.statePos = Constants.STATE_REALIZADO;
                break;

        }
        return vResponseDto;
    }

    public ResponseDto flujoChipMulti(String pStrReply) {
        vResponseDto = new ResponseDto();
        switch (vRequestDto.getPaso()) {
            case 1: {
                if (isAck1 && vRequestDto.getTamaño() == 81) {
                    sendAck();
                    sendTransRevNo();
                    vRequestDto.setPaso(2);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_1);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck1 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }
            case 2: {
                if (isAck2 && vRequestDto.getTamaño() == 36) {
                    sendAck();
                    vRequestDto.setPaso(3);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_2);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck2 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }
            case 3: {
                if (vRequestDto.getTamaño() == 29) {
                    sendAck();
                    sendDataToPos(vRequestDto.getMontoTransaccion());
                    vRequestDto.setPaso(4);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_3);
                    vResponseDto.setData(null);
                    break;
                } else {
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(null);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                }
            }
            case 4: {
                if (isAck4 && vRequestDto.getTamaño() == 36) {
                    sendAck();
                    vRequestDto.setPaso(5);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_4);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck4 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }
            case 5: {
                if (vRequestDto.getTamaño() >= 223) {
                    sendAck();
                    vResponseDto.setData(ResponseUtil.getRespuestaHostVenta(pStrReply));
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_FINAL);
                    vRequestDto.setPaso(-1);
                    vRequestDto.setTamaño(0);
                    isAck1 = false;
                    isAck2 = false;
                    isAck3 = false;
                    isAck4 = false;
                    vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                } else {
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                }
            }
            default:
                vResponseDto.setData(pStrReply);
                vResponseDto.setEstado(false);
                vResponseDto.setMensaje(Constants.RES_NOT_VALID);
                ServerHandler.statePos = Constants.STATE_REALIZADO;
                break;

        }
        return vResponseDto;
    }

    public ResponseDto flujoCtl(String pStrReply) {
        vResponseDto = new ResponseDto();
        switch (vRequestDto.getPaso()) {
            case 1: {
                if (isAck1 && vRequestDto.getTamaño() == 40) {
                    sendAck();
                    sendTransRevNo();
                    vRequestDto.setPaso(2);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_1);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck1 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }
            case 2: {
                if (isAck2 && vRequestDto.getTamaño() == 29) {
                    sendAck();
                    String tramaf = sendDataToPos(vRequestDto.getMontoTransaccion());
                    vRequestDto.setPaso(3);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_2);
                    vResponseDto.setData(tramaf);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck2 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }
            case 3: {
                if (isAck3 && vRequestDto.getTamaño() == 36) {
                    sendAck();
                    sendTipoTarjetaCtl();
                    vRequestDto.setPaso(4);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_3);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck3 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_3);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }

            case 4: {
                if (isAck4 && vRequestDto.getTamaño() == 36) {
                    sendAck();
                    vRequestDto.setPaso(5);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_4);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck4 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }
            case 5: {
                if (vRequestDto.getTamaño() >= 222) {
                    sendAck();
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_FINAL);
                    vResponseDto.setData(ResponseUtil.getRespuestaHostVenta(pStrReply));
                    vRequestDto.setPaso(-1);
                    vRequestDto.setTamaño(0);
                    isAck1 = false;
                    isAck2 = false;
                    isAck3 = false;
                    isAck4 = false;
                    vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                } else {
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                }

            }
            default:
                vResponseDto.setEstado(false);
                vResponseDto.setMensaje(Constants.RES_NOT_VALID);
                ServerHandler.statePos = Constants.STATE_REALIZADO;
                break;

        }
        return vResponseDto;
    }

    public ResponseDto flujoCtlMulti(String pStrReply) {
        vResponseDto = new ResponseDto();
        switch (vRequestDto.getPaso()) {
            case 1: {
                if (isAck1 && vRequestDto.getTamaño() == 81) {
                    sendAck();
                    sendTransRevNo();
                    vRequestDto.setPaso(2);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_1);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck1 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }
            case 2: {
                if (isAck2 && vRequestDto.getTamaño() == 29) {
                    sendAck();
                    sendDataToPos(vRequestDto.getMontoTransaccion());
                    vRequestDto.setPaso(3);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_2);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck2 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }
            case 3: {
                if (isAck3 && vRequestDto.getTamaño() == 36) {
                    sendAck();
                    sendTipoTarjetaCtl();
                    vRequestDto.setPaso(4);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_3);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck3 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }

            case 4: {
                if (isAck4 && vRequestDto.getTamaño() == 36) {
                    sendAck();
                    vRequestDto.setPaso(5);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_4);
                    vResponseDto.setData(pStrReply);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck4 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    break;
                }
            }
            case 5: {
                if (vRequestDto.getTamaño() >= 222) {
                    sendAck();
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_FINAL);
                    vResponseDto.setData(ResponseUtil.getRespuestaHostVenta(pStrReply));
                    vRequestDto.setPaso(-1);
                    vRequestDto.setTamaño(0);
                    isAck1 = false;
                    isAck2 = false;
                    isAck3 = false;
                    isAck4 = false;
                    vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                } else {
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setData(pStrReply);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                }

            }
            default:
                vResponseDto.setEstado(false);
                vResponseDto.setMensaje(Constants.RES_NOT_VALID);
                vResponseDto.setData(pStrReply);
                ServerHandler.statePos = Constants.STATE_REALIZADO;
                break;

        }
        return vResponseDto;
    }

    public ResponseDto flujoAnulacion(String pStrReply) {
        vResponseDto = new ResponseDto();
        switch (vRequestDto.getPaso()) {
            case 1: {
                if (isAck1 && vRequestDto.getTamaño() >= 29) {
                    sendAck();
                    sendAnulacionToPos(vRequestDto.getReferenciaAnulacion());
                    vRequestDto.setPaso(2);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_1);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck1 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    break;
                }
            }
            case 2: {
                if (isAck2 && vRequestDto.getTamaño() == 29) {
                    sendAck();
                    sendConfirmarAnulacion();
                    vRequestDto.setPaso(3);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_2);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck2 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    break;
                }
            }
            case 3: {
                if (vRequestDto.getTamaño() >= 210) {
                    sendAck();
                    vResponseDto.setData(ResponseUtil.getRespuestaAnulacion(pStrReply));
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_FINAL);
                    vRequestDto.setPaso(-1);
                    vRequestDto.setTamaño(0);
                    isAck1 = false;
                    isAck2 = false;
                    isAck3 = false;
                    isAck4 = false;
                    vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(true);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                } else {
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                }
            }
            default: {
                vResponseDto.setData(pStrReply);
                vResponseDto.setEstado(false);
                vResponseDto.setMensaje(Constants.RES_NOT_VALID);
                ServerHandler.statePos = Constants.STATE_REALIZADO;
                break;
            }
        }
        return vResponseDto;
    }

    public ResponseDto flujoAnulacionMulti(String pStrReply) {
        vResponseDto = new ResponseDto();
        switch (vRequestDto.getPaso()) {
            case 1: {
                if (isAck1 && vRequestDto.getTamaño() >= 70) {
                    sendAck();
                    sendAnulacionToPos(vRequestDto.getReferenciaAnulacion());
                    vRequestDto.setPaso(2);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_1);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck1 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    break;
                }
            }
            case 2: {
                if (isAck2 && vRequestDto.getTamaño() == 29) {
                    sendAck();
                    sendConfirmarAnulacion();
                    vRequestDto.setPaso(3);
                    vRequestDto.setTamaño(0);
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_2);
                    break;
                } else if (isAck(pStrReply)) {
                    isAck2 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    break;
                }
            }
            case 3: {
                if (vRequestDto.getTamaño() >= 210) {
                    sendAck();
                    vResponseDto.setData(ResponseUtil.getRespuestaAnulacion(pStrReply));
                    vResponseDto.setEstado(true);
                    vResponseDto.setMensaje(Constants.RES_FINAL);
                    vRequestDto.setPaso(-1);
                    vRequestDto.setTamaño(0);
                    isAck1 = false;
                    isAck2 = false;
                    isAck3 = false;
                    isAck4 = false;
                    vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                } else {
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setEstado(false);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                }
            }
            default: {
                vResponseDto.setData(null);
                vResponseDto.setEstado(false);
                vResponseDto.setMensaje(Constants.RES_NOT_VALID);
                ServerHandler.statePos = Constants.STATE_REALIZADO;
                break;
            }
        }
        return vResponseDto;
    }

    public ResponseDto flujoCierre(String pStrReply) {
        vResponseDto = new ResponseDto();
        switch (vRequestDto.getPaso()) {
            case 1: {
                if (isAck1 && vRequestDto.getTamaño() == 38) {
                    sendAck();
                    String codr = pStrReply.substring(50, 54);
                    if (codr.equals("5858")) {
                        vRequestDto.setPaso(-1);
                        isAck1 = false;
                        isAck2 = false;
                        vResponseDto.setData(ResponseUtil.getRespuestaCierre(pStrReply));
                        vResponseDto.setMensaje(Constants.RES_FINAL);
                        vResponseDto.setEstado(true);
                        ServerHandler.statePos = Constants.STATE_REALIZADO;
                        break;
                    } else {
                        vRequestDto.setPaso(2);
                        vRequestDto.setTamaño(0);
                        vResponseDto.setData(pStrReply);
                        vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                        vResponseDto.setEstado(false);
                        break;
                    }
                } else if (isAck(pStrReply)) {
                    isAck1 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setData(null);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setEstado(false);
                    break;
                } else {
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setEstado(false);
                    vResponseDto.setData(null);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                }
            }
            case 2: {
                if (vRequestDto.getTamaño() > 140) {
                    sendAck();
                    vRequestDto.setTamaño(0);
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setMensaje(Constants.RES_2);
                    vResponseDto.setEstado(true);
                    break;
                } else if (vRequestDto.getTamaño() == 40) {
                    sendAck();
                    vResponseDto.setData(ResponseUtil.getRespuestaCierreTransaccion(pStrReply));
                    vResponseDto.setMensaje(Constants.RES_FINAL);
                    vResponseDto.setEstado(true);
                    vRequestDto.setTamaño(0);
                    vRequestDto.setPaso(-1);
                    isAck1 = false;
                    isAck2 = false;
                    vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                } else {
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setEstado(false);
                    vResponseDto.setData(pStrReply);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                }
            }
            default: {
                vResponseDto.setMensaje(Constants.RES_NOT_VALID);
                vResponseDto.setEstado(false);
                vResponseDto.setData(pStrReply);
                ServerHandler.statePos = Constants.STATE_REALIZADO;
                break;
            }

        }
        return vResponseDto;
    }

    public ResponseDto flujoCierreMulti(String pStrReply) {
        vResponseDto = new ResponseDto();
        switch (vRequestDto.getPaso()) {
            case 1: {
                if (isAck1 && vRequestDto.getTamaño() == 79) {
                    sendAck();
                    String codr = pStrReply.substring(50, 54);
                    if (codr.equals("5858")) {
                        vRequestDto.setPaso(-1);
                        isAck1 = false;
                        isAck2 = false;
                        vResponseDto.setData(ResponseUtil.getRespuestaCierre(pStrReply));
                        vResponseDto.setMensaje(Constants.RES_FINAL);
                        vResponseDto.setEstado(true);
                    } else {
                        vRequestDto.setPaso(2);
                        vRequestDto.setTamaño(0);
                        vResponseDto.setData(pStrReply);
                        vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                        vResponseDto.setEstado(false);
                    }
                    break;
                } else if (isAck(pStrReply)) {
                    isAck1 = true;
                    vRequestDto.setTamaño(0);
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setEstado(true);
                    break;
                } else {
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setEstado(false);
                    vResponseDto.setData(pStrReply);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                }
            }
            case 2: {
                if (vRequestDto.getTamaño() > 140) {
                    sendAck();
                    vRequestDto.setTamaño(0);
                    vResponseDto.setData(pStrReply);
                    vResponseDto.setMensaje(Constants.RES_NOT_VALID);
                    vResponseDto.setEstado(false);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                } else if (vRequestDto.getTamaño() == 40) {
                    sendAck();
                    vResponseDto.setData(ResponseUtil.getRespuestaCierreTransaccion(pStrReply));
                    vResponseDto.setMensaje(Constants.RES_FINAL);
                    vResponseDto.setEstado(true);
                    vRequestDto.setTamaño(0);
                    vRequestDto.setPaso(-1);
                    isAck1 = false;
                    isAck2 = false;
                    vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                    vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                } else {
                    vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                    vResponseDto.setEstado(false);
                    vResponseDto.setData(pStrReply);
                    ServerHandler.statePos = Constants.STATE_REALIZADO;
                    break;
                }
            }
            default:
                vResponseDto.setMensaje(Constants.RES_NOT_VALID);
                vResponseDto.setEstado(false);
                vResponseDto.setData(pStrReply);
                ServerHandler.statePos = Constants.STATE_REALIZADO;
                break;

        }
        return vResponseDto;
    }

    public ResponseDto flujoInicializar(String pStrReply) {
        vResponseDto = new ResponseDto();
        if (vRequestDto.getPaso() == 1) {
            if (isAck1 && vRequestDto.getTamaño() == 29) {
                sendAck();
                vResponseDto.setMensaje(Constants.RES_FINAL);
                vResponseDto.setEstado(true);
                vResponseDto.setData(ResponseUtil.getRespuestaInicializacion(pStrReply));
                vRequestDto.setTamaño(0);
                vRequestDto.setPaso(-1);
                isAck1 = false;
                isAck2 = false;
                vRequestDto.setFlujo(Constants.NUMBER_FLOW_NONE);
                vRequestDto.setStrFlujo(Constants.FLOW_NONE);
                ServerHandler.statePos = Constants.STATE_REALIZADO;
            } else if (isAck(pStrReply)) {
                isAck1 = true;
                vRequestDto.setTamaño(0);
                vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                vResponseDto.setEstado(false);
                vResponseDto.setData(pStrReply);
                return vResponseDto;
            } else {
                vResponseDto.setMensaje(Constants.RES_INCOMPLETE);
                vResponseDto.setEstado(false);
                vResponseDto.setData(pStrReply);
                ServerHandler.statePos = Constants.STATE_REALIZADO;
                return vResponseDto;
            }
        } else {
            vResponseDto.setMensaje(Constants.RES_NOT_VALID);
            vResponseDto.setEstado(false);
            vResponseDto.setData(pStrReply);
            ServerHandler.statePos = Constants.STATE_REALIZADO;
        }
        return vResponseDto;
    }

    public ResponseDto flujoNone(){
        vResponseDto = new ResponseDto();
        vResponseDto.setEstado(false);
        vResponseDto.setMensaje("Sin tipo de flujo definido");
        vResponseDto.setData(null);
        ServerHandler.statePos = Constants.STATE_REALIZADO;
        return vResponseDto;
    }

    public boolean isAck(String pStr) {
        return pStr.equals(ack);
    }
}
