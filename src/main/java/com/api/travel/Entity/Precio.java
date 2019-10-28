package com.api.travel.Entity;

import com.api.travel.Util.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "precio")
@Data
public class Precio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @JsonView(View.Summary.class)
    private Integer id;

    @Column(name = "base", precision = 4)
    @JsonView(View.Summary.class)
    private Double base;

    @Column(name = "iva", precision = 4)
    @JsonView(View.Summary.class)
    private Double iva;

    @Column(name = "tipo", length = 10)
    @JsonView(View.Summary.class)
    private String tipo;

    @Column(name = "descripcion", length = 150)
    @JsonView(View.Summary.class)
    private String descripcion;

    @Column(name = "activo")
    @JsonView(View.Summary.class)
    private Boolean activo;

    @OneToMany(mappedBy = "precio")
    @JsonView(View.Summary.class)
    private List<Promocion> promociones;


}
