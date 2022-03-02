package com.pgt360.payment.controller;

import com.pgt360.payment.service.ComercioService;
import com.pgt360.payment.service.dto.comercio.ComercioAddDto;
import com.pgt360.payment.service.dto.comercio.ComercioQueryDto;
import com.pgt360.payment.service.dto.comercio.ComercioQueryPageableDto;
import com.pgt360.payment.service.dto.comercio.ComercioUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "Endpoint para la gestión de comercios")
@RestController
@RequestMapping("/comercios")
public class ComercioController {
    private final ComercioService comercioService;

    public ComercioController(ComercioService comercioService){
        this.comercioService = comercioService;
    }

    @ApiOperation(value = "Listar comercio por estado paginado", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public ComercioQueryPageableDto obtenerComercioPorEstado(@RequestParam(required = true) String estado,
                                                             @RequestParam(required = true) int page,
                                                             @RequestParam(required = true) int rows){
        return this.comercioService.listarComercioPaginado(estado, page, rows);
    }

    @ApiOperation(value = "Buscar comercio por Id", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/{idComercio}/find")
    @ResponseStatus(HttpStatus.OK)
    public ComercioQueryDto buscarComercioPorId(@PathVariable(required = true) int idComercio){
        return this.comercioService.buscarComercio(idComercio);
    }

    @ApiOperation(value = "Agregar comercio", authorizations = @Authorization(value = "Bearer"))
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ComercioQueryDto guardarComercio(@Valid @RequestBody ComercioAddDto comercioAddDto){
        return comercioService.agregarComercio(comercioAddDto);
    }

    @ApiOperation(value = "Modificar Comercio", authorizations = @Authorization(value = "Bearer"))
    @PutMapping(path = "/{idComercio}")
    @ResponseStatus(HttpStatus.OK)
    public ComercioQueryDto modificarComercio(@PathVariable(required = true) int idComercio,
                                              @Valid @RequestBody ComercioUpdateDto comercioUpdateDto){
        return comercioService.modificarComercio(idComercio, comercioUpdateDto);
    }

    @ApiOperation(value = "Endpoint para la elimiación de comercio", authorizations = @Authorization(value = "Bearer"))
    @DeleteMapping(path = "/{idComercio}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminarComercio(@PathVariable(required = true) int idComercio){
        comercioService.eliminarComercio(idComercio);
    }
}
