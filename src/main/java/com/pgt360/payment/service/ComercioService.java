package com.pgt360.payment.service;


import com.pgt360.payment.service.dto.comercio.ComercioAddDto;
import com.pgt360.payment.service.dto.comercio.ComercioQueryDto;
import com.pgt360.payment.service.dto.comercio.ComercioUpdateDto;

public interface ComercioService {
    public ComercioQueryDto agregarComercio(ComercioAddDto pComercioAddDto);
    public ComercioQueryDto modificarComercio(int pComercioId, ComercioUpdateDto pComercioUpdateDto);
    public ComercioQueryDto buscarComercio(int pComercioId);
    public void eliminarComercio(int pComercioId);
}
