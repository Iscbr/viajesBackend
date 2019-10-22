package com.api.travel.Repository;

import com.api.travel.Entity.Vuelo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloRepository extends CrudRepository<Vuelo, Integer> {
}
