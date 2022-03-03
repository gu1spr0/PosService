package com.pgt360.payment.service.impl;

import com.google.common.base.Strings;
import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.model.entity.Comercio;
import com.pgt360.payment.model.repository.ComercioRepository;
import com.pgt360.payment.service.ComercioService;
import com.pgt360.payment.service.dto.comercio.ComercioAddDto;
import com.pgt360.payment.service.dto.comercio.ComercioQueryDto;
import com.pgt360.payment.service.dto.comercio.ComercioQueryPageableDto;
import com.pgt360.payment.service.dto.comercio.ComercioUpdateDto;
import com.pgt360.payment.util.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComercioServiceImpl implements ComercioService {

    private final ComercioRepository comercioRepository;

    public ComercioServiceImpl(ComercioRepository comercioRepository){
        this.comercioRepository = comercioRepository;
    }

    @Override
    public ComercioQueryPageableDto listarComercioPaginado(String pState, int pPage, int pRowsNumber) {
        if(Strings.isNullOrEmpty(pState)){
            throw Message.GetBadRequest(MessageDescription.stateNullOrEmpty);
        }
        if(!(pState.equals(Constants.STATE_ACTIVE) || pState.equals(Constants.STATE_INACTIVE) || pState.equals(Constants.STATE_BLOCKED))){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        ComercioQueryPageableDto vComercioQueryPageableDto = new ComercioQueryPageableDto();
        Long vTotalRows =  comercioRepository.getCountComercioByState(pState);
        if(vTotalRows > 0){
            List<Comercio> vComercioList = comercioRepository.getComercioPageableByState(pState, PageRequest.of(pPage, pRowsNumber));
            List<ComercioQueryDto> vComercioQueryDtoList = new ArrayList<>();
            for(Comercio vComercio : vComercioList){
                ComercioQueryDto vComercioQueryDto = new ComercioQueryDto();
                BeanUtils.copyProperties(vComercio, vComercioQueryDto);
                vComercioQueryDtoList.add(vComercioQueryDto);
            }
            vComercioQueryPageableDto.setTotalRows(vTotalRows);
            vComercioQueryPageableDto.setComercioQueryDtoList(vComercioQueryDtoList);
        } else {
            vComercioQueryPageableDto.setComercioQueryDtoList(new ArrayList<>());
            vComercioQueryPageableDto.setTotalRows(0);
        }
        return vComercioQueryPageableDto;
    }

    @Override
    public ComercioQueryDto agregarComercio(ComercioAddDto pComercioAddDto) {
        if(pComercioAddDto == null){
            Object[] obj = {pComercioAddDto};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Comercio vComercio = new Comercio();
        BeanUtils.copyProperties(pComercioAddDto, vComercio);
        Comercio vNewComercio = comercioRepository.save(vComercio);
        if(vNewComercio == null){
            Object[] obj = {"Comercio"};
            throw Message.GetBadRequest(MessageDescription.notInserted, obj);
        }
        ComercioQueryDto vComercioQueryDto = new ComercioQueryDto();
        BeanUtils.copyProperties(vNewComercio, vComercioQueryDto);
        return vComercioQueryDto;
    }

    @Override
    public ComercioQueryDto modificarComercio(int pComercioId, ComercioUpdateDto pComercioUpdateDto) {
        if((Integer)pComercioId == null){
            Object[] obj = {"id comercio", pComercioId};
            throw Message.GetBadRequest(MessageDescription.PropertyNullOrEmpty, obj);
        }
        if(pComercioUpdateDto == null){
            Object[] obj = {"Comercio","id",pComercioId};
            throw Message.GetBadRequest(MessageDescription.DataEmptyOrNull, obj);
        }
        Comercio vComercio = comercioRepository.getComercioById(pComercioId).orElse(null);
        if(vComercio == null){
            Object[] obj = {"Comercio", "id", pComercioId};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        BeanUtils.copyProperties(pComercioUpdateDto, vComercio);
        Comercio vNewComercio = comercioRepository.save(vComercio);
        if(vNewComercio == null){
            Object[] obj = {"Comercio", "id", vComercio.getId()};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        ComercioQueryDto vComercioQueryDto = new ComercioQueryDto();
        BeanUtils.copyProperties(vNewComercio, vComercioQueryDto);
        return vComercioQueryDto;
    }

    @Override
    public ComercioQueryDto buscarComercio(int pComercioId) {
        if((Integer)pComercioId == null){
            Object[] obj = {"Comercio"};
            throw Message.GetBadRequest(MessageDescription.DataEmptyOrNull, obj);
        }

        Comercio vComercio = comercioRepository.getComercioById(pComercioId).orElse(null);

        if(vComercio == null){
            Object[] obj = {"Comercio","id",pComercioId};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        ComercioQueryDto vComercioQueryDto = new ComercioQueryDto();
        BeanUtils.copyProperties(vComercio, vComercioQueryDto);
        return vComercioQueryDto;
    }

    @Override
    public void eliminarComercio(int pComercioId) {
        if((Integer)pComercioId == null){
            Object[] obj = {"Id comercio",pComercioId};
            throw Message.GetBadRequest(MessageDescription.PropertyNullOrEmpty, obj);
        }
        ComercioQueryDto vComercioQueryDto = buscarComercio(pComercioId);
        vComercioQueryDto.setEstado(Constants.STATE_INACTIVE);
        Comercio vComercio = new Comercio();
        BeanUtils.copyProperties(vComercioQueryDto, vComercio);
        comercioRepository.save(vComercio);
    }
}
