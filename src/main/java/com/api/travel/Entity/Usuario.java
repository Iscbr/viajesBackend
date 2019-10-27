package com.api.travel.Entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    @Column(name = "apellidos", length = 50, nullable = false)
    private String apellidos;

    @NotNull
    @NotEmpty
    @Email
    @Column(name = "email", length = 20, nullable = false)
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 30)
    @Column(name = "passwd")
    private String password;

    @Column(name = "creado")
    @CreationTimestamp
    private LocalDateTime creado;

    @Column(name = "activo")
    private Boolean activo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_vuelo",
            joinColumns = @JoinColumn(name = "id_usuario", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_vuelo", nullable = false))
    private List<Vuelo> vuelos;
}
