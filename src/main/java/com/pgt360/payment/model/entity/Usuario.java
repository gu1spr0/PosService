package com.pgt360.payment.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pg_usuarios")
public class Usuario extends Base{

    @ApiModelProperty(notes = "Describe el nombre de usuario")
    @NotNull(message="El usuario no puede ser nulo")
    @Column(name="usuario", length = 150)
    private String username;

    @ApiModelProperty(notes = "Describe la constraseña de usuario")
    @NotNull(message="La contraseña no puede ser nulo")
    @Column(name="password", length = 300)
    private String password;

    @ApiModelProperty(notes = "Contiene el nombre propio del usuario")
    @NotNull(message="El nombre del usuario no puede ser nulo")
    @Column(name="nombre", length = 60)
    private String nombre;

    @ApiModelProperty(notes = "Contiene el apellido paterno del usuario")
    @NotNull(message="El apellido paterno del usuario no puede ser nulo")
    @Column(name="paterno", length = 60)
    private String paterno;

    @ApiModelProperty(notes = "Contiene el apellido materno del usuario")
    @Column(name="materno", length = 60)
    private String materno;

    @ApiModelProperty(notes = "Contiene el número de celular del usuario")
    @NotNull(message="El numero de celular no puede ser nulo")
    @Column(name="telcel", length = 10)
    private String telcel;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_caja")
    private Caja caja;
}
