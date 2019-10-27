package com.api.travel.Repository;

import com.api.travel.Entity.Aeropuerto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AeropuertoRepository extends CrudRepository<Aeropuerto, Integer> {

    Aeropuerto findByActivoTrueAndNombreEquals(String name);
}
