package com.api.travel.Entity;

import com.api.travel.Util.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vuelo")
@Data
public class Vuelo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @JsonView(View.Summary.class)
    private Integer id;

    @Column(name = "fecha")
    @CreationTimestamp
    @JsonView(View.Summary.class)
    private LocalDateTime fecha;

    @Column(name = "no_pasajeros")
    @JsonView(View.Summary.class)
    private Integer pasajeros;

    @Column(name = "aerolinea", length = 50)
    @JsonView(View.Summary.class)
    private String aerolinea;

    @JsonView(View.Summary.class)
    @Column(name = "activo")
    private Boolean activo;

    @JsonView(View.Summary.class)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aeropuerto_origen")
    private Aeropuerto aeropuertoOrigen;

    @JsonView(View.Summary.class)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aeropuerto_destino")
    private Aeropuerto aeropuertoDestino;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_precio")
    @JsonView(View.Summary.class)
    private Precio precio;

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vuelo_lugar",
            joinColumns = @JoinColumn(name = "id_vuelo", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_lugar", nullable = false))
    private List<Lugar> lugares;*/

}
