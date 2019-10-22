package com.api.travel.Repository;

import com.api.travel.Entity.Vuelo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VueloRepository extends CrudRepository<Vuelo, Integer> {
    List<Vuelo> findAllByActivoTrueAndOrigenEqualsAndDestinoEqualsAndFechaIdaEquals(String origin, String destination, LocalDate dateGo);
}
