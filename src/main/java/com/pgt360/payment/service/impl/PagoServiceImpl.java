package com.pgt360.payment.service.impl;

import com.pgt360.payment.server.handler.ServerHandler;
import com.pgt360.payment.service.PagoService;
import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.service.dto.netty.ResponseDto;
import com.pgt360.payment.util.Constants;
import com.pgt360.payment.util.NettyUtil;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PagoServiceImpl implements PagoService {
    Logger log = LoggerFactory.getLogger(PagoServiceImpl.class);
    RequestDto vRequestDto;
    ResponseDto vResponseDto;
    @Override
    public ResponseDto payChipSingleCommerce(float pAmount) {
        this.vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pAmount)){
            this.vResponseDto.setData(null);
            this.vResponseDto.setMensaje("El monto debe ser numero mayor a cero");
            this.vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = this.vResponseDto;
        }else {
            String montoBoB = NettyUtil.validarMonto(pAmount);
            log.info("MONTO VALIDADO:"+montoBoB);
            this.vRequestDto = new RequestDto();
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_CHIP);
            this.vRequestDto.setStrFlujo(Constants.FLOW_CHIP);
            this.vRequestDto.setMonto(montoBoB);
            this.vRequestDto.setPaso(1);
            this.vRequestDto.setTamaño(0);
            ServerHandler.selectProcess(this.vRequestDto);
        }

        log.info("Esperando Datos.......................");
        do {
            try{
                Thread.sleep(3000);
            }catch (InterruptedException ie){
                log.error(ie.getMessage());
            }
        }while (ServerHandler.statePos);
        return ServerHandler.vResponseDto;
    }

    @Override
    public ResponseDto payChipMultiCommerce(float pAmount, int pCommerceId) {
        this.vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pAmount)){
            this.vResponseDto.setData(null);
            this.vResponseDto.setMensaje("El monto debe ser numero mayor a cero");
            this.vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = this.vResponseDto;
        }else {
            String montoBoB = NettyUtil.validarMonto(pAmount);
            log.info("MONTO VALIDADO:"+montoBoB);
            this.vRequestDto = new RequestDto();
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_CHIP_MULTI);
            this.vRequestDto.setStrFlujo(Constants.FLOW_CHIP_MULTI);
            this.vRequestDto.setMonto(montoBoB);
            this.vRequestDto.setPaso(1);
            this.vRequestDto.setTamaño(0);
            this.vRequestDto.setIdComercio(pCommerceId);
            ServerHandler.selectProcess(this.vRequestDto);
        }
        do {
            try{
                Thread.sleep(3000);
            }catch (InterruptedException ie){
                log.error(ie.getMessage());
            }
        }while (ServerHandler.statePos);
        return ServerHandler.vResponseDto;
    }

    @Override
    public ResponseDto payContactlessSingleCommerce(float pAmount) {
        this.vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pAmount)){
            this.vResponseDto.setData(null);
            this.vResponseDto.setMensaje("El monto debe ser número mayor a cero");
            this.vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = this.vResponseDto;
        }else{
            String montoBoB = NettyUtil.validarMonto(pAmount);
            log.info("MONTO VALIDADO:"+montoBoB);
            this.vRequestDto = new RequestDto();
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_CTL);
            this.vRequestDto.setStrFlujo(Constants.FLOW_CTL);
            this.vRequestDto.setMonto(montoBoB);
            this.vRequestDto.setPaso(1);
            this.vRequestDto.setTamaño(0);
            ServerHandler.selectProcess(this.vRequestDto);
        }
        do {
            try{
                Thread.sleep(3000);
            }catch (InterruptedException ie){
                log.error(ie.getMessage());
            }
        }while (ServerHandler.statePos);
        return ServerHandler.vResponseDto;
    }

    @Override
    public ResponseDto payContactlessMultiCommerce(float pAmount, int pCommerceId) {
        this.vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pAmount)){
            this.vResponseDto.setData(null);
            this.vResponseDto.setMensaje("El monto debe ser número mayor a cero");
            this.vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = this.vResponseDto;
        }else{
            String montoBoB = NettyUtil.validarMonto(pAmount);
            log.info("MONTO VALIDADO:"+montoBoB);
            this.vRequestDto = new RequestDto();
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_CTL_MULTI);
            this.vRequestDto.setStrFlujo(Constants.FLOW_CTL_MULTI);
            this.vRequestDto.setMonto(montoBoB);
            this.vRequestDto.setPaso(1);
            this.vRequestDto.setTamaño(0);
            this.vRequestDto.setIdComercio(pCommerceId);
            ServerHandler.selectProcess(this.vRequestDto);
        }
        do {
            try{
                Thread.sleep(3000);
            }catch (InterruptedException ie){
                log.error(ie.getMessage());
            }
        }while (ServerHandler.statePos);
        return ServerHandler.vResponseDto;
    }

    @Override
    public ResponseDto cancelTransactionSingleCommerce(int pTransaction) {
        this.vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pTransaction)){
            this.vResponseDto.setData(null);
            this.vResponseDto.setMensaje("El numero de transacción debe ser un número mayor a cero");
            this.vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = this.vResponseDto;
        }else{
            String vRef = NettyUtil.validarRef(pTransaction);
            this.vRequestDto = new RequestDto();
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_DELETED);
            this.vRequestDto.setStrFlujo(Constants.FLOW_DELETED);
            this.vRequestDto.setReferenciaAnulacion(vRef);
            this.vRequestDto.setPaso(1);
            this.vRequestDto.setTamaño(0);
            ServerHandler.selectProcess(this.vRequestDto);
        }
        do {
            try{
                Thread.sleep(3000);
            }catch (InterruptedException ie){
                log.error(ie.getMessage());
            }
        }while (ServerHandler.statePos);
        return ServerHandler.vResponseDto;
    }

    @Override
    public ResponseDto cancelTransactionMultiCommerce(int pTransaction, int pCommerceId) {
        this.vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pTransaction)){
            this.vResponseDto.setData(null);
            this.vResponseDto.setMensaje("El numero de transacción debe ser un número mayor a cero");
            this.vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = this.vResponseDto;
        }else{
            String vRef = NettyUtil.validarRef(pTransaction);
            this.vRequestDto = new RequestDto();
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_DELETED_MULTI);
            this.vRequestDto.setStrFlujo(Constants.FLOW_DELETED_MULTI);
            this.vRequestDto.setReferenciaAnulacion(vRef);
            this.vRequestDto.setPaso(1);
            this.vRequestDto.setTamaño(0);
            this.vRequestDto.setIdComercio(pCommerceId);
            ServerHandler.selectProcess(this.vRequestDto);
        }
        do {
            try{
                Thread.sleep(3000);
            }catch (InterruptedException ie){
                log.error(ie.getMessage());
            }
        }while (ServerHandler.statePos);
        return ServerHandler.vResponseDto;
    }

    @Override
    public ResponseDto closeSingleCommerce(int pConfirm) {
        this.vResponseDto = new ResponseDto();
        if(pConfirm != 1){
            this.vResponseDto.setData(null);
            this.vResponseDto.setMensaje("El valor de confirmación no válido");
            this.vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = this.vResponseDto;
        }else{
            this.vRequestDto = new RequestDto();
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_CLOSE);
            this.vRequestDto.setStrFlujo(Constants.FLOW_CLOSE);
            this.vRequestDto.setTamaño(0);
            this.vRequestDto.setPaso(1);
            this.vRequestDto.setConfirm(pConfirm);
            ServerHandler.selectProcess(this.vRequestDto);
            do {
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException ie){
                    log.error(ie.getMessage());
                }
            }while (ServerHandler.statePos);
        }
        return ServerHandler.vResponseDto;
    }

    @Override
    public ResponseDto closeMultiCommerce(int pConfirm, int pCommerceId) {
        this.vResponseDto = new ResponseDto();
        if(pConfirm != 1){
            this.vResponseDto.setData(null);
            this.vResponseDto.setMensaje("El valor de confirmación no válido");
            this.vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = this.vResponseDto;
        }else{
            this.vRequestDto = new RequestDto();
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_CLOSE_MULTI);
            this.vRequestDto.setStrFlujo(Constants.FLOW_CLOSE_MULTI);
            this.vRequestDto.setTamaño(0);
            this.vRequestDto.setPaso(1);
            this.vRequestDto.setConfirm(pConfirm);
            this.vRequestDto.setIdComercio(pCommerceId);
            ServerHandler.selectProcess(this.vRequestDto);
            do {
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException ie){
                    log.error(ie.getMessage());
                }
            }while (ServerHandler.statePos);
        }
        return ServerHandler.vResponseDto;
    }

    @Override
    public ResponseDto initDevice(int pCommerceId, int pConfirm) {
        if(pConfirm != 1) {
            log.error("Inicialización no autorizada");
        } else {
            this.vRequestDto = new RequestDto();
            this.vRequestDto.setIdComercio(pCommerceId);
            this.vRequestDto.setConfirm(pConfirm);
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_INIT);
            this.vRequestDto.setStrFlujo(Constants.FLOW_INIT);
            this.vRequestDto.setTamaño(0);
            this.vRequestDto.setPaso(1);
            ServerHandler.selectProcess(this.vRequestDto);

            do {
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException ie){
                    log.error(ie.getMessage());
                }
            }while (ServerHandler.statePos);
        }
        return ServerHandler.vResponseDto;
    }
}
