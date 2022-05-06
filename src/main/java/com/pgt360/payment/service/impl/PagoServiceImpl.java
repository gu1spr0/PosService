package com.pgt360.payment.service.impl;

import com.pgt360.payment.server.handler.ServerHandler;
import com.pgt360.payment.service.PagoService;
import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.service.dto.netty.ResponseDto;
import com.pgt360.payment.util.Constants;
import com.pgt360.payment.util.NettyUtil;
import com.pgt360.payment.util.ServerCom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PagoServiceImpl implements PagoService {
    Logger log = LoggerFactory.getLogger(PagoServiceImpl.class);
    ServerCom vServerCom = new ServerCom(ServerHandler.vCtx);
    @Override
    public ResponseDto payChipSingleCommerce(float pAmount) {
        ResponseDto vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pAmount)){
            vResponseDto.setData(null);
            vResponseDto.setMensaje("El monto debe ser numero mayor a cero");
            vResponseDto.setEstado(false);
        }else {
            String montoBoB = NettyUtil.validarMonto(pAmount);
            log.info("MONTO VALIDADO:"+montoBoB);
            RequestDto vRequestDto = new RequestDto();
            vRequestDto.setFlujo(Constants.NUMBER_FLOW_CHIP);
            vRequestDto.setStrFlujo(Constants.FLOW_CHIP);
            vRequestDto.setMontoTransaccion(montoBoB);
            vRequestDto.setMonto(pAmount);
            vRequestDto.setPaso(1);
            vRequestDto.setTamaño(0);
            vServerCom.selectProcess(vRequestDto);
            do {
                vResponseDto = ServerCom.vResponseDto;
            }
            while (ServerHandler.statePos);
        }
        return vResponseDto;
    }

    @Override
    public ResponseDto payChipMultiCommerce(float pAmount, int pCommerceId) {
        ResponseDto vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pAmount)){
            vResponseDto.setData(null);
            vResponseDto.setMensaje("El monto debe ser numero mayor a cero");
            vResponseDto.setEstado(false);
        }else {
            String montoBoB = NettyUtil.validarMonto(pAmount);
            log.info("MONTO VALIDADO:"+montoBoB);
            RequestDto vRequestDto = new RequestDto();
            vRequestDto.setFlujo(Constants.NUMBER_FLOW_CHIP_MULTI);
            vRequestDto.setStrFlujo(Constants.FLOW_CHIP_MULTI);
            vRequestDto.setMontoTransaccion(montoBoB);
            vRequestDto.setMonto(pAmount);
            vRequestDto.setPaso(1);
            vRequestDto.setTamaño(0);
            vRequestDto.setIdComercio(pCommerceId);
            vServerCom.selectProcess(vRequestDto);
            do {
                vResponseDto = ServerCom.vResponseDto;
            }
            while (ServerHandler.statePos);
        }
        return vResponseDto;
    }

    @Override
    public ResponseDto payContactlessSingleCommerce(float pAmount) {
        ResponseDto vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pAmount)){
            vResponseDto.setData(null);
            vResponseDto.setMensaje("El monto debe ser número mayor a cero");
            vResponseDto.setEstado(false);
        }else{
            String montoBoB = NettyUtil.validarMonto(pAmount);
            log.info("MONTO VALIDADO:"+montoBoB);
            RequestDto vRequestDto = new RequestDto();
            vRequestDto.setFlujo(Constants.NUMBER_FLOW_CTL);
            vRequestDto.setStrFlujo(Constants.FLOW_CTL);
            vRequestDto.setMontoTransaccion(montoBoB);
            vRequestDto.setMonto(pAmount);
            vRequestDto.setPaso(1);
            vRequestDto.setTamaño(0);
            vServerCom.selectProcess(vRequestDto);
            do {
                vResponseDto = ServerCom.vResponseDto;
            }
            while (ServerHandler.statePos);
        }
        return vResponseDto;
    }

    @Override
    public ResponseDto payContactlessMultiCommerce(float pAmount, int pCommerceId) {
        ResponseDto vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pAmount)){
            vResponseDto.setData(null);
            vResponseDto.setMensaje("El monto debe ser número mayor a cero");
            vResponseDto.setEstado(false);
        }else{
            String montoBoB = NettyUtil.validarMonto(pAmount);
            log.info("MONTO VALIDADO:"+montoBoB);
            RequestDto vRequestDto = new RequestDto();
            vRequestDto.setFlujo(Constants.NUMBER_FLOW_CTL_MULTI);
            vRequestDto.setStrFlujo(Constants.FLOW_CTL_MULTI);
            vRequestDto.setMontoTransaccion(montoBoB);
            vRequestDto.setMonto(pAmount);
            vRequestDto.setPaso(1);
            vRequestDto.setTamaño(0);
            vRequestDto.setIdComercio(pCommerceId);
            vServerCom.selectProcess(vRequestDto);
            do {
                vResponseDto = ServerCom.vResponseDto;
            }
            while (ServerHandler.statePos);
        }
        return vResponseDto;
    }

    @Override
    public ResponseDto cancelTransactionSingleCommerce(int pTransaction) {
        ResponseDto vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pTransaction)){
            vResponseDto.setData(null);
            vResponseDto.setMensaje("El numero de transacción debe ser un número mayor a cero");
            vResponseDto.setEstado(false);
        }else{
            String vRef = NettyUtil.validarRef(pTransaction);
            RequestDto vRequestDto = new RequestDto();
            vRequestDto.setFlujo(Constants.NUMBER_FLOW_DELETED);
            vRequestDto.setStrFlujo(Constants.FLOW_DELETED);
            vRequestDto.setReferenciaAnulacion(vRef);
            vRequestDto.setPaso(1);
            vRequestDto.setTamaño(0);
            vServerCom.selectProcess(vRequestDto);
            do {
                vResponseDto = ServerCom.vResponseDto;
            }
            while (ServerHandler.statePos);
        }
        return vResponseDto;
    }

    @Override
    public ResponseDto cancelTransactionMultiCommerce(int pTransaction, int pCommerceId) {
        ResponseDto vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pTransaction)){
            vResponseDto.setData(null);
            vResponseDto.setMensaje("El numero de transacción debe ser un número mayor a cero");
            vResponseDto.setEstado(false);
        }else{
            String vRef = NettyUtil.validarRef(pTransaction);
            RequestDto vRequestDto = new RequestDto();
            vRequestDto.setFlujo(Constants.NUMBER_FLOW_DELETED_MULTI);
            vRequestDto.setStrFlujo(Constants.FLOW_DELETED_MULTI);
            vRequestDto.setReferenciaAnulacion(vRef);
            vRequestDto.setPaso(1);
            vRequestDto.setTamaño(0);
            vRequestDto.setIdComercio(pCommerceId);
            vServerCom.selectProcess(vRequestDto);
            do {
                vResponseDto = ServerCom.vResponseDto;
            }
            while (ServerHandler.statePos);
        }
        return vResponseDto;
    }

    @Override
    public ResponseDto closeSingleCommerce(int pConfirm) {
        ResponseDto vResponseDto = new ResponseDto();
        if(pConfirm != 1){
            vResponseDto.setData(null);
            vResponseDto.setMensaje("El valor de confirmación no válido");
            vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = vResponseDto;
        }else{
            RequestDto vRequestDto = new RequestDto();
            vRequestDto.setFlujo(Constants.NUMBER_FLOW_CLOSE);
            vRequestDto.setStrFlujo(Constants.FLOW_CLOSE);
            vRequestDto.setTamaño(0);
            vRequestDto.setPaso(1);
            vRequestDto.setConfirm(pConfirm);
            vServerCom.selectProcess(vRequestDto);
            do {
                vResponseDto = ServerCom.vResponseDto;
            }
            while (ServerHandler.statePos);
        }
        return vResponseDto;
    }

    @Override
    public ResponseDto closeMultiCommerce(int pConfirm, int pCommerceId) {
        ResponseDto vResponseDto = new ResponseDto();
        if(pConfirm != 1){
            vResponseDto.setData(null);
            vResponseDto.setMensaje("El valor de confirmación no válido");
            vResponseDto.setEstado(false);
        }else{
            RequestDto vRequestDto = new RequestDto();
            vRequestDto.setFlujo(Constants.NUMBER_FLOW_CLOSE_MULTI);
            vRequestDto.setStrFlujo(Constants.FLOW_CLOSE_MULTI);
            vRequestDto.setTamaño(0);
            vRequestDto.setPaso(1);
            vRequestDto.setConfirm(pConfirm);
            vRequestDto.setIdComercio(pCommerceId);
            vServerCom.selectProcess(vRequestDto);
            do {
                vResponseDto = ServerCom.vResponseDto;
            }
            while (ServerHandler.statePos);
        }
        return vResponseDto;
    }

    @Override
    public ResponseDto initDevice(int pCommerceId, int pConfirm) {
        ResponseDto vResponseDto = new ResponseDto();
        if(pConfirm != 1) {
            vResponseDto.setEstado(false);
            vResponseDto.setMensaje("Inicialización no autorizada");
            vResponseDto.setData(null);
        } else {
            RequestDto vRequestDto = new RequestDto();
            vRequestDto.setIdComercio(pCommerceId);
            vRequestDto.setConfirm(pConfirm);
            vRequestDto.setFlujo(Constants.NUMBER_FLOW_INIT);
            vRequestDto.setStrFlujo(Constants.FLOW_INIT);
            vRequestDto.setTamaño(0);
            vRequestDto.setPaso(1);
            vServerCom.selectProcess(vRequestDto);
            do {
                vResponseDto = ServerCom.vResponseDto;
            }
            while (ServerHandler.statePos);
        }
        return vResponseDto;
    }
}
