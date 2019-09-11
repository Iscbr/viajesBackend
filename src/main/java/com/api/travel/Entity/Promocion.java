package com.api.travel.Entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "promocion")
@Data
public class Promocion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Integer id;

    @Column(name = "clave", length = 10)
    private String clave;

    @Column(name = "tipo", length = 10)
    private String tipo;

    @Column(name = "descripion", length = 150)
    private String descripcion;

    @Column(name = "activo")
    private Boolean activo;
}
