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
import com.pgt360.payment.util.Constants;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        DispositivoQueryDto vDispositivoQueryDto = dispositivoService.buscarDispositivo(pConexionAddDto.getIdDispositivo());
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
    public ConexionQueryDto modificarConexionPorId(int pConexionId, ConexionUpdateDto pConexionUpdateDto) {
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
    public ConexionQueryDto modificarConexionPorCanal(String pCanalId) {
        if(StringUtil.isNullOrEmpty(pCanalId)){
            Object[] obj = {pCanalId};
            throw Message.GetBadRequest(MessageDescription.DataEmptyOrNull, obj);
        }
        Conexion vConexion = conexionRepository.getConexionByIdCanal(pCanalId).orElse(null);
        if(vConexion == null){
            Object[] obj = {vConexion};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        vConexion.setFechaDesconexion(new Date());
        vConexion.setEstado(Constants.STATE_INACTIVE);
        Conexion vNewConexion = conexionRepository.save(vConexion);
        ConexionQueryDto vConexionQueryDto = new ConexionQueryDto();
        BeanUtils.copyProperties(vNewConexion, vConexionQueryDto);
        return vConexionQueryDto;
    }

    @Override
    public ConexionQueryDto buscarConexion(int pConexionId) {
        if((Integer)pConexionId == null){
            Object[] obj = {"Id Conexion"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Conexion vConexion = conexionRepository.getConexionByIdAndState(pConexionId, Constants.STATE_ACTIVE).orElse(null);
        if(vConexion == null){
            Object[] obj = {"Objeto consulta Conexion"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        ConexionQueryDto vConexionQueryDto = new ConexionQueryDto();
        BeanUtils.copyProperties(vConexion, vConexionQueryDto);
        return vConexionQueryDto;
    }

    @Override
    public void verificarConexiones(String pIp) {
        if(StringUtil.isNullOrEmpty(pIp)){
            Object[] obj = {pIp};
            throw  Message.GetBadRequest(MessageDescription.DataEmptyOrNull);
        }
        List<Conexion> vConexionList = new ArrayList<>();
        vConexionList = conexionRepository.getConexionByDispositivoAndEstado(pIp, Constants.STATE_ACTIVE);
        if(vConexionList.size()>0){
            for(Conexion vConexion : vConexionList){
                vConexion.setFechaDesconexion(new Date());
                vConexion.setEstado(Constants.STATE_INACTIVE);
                conexionRepository.save(vConexion);
            }
        }

    }

    @Override
    public ConexionQueryDto buscarConexionPorCodigo(String pCanalId) {
        if(pCanalId == null){
            Object[] obj = {"Id de Canal"};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Conexion vConexion = conexionRepository.getConexionByIdCanalAndEstado(pCanalId, Constants.STATE_ACTIVE).orElse(null);
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
        vConexion.setEstado(Constants.STATE_INACTIVE);
        conexionRepository.save(vConexion);
    }
}
