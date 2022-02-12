package com.pgt360.payment.service.impl;

import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.model.entity.Caja;
import com.pgt360.payment.model.repository.CajaRepository;
import com.pgt360.payment.service.CajaService;
import com.pgt360.payment.service.dto.caja.CajaQueryDto;
import com.pgt360.payment.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CajaServiceImpl implements CajaService {
    private final CajaRepository cajaRepository;

    public CajaServiceImpl(CajaRepository cajaRepository){
        this.cajaRepository = cajaRepository;
    }

    @Override
    public CajaQueryDto buscarCajaPorId(int pCajaId) {
        if((Integer)pCajaId == null){
            Object[] obj = {"Id "+pCajaId};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Caja vCaja = cajaRepository.getCajaByIdAndState((long)pCajaId, Constants.STATE_ACTIVE).orElse(null);
        if(vCaja == null){
            Object[] obj = {"Entidad Caja"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        CajaQueryDto vCajaQueryDto = new CajaQueryDto();
        BeanUtils.copyProperties(vCaja, vCajaQueryDto);
        return vCajaQueryDto;
    }
}
