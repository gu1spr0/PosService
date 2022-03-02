package com.pgt360.payment.controller;

import com.pgt360.payment.service.CajaService;
import com.pgt360.payment.service.dto.caja.CajaQueryPageableDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "Endpoint para la gestión de cajas")
@RestController
@RequestMapping("/cajas")
public class CajaController {
    private final CajaService cajaService;

    public CajaController(CajaService cajaService) {
        this.cajaService = cajaService;
    }
    @ApiOperation(value = "Listar cajas por estado paginado", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public CajaQueryPageableDto obtenerComercioPorEstado(@RequestParam(required = true) String estado,
                                                         @RequestParam(required = true) int page,
                                                         @RequestParam(required = true) int rows){
        return this.cajaService.listarCajaPageable(estado, page, rows);
    }

}
