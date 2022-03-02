package com.pgt360.payment.service.dto.comercio;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ComercioQueryPageableDto {
    private List<ComercioQueryDto> comercioQueryDtoList;
    private long totalRows;
}
