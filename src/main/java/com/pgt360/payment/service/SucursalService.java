package com.pgt360.payment.service;


import com.pgt360.payment.service.dto.sucursal.SucursalComercioQueryDto;
import com.pgt360.payment.service.dto.sucursal.SucursalQueryDto;
import com.pgt360.payment.service.dto.sucursal.SucursalQueryPageableDto;

public interface SucursalService {
    public SucursalQueryPageableDto listarSucursalesPageable(String pEstado, int pPage, int pRow);
    public SucursalComercioQueryDto listarSucursalesPorComercioEstado(String pEstado, int pComercioId);
    public SucursalQueryDto buscarSucursalPorId(int pSucursalId);
    public void eliminarSucursal(int pSucursalId);
}
