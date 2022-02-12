package com.pgt360.payment.service.impl;

import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.model.entity.Caja;
import com.pgt360.payment.model.entity.Flujo;
import com.pgt360.payment.model.repository.FlujoRepository;
import com.pgt360.payment.service.CajaService;
import com.pgt360.payment.service.FlujoService;
import com.pgt360.payment.service.dto.caja.CajaQueryDto;
import com.pgt360.payment.service.dto.flujo.FlujoAddDto;
import com.pgt360.payment.service.dto.flujo.FlujoQueryDto;
import com.pgt360.payment.service.dto.flujo.FlujoUpdateDto;
import com.pgt360.payment.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FlujoServiceImpl implements FlujoService {
    private final FlujoRepository flujoRepository;

    private final CajaService cajaService;

    public FlujoServiceImpl(FlujoRepository flujoRepository, CajaService cajaService){
        this.flujoRepository = flujoRepository;
        this.cajaService = cajaService;
    }

    @Override
    public FlujoQueryDto agregarFlujo(FlujoAddDto pFlujoAddDto) {
        if(pFlujoAddDto == null){
            Object[] obj = {"Objeto agregar flujo"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Flujo vFlujo = new Flujo();
        BeanUtils.copyProperties(pFlujoAddDto, vFlujo);
        CajaQueryDto vCajaQueryDto = cajaService.buscarCajaPorId(pFlujoAddDto.getCaja());
        Caja vCaja = new Caja();
        BeanUtils.copyProperties(vCajaQueryDto, vCaja);
        vFlujo.setCaja(vCaja);
        Flujo vNewFlujo = flujoRepository.save(vFlujo);
        if(vNewFlujo == null){
            Object[] obj = {"Nuevo Flujo"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        FlujoQueryDto vFlujoQueryDto = new FlujoQueryDto();
        BeanUtils.copyProperties(vNewFlujo, vFlujoQueryDto);
        return vFlujoQueryDto;

    }

    @Override
    public FlujoQueryDto buscarFlujo(int pFlujoId) {
        if((Integer)pFlujoId==null){
            Object[] obj = {"Id Flujo"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Flujo vFlujo = flujoRepository.getFlujoByIdAndState(pFlujoId, Constants.STATE_ACTIVE).orElse(null);
        if(vFlujo == null){
            Object[] obj = {"Consulta objet Flujo"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        FlujoQueryDto vFlujoQueryDto = new FlujoQueryDto();
        BeanUtils.copyProperties(vFlujo, vFlujoQueryDto);
        vFlujoQueryDto.setCaja(vFlujo.getCaja().getId());
        return vFlujoQueryDto;
    }

    @Override
    public FlujoQueryDto modificarFlujo(int pFlujoId, FlujoUpdateDto pFlujoUpdateDto) {
        if((Integer)pFlujoId == null){
            Object[] obj = {"Id Flujo"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        if(pFlujoUpdateDto == null){
            Object[] obj = {"Objeto modificacion Flujo"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        FlujoQueryDto vFlujoQueryDto = this.buscarFlujo(pFlujoId);
        Flujo vFlujo = new Flujo();
        BeanUtils.copyProperties(vFlujoQueryDto, vFlujo);
        BeanUtils.copyProperties(pFlujoUpdateDto, vFlujo);
        CajaQueryDto vCajaQueryDto = cajaService.buscarCajaPorId(pFlujoUpdateDto.getCaja());
        Caja vCaja = new Caja();
        BeanUtils.copyProperties(vCajaQueryDto, vCaja);
        vFlujo.setCaja(vCaja);
        Flujo vUpdateFlujo = flujoRepository.save(vFlujo);
        if(vUpdateFlujo == null){
            Object[] obj = {"Objeto modificacion Flujo"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        FlujoQueryDto vUpdateFlujoQueryDto = new FlujoQueryDto();
        BeanUtils.copyProperties(vUpdateFlujo, vUpdateFlujoQueryDto);
        return vUpdateFlujoQueryDto;
    }

    @Override
    public void eliminarFlujo(int pFlujoId) {
        if((Integer)pFlujoId==null){
            Object[] obj = {"Id: "+pFlujoId};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        FlujoQueryDto vFlujoQueryDto = this.buscarFlujo(pFlujoId);
        if(vFlujoQueryDto == null){
            Object[] obj = {"Flujo"};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        Flujo vFlujo = new Flujo();
        BeanUtils.copyProperties(vFlujoQueryDto, vFlujo);
        vFlujo.setEstado(Constants.STATE_INACTIVE);
        flujoRepository.save(vFlujo);

    }
}
