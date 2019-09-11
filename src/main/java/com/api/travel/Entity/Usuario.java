package com.api.travel.Entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Data
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Integer id;

    @Column(name = "nombre", length = 30)
    private String nombre;

    @Column(name = "apellidos", length = 50)
    private String apellidos;

    @Column(name = "email", length = 20)
    private String email;

    @Column(name = "passwd")
    private String password;

    @Column(name = "creado")
    @CreationTimestamp
    private LocalDateTime creado;

    @Column(name = "activo")
    private Boolean activo;
}
