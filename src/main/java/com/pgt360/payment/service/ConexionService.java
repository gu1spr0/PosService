package com.pgt360.payment.service;

import com.pgt360.payment.service.dto.conexion.ConexionAddDto;
import com.pgt360.payment.service.dto.conexion.ConexionQueryDto;
import com.pgt360.payment.service.dto.conexion.ConexionUpdateDto;

public interface ConexionService {
    public ConexionQueryDto agregarConexion(ConexionAddDto pConexionAddDto);
    public ConexionQueryDto modificarConexion(int pConexionId, ConexionUpdateDto pConexionUpdateDto);
    public ConexionQueryDto buscarConexion(int pConexionId);
    public ConexionQueryDto buscarConexionPorCodigo(String pCanalId);
    public void eliminarConexion(int pConexionId);

}
