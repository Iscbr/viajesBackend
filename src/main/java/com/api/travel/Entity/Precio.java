package com.api.travel.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "precio")
@Data
public class Precio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Integer id;

    @Column(name = "base", precision = 4)
    private Double base;

    @Column(name = "iva", precision = 4)
    private Double iva;

    @Column(name = "tipo", length = 10)
    private String tipo;

    @Column(name = "descripcion", length = 150)
    private String descripcion;

    @Column(name = "activo")
    private String activo;
}
