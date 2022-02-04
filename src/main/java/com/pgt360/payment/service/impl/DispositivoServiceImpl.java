package com.pgt360.payment.service.impl;

import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.model.entity.Dispositivo;
import com.pgt360.payment.model.repository.DispositivoRepository;
import com.pgt360.payment.service.DispositivoService;
import com.pgt360.payment.service.dto.dispositivo.DispositivoQueryDto;
import com.pgt360.payment.util.ConstantsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DispositivoServiceImpl implements DispositivoService {
    private final DispositivoRepository dispositivoRepository;
    public DispositivoServiceImpl(DispositivoRepository dispositivoRepository){
        this.dispositivoRepository = dispositivoRepository;
    }
    @Override
    public DispositivoQueryDto buscarDispositivo(int pDispositivoId) {
        if((Integer)pDispositivoId==null){
            Object[] obj = {"Id:"+pDispositivoId};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Dispositivo vDispositivo = dispositivoRepository.getDispositivoByIdAndState(pDispositivoId, ConstantsUtil.STATE_ACTIVE).orElse(null);
        if(vDispositivo == null){
            Object[] obj = {vDispositivo};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        DispositivoQueryDto vDispositivoQueryDto = new DispositivoQueryDto();
        BeanUtils.copyProperties(vDispositivo,vDispositivoQueryDto);
        return vDispositivoQueryDto;

    }
}
