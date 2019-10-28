package com.api.travel.Entity;

import com.api.travel.Util.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(View.Summary.class)
    private String clave;

    @Column(name = "tipo", length = 10)
    @JsonView(View.Summary.class)
    private String tipo;

    @Column(name = "descripcion", length = 150)
    @JsonView(View.Summary.class)
    private String descripcion;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_precio")
    @JsonView(View.Summary.class)
    private Precio precio;
}
