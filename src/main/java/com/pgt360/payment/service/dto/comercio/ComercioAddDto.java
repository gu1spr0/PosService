package com.pgt360.payment.service.dto.comercio;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ComercioAddDto {
    private String razonSocial;
    private String estado;
}
