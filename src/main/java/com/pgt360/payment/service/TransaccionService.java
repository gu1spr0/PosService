package com.pgt360.payment.service;

import com.pgt360.payment.service.dto.netty.ResponseDto;
import com.pgt360.payment.service.dto.transaccion.TransaccionAddDto;

public interface TransaccionService {
    ResponseDto agregarInicializar(TransaccionAddDto pTransaccionAddDto);

}
