package com.api.travel.Entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "boleto")
@Data
public class Boleto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva", nullable = false, unique = true, updatable = false)
    private Integer reserva;

    @Column(name = "fecha_creacion")
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_expiracion")
    @CreationTimestamp
    private LocalDateTime fechaExpiracion;

    @Column(name = "descripcion", length = 150)
    private String descripcion;

    @Column(name = "activo")
    private Boolean activo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vuelo", nullable = false)
    private Vuelo vuelo;
}
