package com.pgt360.payment.service.dto.netty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDto {
    private boolean estado;
    private String mensaje;
    private Object data;
}
