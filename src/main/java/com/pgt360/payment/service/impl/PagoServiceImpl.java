package com.pgt360.payment.service.impl;

import com.pgt360.payment.server.handler.ServerHandler;
import com.pgt360.payment.service.PagoService;
import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.service.dto.netty.ResponseDto;
import com.pgt360.payment.util.Constants;
import com.pgt360.payment.util.NettyUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PagoServiceImpl implements PagoService {
    Logger log = LoggerFactory.getLogger(PagoServiceImpl.class);
    RequestDto vRequestDto = null;
    @Override
    public ResponseDto payChipSingleCommerce(float pAmount) {
        ResponseDto vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pAmount)){
            vResponseDto.setData(null);
            vResponseDto.setMensaje("El monto debe ser numero mayor a cero");
            vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = vResponseDto;
        }else {
            float montob = NettyUtil.redondearMonto(pAmount);
            System.out.println("MONTO:"+montob);
            String montoBoB = NettyUtil.validarMonto(montob);
            System.out.println("MONTO VALIDADO:"+montoBoB);
            this.vRequestDto = new RequestDto();
            vRequestDto.setFlujo(Constants.NUMBER_FLOW_CHIP);
            vRequestDto.setStrFlujo(Constants.FLOW_CHIP);
            vRequestDto.setMonto(montoBoB);
            vRequestDto.setPaso(1);
            vRequestDto.setTamaño(0);
            ServerHandler.selectProcess(vRequestDto);
        }
        return ServerHandler.vResponseDto;
    }

    @Override
    public ResponseDto payChipMultiCommerce(float pAmount, int pCommerceId) {
        return null;
    }

    @Override
    public ResponseDto payContactlessSingleCommerce(float pAmount) {
        ResponseDto vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pAmount)){
            vResponseDto.setData(null);
            vResponseDto.setMensaje("El monto debe ser número mayor a cero");
            vResponseDto.setEstado(false);
            ServerHandler.vResponseDto = vResponseDto;
        }else{
            float montob = NettyUtil.redondearMonto(pAmount);
            System.out.println("MONTO:"+montob);
            String montoBoB = NettyUtil.validarMonto(montob);
            System.out.println("MONTO VALIDADO:"+montoBoB);
            this.vRequestDto = new RequestDto();
            vRequestDto.setFlujo(Constants.NUMBER_FLOW_CTL);
            vRequestDto.setStrFlujo(Constants.FLOW_CTL);
            vRequestDto.setMonto(montoBoB);
            vRequestDto.setPaso(1);
            vRequestDto.setTamaño(0);
            ServerHandler.selectProcess(vRequestDto);
        }
        return ServerHandler.vResponseDto;
    }

    @Override
    public ResponseDto payContactlessMultiCommerce(float pAmount, int pCommerceId) {
        return null;
    }

    @Override
    public ResponseDto cancelTransactionSingleCommerce(String pTransaction) {
        return null;
    }

    @Override
    public ResponseDto cancelTransactionMultiCommerce(String pTransaction, int pCommerceId) {
        return null;
    }

    @Override
    public ResponseDto closeSingleCommerce(int pConfirm) {
        return null;
    }

    @Override
    public ResponseDto closeMultiCommerce(int pConfirm, int pCommerceId) {
        return null;
    }

    @Override
    public ResponseDto initDevice(int pCommerceId, int pConfirm) {
        log.info("Inicialización de Pos");
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
        return ServerHandler.vResponseDto;
    }
}
