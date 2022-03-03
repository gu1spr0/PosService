package com.pgt360.payment.service.impl;

import com.google.common.base.Strings;
import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.model.entity.Comercio;
import com.pgt360.payment.model.entity.Sucursal;
import com.pgt360.payment.model.repository.SucursalRepository;
import com.pgt360.payment.service.ComercioService;
import com.pgt360.payment.service.SucursalService;
import com.pgt360.payment.service.dto.comercio.ComercioQueryDto;
import com.pgt360.payment.service.dto.sucursal.SucursalComercioQueryDto;
import com.pgt360.payment.service.dto.sucursal.SucursalQueryDto;
import com.pgt360.payment.service.dto.sucursal.SucursalQueryPageableDto;
import com.pgt360.payment.util.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SucursalServiceImpl implements SucursalService {
    private final SucursalRepository sucursalRepository;

    private final ComercioService comercioService;
    public SucursalServiceImpl(SucursalRepository sucursalRepository,
                               ComercioService comercioService){
        this.sucursalRepository = sucursalRepository;
        this.comercioService = comercioService;
    }
    @Override
    public SucursalQueryPageableDto listarSucursalesPageable(String pEstado, int pPage, int pRow) {
        if(Strings.isNullOrEmpty(pEstado)){
            throw Message.GetBadRequest(MessageDescription.stateNullOrEmpty);
        }
        if(!(pEstado.equals(Constants.STATE_ACTIVE) || pEstado.equals(Constants.STATE_INACTIVE) || pEstado.equals(Constants.STATE_BLOCKED))){
            Object[] obj = {pEstado};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        SucursalQueryPageableDto vSucursalQueryPageableDto = new SucursalQueryPageableDto();
        Long vTotalRows =  sucursalRepository.getCountSucursalByState(pEstado);
        if(vTotalRows > 0){
            List<Sucursal> vSucursalList = sucursalRepository.getSucursalPageableByState(pEstado, PageRequest.of(pPage, pRow));
            List<SucursalQueryDto> vSucursalQueryDtoList = new ArrayList<>();
            for(Sucursal vSucursal : vSucursalList){
                SucursalQueryDto vSucursalQueryDto = new SucursalQueryDto();
                BeanUtils.copyProperties(vSucursal, vSucursalQueryDto);
                vSucursalQueryDtoList.add(vSucursalQueryDto);
            }
            vSucursalQueryPageableDto.setTotalRows(vTotalRows);
            vSucursalQueryPageableDto.setSucursalQueryDtoList(vSucursalQueryDtoList);
        } else {
            vSucursalQueryPageableDto.setSucursalQueryDtoList(new ArrayList<>());
            vSucursalQueryPageableDto.setTotalRows(0);
        }
        return vSucursalQueryPageableDto;
    }

    @Override
    public SucursalComercioQueryDto listarSucursalesPorComercioEstado(String pEstado, int pComercioId) {
        if(Strings.isNullOrEmpty(pEstado)){
            throw Message.GetBadRequest(MessageDescription.stateNullOrEmpty);
        }
        if(!(pEstado.equals(Constants.STATE_ACTIVE) || pEstado.equals(Constants.STATE_INACTIVE) || pEstado.equals(Constants.STATE_BLOCKED))){
            Object[] obj = {pEstado};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        if((Integer)pComercioId == null){
            Object[] obj = {"id comercio", pComercioId};
            throw Message.GetBadRequest(MessageDescription.PropertyNullOrEmpty, obj);
        }
        List<Sucursal> vSucursalList = sucursalRepository.getSucursalsByStateAndComercio(pEstado, pComercioId);
        if(vSucursalList == null){
            Object[] obj = {"Sucursal"};
            throw Message.GetBadRequest(MessageDescription.DataEmptyOrNull, obj);
        }
        Long vTotalRows = sucursalRepository.getCountSucursalByStateAndComercio(pEstado, pComercioId);
        SucursalComercioQueryDto vSucursalComercioQueryDto = new SucursalComercioQueryDto();
        List<SucursalQueryDto> vSucursalQueryDtoList = new ArrayList<>();
        if(vTotalRows > 0){

            for(Sucursal vSucursal : vSucursalList){
                SucursalQueryDto vSucursalQueryDto = new SucursalQueryDto();
                BeanUtils.copyProperties(vSucursal, vSucursalQueryDto);
                vSucursalQueryDto.setIdComercio(vSucursal.getComercio().getId());
                vSucursalQueryDtoList.add(vSucursalQueryDto);
            }
            vSucursalComercioQueryDto.setTotalRows(vTotalRows);
            vSucursalComercioQueryDto.setSucursalQueryDtoList(vSucursalQueryDtoList);
        } else {
            vSucursalComercioQueryDto.setTotalRows(0);
            vSucursalComercioQueryDto.setSucursalQueryDtoList(vSucursalQueryDtoList);
        }
        return vSucursalComercioQueryDto;
    }

    @Override
    public SucursalQueryDto buscarSucursalPorId(int pSucursalId) {
        if((Integer)pSucursalId == null){
            Object[] obj = {"id sucursal", pSucursalId};
            throw Message.GetBadRequest(MessageDescription.PropertyNullOrEmpty, obj);
        }
        Sucursal vSucursal = sucursalRepository.getSucursalById(pSucursalId).orElse(null);
        if(vSucursal == null){
            Object[] obj = {"Sucursal"};
            throw Message.GetBadRequest(MessageDescription.DataEmptyOrNull, obj);
        }
        SucursalQueryDto vSucursalQueryDto = new SucursalQueryDto();
        BeanUtils.copyProperties(vSucursal, vSucursalQueryDto);
        vSucursalQueryDto.setIdComercio(vSucursal.getComercio().getId());
        return vSucursalQueryDto;
    }

    @Override
    public void eliminarSucursal(int pSucursalId) {
        SucursalQueryDto vSucursalQueryDto = buscarSucursalPorId(pSucursalId);
        Sucursal vSucursal = new Sucursal();
        BeanUtils.copyProperties(vSucursalQueryDto, vSucursal);
        ComercioQueryDto vComercioQueryDto = comercioService.buscarComercio(vSucursalQueryDto.getIdComercio());
        Comercio vComercio = new Comercio();
        BeanUtils.copyProperties(vComercioQueryDto, vComercio);
        vSucursal.setComercio(vComercio);
        vSucursal.setEstado(Constants.STATE_INACTIVE);
        Sucursal vNewSucursal = sucursalRepository.save(vSucursal);
        if(vNewSucursal == null){
            Object[] obj = {"Sucursal"};
            throw Message.GetBadRequest(MessageDescription.DataEmptyOrNull, obj);
        }
    }
}
