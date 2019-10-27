package com.api.travel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "aeropuerto")
@Data
public class Aeropuerto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aeropuerto", nullable = false, unique = true, updatable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 3000)
    private String descripcion;

    @Column(name = "latitud", precision = 10)
    private Double latitud;

    @Column(name = "longitud", precision = 10)
    private Double longitud;

    @Column(name = "iata", length = 10)
    private String iata;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ciudad")
    private Ciudad ciudad;
}
