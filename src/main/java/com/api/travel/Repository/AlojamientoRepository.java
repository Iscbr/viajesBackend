package com.api.travel.Repository;

import com.api.travel.Entity.Alojamiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlojamientoRepository extends CrudRepository<Alojamiento, Integer> {
}
