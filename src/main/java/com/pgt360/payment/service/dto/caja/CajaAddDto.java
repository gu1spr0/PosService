package com.pgt360.payment.service.dto.caja;

import com.pgt360.payment.model.entity.Comercio;
import com.pgt360.payment.model.entity.Dispositivo;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class CajaAddDto {
    private String estado;
    private int numeroCaja;
    private int comercio;
    private int dispositivo;
    //private List<Flujo> flujoList;
}
