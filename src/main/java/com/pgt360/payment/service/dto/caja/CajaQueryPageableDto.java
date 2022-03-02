package com.pgt360.payment.service.dto.caja;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CajaQueryPageableDto {
    private List<CajaQueryDto> cajaQueryDtoList;
    private long totalRows;
}
