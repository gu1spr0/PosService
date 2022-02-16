package com.pgt360.payment.service.impl;

import com.pgt360.payment.server.handler.ServerHandler;
import com.pgt360.payment.service.PagoService;
import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.service.dto.netty.ResponseDto;
import com.pgt360.payment.util.Constants;
import com.pgt360.payment.util.NettyUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
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
            //float montob = NettyUtil.redondearMonto(pAmount);
            //log.info("MONTO REDONDEADO:"+montob);
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
        synchronized (ServerHandler.vResponseDto){
            try{
                System.out.println("::NOMBRE DEL HILO ACTUAL::"+Thread.currentThread().getName());
                ServerHandler.vResponseDto.wait();
            }catch (Exception e){
                log.error(e.toString());
            }

        }
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
            //float montob = NettyUtil.redondearMonto(pAmount);
            //log.info("MONTO:"+montob);
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
            //float montob = NettyUtil.redondearMonto(pAmount);
            //log.info("MONTO:"+montob);
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
            //float montob = NettyUtil.redondearMonto(pAmount);
            //log.info("MONTO:"+montob);
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
        return ServerHandler.vResponseDto;
    }

    @Override
    public ResponseDto cancelTransactionSingleCommerce(String pTransaction) {
        this.vResponseDto = new ResponseDto();
        if(StringUtil.isNullOrEmpty(pTransaction)){
            this.vResponseDto.setData(null);
            this.vResponseDto.setMensaje("El identificador de transacción no es válido");
            this.vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = this.vResponseDto;
        }else{
            this.vRequestDto = new RequestDto();
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_DELETED);
            this.vRequestDto.setStrFlujo(Constants.FLOW_DELETED);
            this.vRequestDto.setReferenciaAnulacion(pTransaction);
            this.vRequestDto.setPaso(1);
            this.vRequestDto.setTamaño(0);
            ServerHandler.selectProcess(this.vRequestDto);
        }
        return ServerHandler.vResponseDto;
    }

    @Override
    public ResponseDto cancelTransactionMultiCommerce(String pTransaction, int pCommerceId) {
        this.vResponseDto = new ResponseDto();
        if(StringUtil.isNullOrEmpty(pTransaction)){
            this.vResponseDto.setData(null);
            this.vResponseDto.setMensaje("El identificador de transacción no es válido");
            this.vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = this.vResponseDto;
        }else{
            this.vRequestDto = new RequestDto();
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_DELETED_MULTI);
            this.vRequestDto.setStrFlujo(Constants.FLOW_DELETED_MULTI);
            this.vRequestDto.setReferenciaAnulacion(pTransaction);
            this.vRequestDto.setPaso(1);
            this.vRequestDto.setTamaño(0);
            this.vRequestDto.setIdComercio(pCommerceId);
            ServerHandler.selectProcess(this.vRequestDto);
        }
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
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_CLOSE);
            this.vRequestDto.setStrFlujo(Constants.FLOW_CLOSE);
            this.vRequestDto.setTamaño(0);
            this.vRequestDto.setPaso(1);
            this.vRequestDto.setConfirm(pConfirm);
            ServerHandler.selectProcess(this.vRequestDto);
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
            this.vRequestDto.setFlujo(Constants.NUMBER_FLOW_CLOSE_MULTI);
            this.vRequestDto.setStrFlujo(Constants.FLOW_CLOSE_MULTI);
            this.vRequestDto.setTamaño(0);
            this.vRequestDto.setPaso(1);
            this.vRequestDto.setConfirm(pConfirm);
            this.vRequestDto.setIdComercio(pCommerceId);
            ServerHandler.selectProcess(this.vRequestDto);
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
        }
        while (ServerHandler.vResponseDto.getMensaje().isEmpty()){
            log.info("Esperando tarea a completar..");
        }
        return ServerHandler.vResponseDto;
    }
}
