package com.pgt360.payment.service;

import com.pgt360.payment.service.dto.dispositivo.DispositivoQueryDto;

public interface DispositivoService {
    public DispositivoQueryDto buscarDispositivo(int pDispositivoId);
}
