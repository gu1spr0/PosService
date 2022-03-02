package com.pgt360.payment.service.impl;

import com.google.common.base.Strings;
import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.model.entity.Caja;
import com.pgt360.payment.model.entity.Comercio;
import com.pgt360.payment.model.repository.CajaRepository;
import com.pgt360.payment.service.CajaService;
import com.pgt360.payment.service.dto.caja.CajaAddDto;
import com.pgt360.payment.service.dto.caja.CajaQueryDto;
import com.pgt360.payment.service.dto.caja.CajaQueryPageableDto;
import com.pgt360.payment.service.dto.caja.CajaUpdateDto;
import com.pgt360.payment.service.dto.comercio.ComercioQueryDto;
import com.pgt360.payment.service.dto.comercio.ComercioQueryPageableDto;
import com.pgt360.payment.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CajaServiceImpl implements CajaService {

    private final CajaRepository cajaRepository;

    public CajaServiceImpl(CajaRepository cajaRepository){
        this.cajaRepository = cajaRepository;
    }

    @Override
    public CajaQueryPageableDto listarCajaPageable(String pEstado, int pPage, int pRow) {
        if(Strings.isNullOrEmpty(pEstado)){
            throw Message.GetBadRequest(MessageDescription.stateNullOrEmpty);
        }
        if(!(pEstado.equals(Constants.STATE_ACTIVE) || pEstado.equals(Constants.STATE_INACTIVE) || pEstado.equals(Constants.STATE_BLOCKED))){
            Object[] obj = {pEstado};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        CajaQueryPageableDto vCajaQueryPageableDto = new CajaQueryPageableDto();
        Long vTotalRows =  cajaRepository.getCountCajaByState(pEstado);
        if(vTotalRows > 0){
            List<Caja> vCajaList = cajaRepository.getCajaPageableByState(pEstado, PageRequest.of(pPage, pRow));
            List<CajaQueryDto> vCajaQueryDtoList = new ArrayList<>();
            for(Caja vCaja : vCajaList){
                CajaQueryDto vCajaQueryDto = new CajaQueryDto();
                BeanUtils.copyProperties(vCaja, vCajaQueryDto);
                vCajaQueryDtoList.add(vCajaQueryDto);
            }
            vCajaQueryPageableDto.setTotalRows(vTotalRows);
            vCajaQueryPageableDto.setCajaQueryDtoList(vCajaQueryDtoList);
        } else {
            vCajaQueryPageableDto.setCajaQueryDtoList(new ArrayList<>());
            vCajaQueryPageableDto.setTotalRows(0);
        }
        return vCajaQueryPageableDto;
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
