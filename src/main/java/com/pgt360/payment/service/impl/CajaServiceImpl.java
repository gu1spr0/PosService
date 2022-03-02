package com.pgt360.payment.service.impl;

import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.model.entity.Caja;
import com.pgt360.payment.model.repository.CajaRepository;
import com.pgt360.payment.service.CajaService;
import com.pgt360.payment.service.dto.caja.CajaAddDto;
import com.pgt360.payment.service.dto.caja.CajaQueryDto;
import com.pgt360.payment.service.dto.caja.CajaUpdateDto;
import com.pgt360.payment.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CajaServiceImpl implements CajaService {

    private final CajaRepository cajaRepository;

    public CajaServiceImpl(CajaRepository cajaRepository){
        this.cajaRepository = cajaRepository;
    }

    @Override
    public CajaQueryDto agregarCaja(CajaAddDto pCajaAddDto) {
        if(pCajaAddDto == null){
            Object[] obj = {"Caja"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Caja vCaja = new Caja();
        BeanUtils.copyProperties(pCajaAddDto, vCaja);
        Caja vCajaNew = new Caja();
        vCajaNew = cajaRepository.save(vCaja);
        if(vCajaNew == null){
            Object[] obj = {"Caja Nuevo"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        CajaQueryDto vCajaQueryDto = new CajaQueryDto();
        BeanUtils.copyProperties(vCajaNew, vCajaQueryDto);
        return vCajaQueryDto;
    }

    @Override
    public CajaQueryDto modificarCaja(int pCajaId, CajaUpdateDto pCajaUpdateDto) {
        return null;
    }

    @Override
    public CajaQueryDto buscarCaja(int pCajaId) {
        if((Integer) pCajaId == null){
            Object[] obj = {"Id Caja"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Caja vCaja = cajaRepository.getCajaByIdAndState(pCajaId, Constants.STATE_ACTIVE).orElse(null);
        if(vCaja == null){
            Object[] obj = {pCajaId};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        CajaQueryDto vCajaQueryDto = new CajaQueryDto();
        BeanUtils.copyProperties(vCaja, vCajaQueryDto);
        return vCajaQueryDto;
    }

    @Override
    public void eliminarCaja(int pCajaId) {

    }
}
