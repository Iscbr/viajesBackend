package com.api.travel.Repository;

import com.api.travel.Entity.Precio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecioRepository extends CrudRepository<Precio, Integer> {
}
