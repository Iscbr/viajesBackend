package com.api.travel.Entity;

import com.api.travel.Util.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ciudad")
@Data
public class Ciudad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad", nullable = false, unique = true, updatable = false)
    @JsonView({View.Summary.class, View.Airport.class})
    private Integer id;

    @Column(name = "nombre", length = 50)
    @JsonView({View.Summary.class, View.Airport.class})
    private String nombre;

    @Column(name = "descripcion", length = 300)
    @JsonView({View.Summary.class, View.Airport.class})
    private String descripcion;

    @Column(name = "activo")
    @JsonView(View.Summary.class)
    private Boolean activo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estado")
    @JsonView(View.Airport.class)
    private Estado estado;
}
