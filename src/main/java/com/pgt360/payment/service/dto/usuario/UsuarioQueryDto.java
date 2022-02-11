package com.pgt360.payment.service.dto.usuario;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class UsuarioQueryDto {
    private String usuario;
    private String password;
    private String nombre;
    private String paterno;
    private String materno;
    private String celular;
    private long id;
    private Date fechaAlta;
    private int usuarioAlta;
    private String estado;
}
