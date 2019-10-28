package com.api.travel.Entity;

import com.api.travel.Util.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "estado")
@Data
public class Estado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @JsonView(View.Airport.class)
    private Integer id;

    @Column(name = "nombre", length = 50)
    @JsonView(View.Airport.class)
    private String nombre;

    @Column(name = "descripcion", length = 150)
    @JsonView(View.Airport.class)
    private String descripcion;

    @Column(name = "activo")
    private Boolean activo;

    @OneToMany
    @JoinColumn(name = "id_estado")
    private List<Lugar> lugares;
}
