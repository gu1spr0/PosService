package com.pgt360.payment.service.impl;

import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.model.entity.Conexion;
import com.pgt360.payment.model.entity.Dispositivo;
import com.pgt360.payment.model.repository.ConexionRepository;
import com.pgt360.payment.service.ConexionService;
import com.pgt360.payment.service.DispositivoService;
import com.pgt360.payment.service.dto.conexion.ConexionAddDto;
import com.pgt360.payment.service.dto.conexion.ConexionQueryDto;
import com.pgt360.payment.service.dto.conexion.ConexionUpdateDto;
import com.pgt360.payment.service.dto.dispositivo.DispositivoQueryDto;
import com.pgt360.payment.util.ConstantsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConexionServiceImpl implements ConexionService {
    private final ConexionRepository conexionRepository;

    private final DispositivoService dispositivoService;
    public ConexionServiceImpl(ConexionRepository conexionRepository, DispositivoService dispositivoService){
        this.conexionRepository = conexionRepository;
        this.dispositivoService = dispositivoService;
    }
    @Override
    public ConexionQueryDto agregarConexion(ConexionAddDto pConexionAddDto) {
        if(pConexionAddDto == null){
            Object[] obj = {"Objeto Conexion"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Conexion vConexion = new Conexion();
        BeanUtils.copyProperties(pConexionAddDto, vConexion);
        DispositivoQueryDto vDispositivoQueryDto = dispositivoService.buscarDispositivo(pConexionAddDto.getDispositivo());
        if(vDispositivoQueryDto == null){
            Object[] obj = {"Objeto Dispositivo"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Dispositivo vDispositivo = new Dispositivo();
        BeanUtils.copyProperties(vDispositivoQueryDto, vDispositivo);
        vConexion.setDispositivo(vDispositivo);
        Conexion vNewConexion = conexionRepository.save(vConexion);

        ConexionQueryDto vConexionQueryDto = new ConexionQueryDto();
        BeanUtils.copyProperties(vNewConexion, vConexionQueryDto);
        return vConexionQueryDto;

    }

    @Override
    public ConexionQueryDto modificarConexion(int pConexionId, ConexionUpdateDto pConexionUpdateDto) {
        if((Integer)pConexionId==null){
            Object[] obj = {"Id:"+pConexionId};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        if(pConexionUpdateDto==null){
            Object[] obj = {"Objeto Conexion"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }

        ConexionQueryDto vConexionQueryDto = this.buscarConexion(pConexionId);
        Conexion vConexion = new Conexion();
        BeanUtils.copyProperties(vConexionQueryDto, vConexion);
        BeanUtils.copyProperties(pConexionUpdateDto, vConexion);
        Conexion vUpdateConexion = conexionRepository.save(vConexion);
        vConexionQueryDto = new ConexionQueryDto();
        BeanUtils.copyProperties(vUpdateConexion, vConexionQueryDto);
        return vConexionQueryDto;
    }

    @Override
    public ConexionQueryDto buscarConexion(int pConexionId) {
        if((Integer)pConexionId == null){
            Object[] obj = {"Id Conexion"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Conexion vConexion = conexionRepository.getConexionByIdAndState(pConexionId, ConstantsUtil.STATE_ACTIVE).orElse(null);
        if(vConexion == null){
            Object[] obj = {"Objeto consulta Conexion"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        ConexionQueryDto vConexionQueryDto = new ConexionQueryDto();
        BeanUtils.copyProperties(vConexion, vConexionQueryDto);
        return vConexionQueryDto;
    }

    @Override
    public ConexionQueryDto buscarConexionPorCodigo(String pCanalId) {
        if(pCanalId == null){
            Object[] obj = {"Id de Canal"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Conexion vConexion = conexionRepository.getConexioByIdCanalAndEstado(pCanalId, ConstantsUtil.STATE_ACTIVE).orElse(null);
        if(vConexion == null){
            Object[] obj = {"Objeto consulta Conexion"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        ConexionQueryDto vConexionQueryDto = new ConexionQueryDto();
        BeanUtils.copyProperties(vConexion, vConexionQueryDto);
        return vConexionQueryDto;
    }

    @Override
    public void eliminarConexion(int pConexionId) {
        if((Integer)pConexionId == null){
            Object[] obj = {"Id Conexion"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        ConexionQueryDto vConexionQueryDto = this.buscarConexion(pConexionId);
        Conexion vConexion = new Conexion();
        BeanUtils.copyProperties(vConexionQueryDto, vConexion);
        vConexion.setEstado(ConstantsUtil.STATE_INACTIVE);
        conexionRepository.save(vConexion);
    }
}
