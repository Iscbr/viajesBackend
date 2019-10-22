package com.api.travel.Repository;

import com.api.travel.Entity.Lugar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LugarRepository extends CrudRepository<Lugar, Integer> {
    List<Lugar> getAllByActivoTrue();
}
