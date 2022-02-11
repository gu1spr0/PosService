package com.pgt360.payment.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "pg_usuarios")
public class Usuario extends Base{
    @NotNull(message="El usuario no puede ser nulo")
    @Column(name="usuario", length = 150)
    private String username;

    @NotNull(message="La contraseña no puede ser nulo")
    @Column(name="password", length = 300)
    private String password;

    @NotNull(message="El nombre del usuario no puede ser nulo")
    @Column(name="nombre", length = 60)
    private String nombre;

    @NotNull(message="El apellido paterno del usuario no puede ser nulo")
    @Column(name="paterno", length = 60)
    private String paterno;

    @Column(name="materno", length = 60)
    private String materno;

    @NotNull(message="El numero de celular no puede ser nulo")
    @Column(name="celular", length = 10)
    private String celular;
}
