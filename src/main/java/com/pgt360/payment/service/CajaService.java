package com.pgt360.payment.service;

import com.pgt360.payment.service.dto.caja.CajaQueryDto;

public interface CajaService {
    public CajaQueryDto buscarCajaPorId(int pCajaId);
}
