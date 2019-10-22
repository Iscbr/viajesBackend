package com.api.travel.Entity;

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
    private Integer id;

    @Column(name = "origen", length = 50)
    private String origen;

    @Column(name = "destino", length = 50)
    private String destino;

    @Column(name = "fecha_ida")
    @CreationTimestamp
    private LocalDateTime fechaIda;

    @Column(name = "fecha_regreso")
    @CreationTimestamp
    private LocalDateTime fecahregreso;

    @Column(name = "no_pasajeros")
    private Integer pasajeros;

    @Column(name = "aerolinea", length = 50)
    private String aerolinea;

    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "vuelo")
    private List<Precio> precios;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vuelo_lugar",
            joinColumns = @JoinColumn(name = "id_vuelo", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_lugar", nullable = false))
    private List<Lugar> lugares;

}
