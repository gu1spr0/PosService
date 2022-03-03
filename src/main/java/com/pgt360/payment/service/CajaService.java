package com.pgt360.payment.service;

import com.pgt360.payment.service.dto.caja.CajaAddDto;
import com.pgt360.payment.service.dto.caja.CajaQueryDto;
import com.pgt360.payment.service.dto.caja.CajaQueryPageableDto;
import com.pgt360.payment.service.dto.caja.CajaUpdateDto;

public interface CajaService {
    public CajaQueryPageableDto listarCajaPageable(String pEstado, int pPage, int pRow);
    public CajaQueryDto agregarCaja(CajaAddDto pCajaAddDto);
    public CajaQueryDto modificarCaja(int pCajaId, CajaUpdateDto pCajaUpdateDto);
    public CajaQueryDto buscarCaja(int pCajaId);
    public void eliminarCaja(int pCajaId);
}