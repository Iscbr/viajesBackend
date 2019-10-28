package com.api.travel.Entity;

import com.api.travel.Util.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView({View.Summary.class, View.Airport.class})
    private Integer id;

    @Column(name = "nombre", length = 50)
    @JsonView({View.Summary.class, View.Airport.class})
    private String nombre;

    @Column(name = "descripcion", length = 3000)
    @JsonView({View.Summary.class, View.Airport.class})
    private String descripcion;

    @Column(name = "latitud", precision = 10)
    @JsonView({View.Summary.class, View.Airport.class})
    private Double latitud;

    @Column(name = "longitud", precision = 10)
    @JsonView({View.Summary.class, View.Airport.class})
    private Double longitud;

    @Column(name = "iata", length = 10)
    @JsonView({View.Summary.class, View.Airport.class})
    private String iata;

    @Column(name = "activo")
    @JsonView({View.Summary.class, View.Airport.class})
    private Boolean activo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ciudad")
    @JsonView({View.Summary.class, View.Airport.class})
    private Ciudad ciudad;
}
