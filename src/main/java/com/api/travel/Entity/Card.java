package com.api.travel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "card")
@Data
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @NotEmpty
    @Size(min = 19, max = 19)
    @Column(name = "card_number", length = 20)
    private String cardNumber;

    @NotEmpty
    @Size(min = 5, max = 5)
    @Column(name = "expiration_date", length = 6)
    private String expirationDate;

    @NotEmpty
    @Size(min = 3, max = 3)
    @Column(name = "cvv", length = 5)
    private String cvv;

    @NotEmpty
    @Column(name = "type", length = 20)
    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    @JsonIgnore
    private Usuario usuario;
}
