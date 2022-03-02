package com.pgt360.payment.service.impl;

import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.model.entity.Comercio;
import com.pgt360.payment.model.repository.ComercioRepository;
import com.pgt360.payment.service.ComercioService;
import com.pgt360.payment.service.dto.comercio.ComercioAddDto;
import com.pgt360.payment.service.dto.comercio.ComercioQueryDto;
import com.pgt360.payment.service.dto.comercio.ComercioUpdateDto;
import com.pgt360.payment.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ComercioServiceImpl implements ComercioService {

    private final ComercioRepository comercioRepository;

    public ComercioServiceImpl(ComercioRepository comercioRepository){
        this.comercioRepository = comercioRepository;
    }
    @Override
    public ComercioQueryDto agregarComercio(ComercioAddDto pComercioAddDto) {
        if(pComercioAddDto == null){
            Object[] obj = {pComercioAddDto};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Comercio vComercio = new Comercio();
        return null;
    }

    @Override
    public ComercioQueryDto modificarComercio(int pComercioId, ComercioUpdateDto pComercioUpdateDto) {
        return null;
    }

    @Override
    public ComercioQueryDto buscarComercio(int pComercioId) {
        if((Integer)pComercioId == null){
            Object[] obj = {pComercioId};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Comercio vComercio = comercioRepository.getComercioByIdAndState(pComercioId, Constants.STATE_ACTIVE).orElse(null);

        if(vComercio == null){
            Object[] obj = {vComercio};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        ComercioQueryDto vComercioQueryDto = new ComercioQueryDto();
        BeanUtils.copyProperties(vComercio, vComercioQueryDto);
        return vComercioQueryDto;
    }

    @Override
    public void eliminarComercio(int pComercioId) {

    }
}
