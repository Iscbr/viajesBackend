package com.api.travel.Repository;

import com.api.travel.Entity.Ciudad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad, Integer> {
}
