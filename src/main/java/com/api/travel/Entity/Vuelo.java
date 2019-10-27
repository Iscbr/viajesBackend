package com.api.travel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnore
    private Integer id;

    @Column(name = "fecha")
    @CreationTimestamp
    private LocalDateTime fecha;

    @Column(name = "no_pasajeros")
    private Integer pasajeros;

    @Column(name = "aerolinea", length = 50)
    private String aerolinea;

    @Column(name = "activo")
    private Boolean activo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aeropuerto_origen")
    private Aeropuerto aeropuertoOrigen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aeropuerto_destino")
    private Aeropuerto aeropuertoDestino;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_precio")
    private Precio precio;

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vuelo_lugar",
            joinColumns = @JoinColumn(name = "id_vuelo", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_lugar", nullable = false))
    private List<Lugar> lugares;*/

}
