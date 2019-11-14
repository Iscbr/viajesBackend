package com.api.travel.Entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
@Data
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "fecha_creada")
    @CreationTimestamp
    private LocalDateTime fechaCreada;

    @Column(name = "fecha_reserva")
    @CreationTimestamp
    private LocalDateTime fechaReserva;

    @Column(name = "fecha_expiracion")
    @CreationTimestamp
    private LocalDateTime fechaExpiracion;

    @Column(name = "finalizada")
    private Boolean finalizada;

    @OneToOne
    @JoinColumn(name = "id_vuelo")
    private Vuelo vuelo;

}
