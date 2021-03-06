package com.pgt360.payment.controller;

import com.pgt360.payment.service.DispositivoService;
import com.pgt360.payment.service.dto.dispositivo.DispositivoQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "Endpoint para la gestión de dispositivos POS")
@RestController
@RequestMapping("/dispositivos")
public class DispositivoController {
    private final DispositivoService dispositivoService;
    public DispositivoController(DispositivoService dispositivoService){
        this.dispositivoService = dispositivoService;
    }
    @ApiOperation(value = "Busqueda de dispositivo POS por id", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/{idDispositivo}")
    @ResponseStatus(HttpStatus.OK)
    public DispositivoQueryDto buscarDispositivoPorId(@PathVariable(value = "idDispositivo", required = true) int idDispositivo){
        return this.dispositivoService.buscarDispositivo(idDispositivo);
    }
}
