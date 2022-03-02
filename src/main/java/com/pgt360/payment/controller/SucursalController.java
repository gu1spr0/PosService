package com.pgt360.payment.controller;

import com.pgt360.payment.service.SucursalService;
import com.pgt360.payment.service.dto.sucursal.SucursalComercioQueryDto;
import com.pgt360.payment.service.dto.sucursal.SucursalQueryDto;
import com.pgt360.payment.service.dto.sucursal.SucursalQueryPageableDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "Endpoint para la gestión de sucursales de comercios")
@RestController
@RequestMapping("/sucursales")
public class SucursalController {
    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService){
        this.sucursalService = sucursalService;
    }

    @ApiOperation(value = "Buscar sucursal por Id", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/{idSucursal}")
    @ResponseStatus(HttpStatus.OK)
    public SucursalQueryDto buscarSucursalPorId(@PathVariable(required = true) int idSucursal){
        return this.sucursalService.buscarSucursalPorId(idSucursal);
    }

    @ApiOperation(value = "Listar sucursales por estado paginado", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public SucursalQueryPageableDto obtenerSucursalesPorEstado(@RequestParam(required = true) String estado,
                                                             @RequestParam(required = true) int page,
                                                             @RequestParam(required = true) int rows){
        return this.sucursalService.listarSucursalesPageable(estado, page, rows);
    }

    @ApiOperation(value = "Listar sucursales por estado y comercio", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/{idComercio}/select")
    @ResponseStatus(HttpStatus.OK)
    public SucursalComercioQueryDto obtenerSucursalesPorEstadoYComercio(@PathVariable(required = true) int idComercio,
                                                                        @RequestParam(required = true) String estado){
        return this.sucursalService.listarSucursalesPorComercioEstado(estado, idComercio);
    }

    @ApiOperation(value = "Eliminar sucursal", authorizations = @Authorization(value = "Bearer"))
    @DeleteMapping(path = "/{idSucursal}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminarSucursalPorId(@PathVariable(required = true) int idSucursal){
        this.sucursalService.eliminarSucursal(idSucursal);
    }
}
