package com.pgt360.payment.service.dto.comercio;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Getter
public class ComercioAddDto {
    private String nit;
    private String razonSocial;
    private String municipio;
    private String direccion;
    private String telefonoFijo;
    private String telefonoMovil;
    private String email;
}
