package com.pgt360.payment.service.impl;

import com.pgt360.payment.server.handler.ServerHandler;
import com.pgt360.payment.service.PagoService;
import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.service.dto.netty.ResponseDto;
import com.pgt360.payment.util.ConstantsUtil;
import com.pgt360.payment.util.NettyUtil;
import com.pgt360.payment.util.ServerCommunication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PagoServiceImpl implements PagoService {
    RequestDto vRequestDto = new RequestDto();
    @Override
    public ResponseDto payChipSingleCommerce(float pAmount) {
        ResponseDto vResponseDto = new ResponseDto();
        if(NettyUtil.isNaN(pAmount)){
            vResponseDto.setData(null);
            vResponseDto.setMensaje("El monto debe ser numero mayor a cero");
            vResponseDto.setEstado(false);
        }else {
            float montob = (float) ((Math.round(pAmount*100.0))/100.0);
            String montoBoB = NettyUtil.validarMonto(montob);
            this.vRequestDto = new RequestDto();
            vRequestDto.setFlujo(ConstantsUtil.NUMBER_FLOW_CHIP);
            vRequestDto.setStrFlujo(ConstantsUtil.FLOW_CHIP);
            vRequestDto.setMonto(montoBoB);
            vRequestDto.setPaso(1);
            vRequestDto.setTamaño(0);
            ServerHandler.selectProcess(vRequestDto);
            vResponseDto.setData(null);
            vResponseDto.setMensaje("Pago con chip inicializado correctamente");
            vResponseDto.setEstado(true);
        }
        return vResponseDto;
    }

    @Override
    public ResponseDto payChipMultiCommerce(float pAmount, int pCommerceId) {
        return null;
    }

    @Override
    public ResponseDto payContactlessSingleCommerce(float pAmount) {
        return null;
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
        ResponseDto vResponseDto = new ResponseDto();
        if(pConfirm != 1 && (Integer)pConfirm == null) {
            log.error("Inicialización no autorizada");
        } else {
            if((Integer)pCommerceId == null){
                log.error("Id comercio nulo");
            }else{
                RequestDto vRequestDto = new RequestDto();
                vRequestDto.setIdComercio(pCommerceId);
                vRequestDto.setConfirm(pConfirm);
                vRequestDto.setFlujo(1);
                ServerHandler.selectProcess(vRequestDto);
            }
        }
        return vResponseDto;
    }
}
