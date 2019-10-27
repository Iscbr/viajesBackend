package com.api.travel.Repository;

import com.api.travel.Entity.Estado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Integer> {
    Estado findByActivoTrueAndNombreEquals(String state);
}
