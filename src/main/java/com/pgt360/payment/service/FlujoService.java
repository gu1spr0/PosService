package com.pgt360.payment.service;

import com.pgt360.payment.service.dto.flujo.FlujoAddDto;
import com.pgt360.payment.service.dto.flujo.FlujoQueryDto;
import com.pgt360.payment.service.dto.flujo.FlujoUpdateDto;

public interface FlujoService {
    public FlujoQueryDto agregarFlujo(FlujoAddDto pFlujoAddDto);
    public FlujoQueryDto buscarFlujo(int pFlujoId);
    public FlujoQueryDto modificarFlujo(int pFlujoId, FlujoUpdateDto pFlujoUpdateDto);
    public void eliminarFlujo(int pFlujoId);
}
