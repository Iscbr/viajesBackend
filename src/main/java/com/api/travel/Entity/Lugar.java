package com.api.travel.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lugar")
@Data
public class Lugar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "pais", length = 50)
    private String pais;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    @Column(name = "activo")
    private Boolean activo;
}