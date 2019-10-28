package com.api.travel.Repository;

import com.api.travel.Entity.Aeropuerto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AeropuertoRepository extends CrudRepository<Aeropuerto, Integer> {
    List<Aeropuerto> findAllByActivoTrue();
    Aeropuerto findByActivoTrueAndId(Integer id);
}
