package com.pgt360.payment.service.dto.sucursal;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SucursalComercioQueryDto {
    private List<SucursalQueryDto> sucursalQueryDtoList;
    private long totalRows;
}
