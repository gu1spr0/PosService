package com.pgt360.payment.service.dto.comercio;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ComercioUpdateDto {
    private String razonSocial;
    private Date fechaAlta;
    private int usuarioAlta;
    private String estado;
}
