package com.pgt360.payment.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "pg_comercios")
public class Comercio extends Base{
    @NotNull(message = "La razon social no puede ser nulo")
    @Column(name = "razon_social", length = 40)
    private String razonSocial;

    @OneToMany(mappedBy = "comercio",fetch = FetchType.LAZY)
    private List<Caja> cajaList;
}
