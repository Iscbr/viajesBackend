package com.api.travel.Entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "alojamiento")
@Data
public class Alojamiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    @Column(name = "longitud", precision = 10)
    private Double longitud;

    @Column(name = "latitud", precision = 10)
    private Double latitud;

    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "alojamiento")
    private List<Precio> precio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_lugar")
    private Lugar lugar;
}
