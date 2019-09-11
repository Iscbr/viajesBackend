package com.api.travel.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "vuelo")
@Data
public class Vuelo implements Serializable {
}
