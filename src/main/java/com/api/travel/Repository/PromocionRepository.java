package com.api.travel.Repository;

import com.api.travel.Entity.Promocion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionRepository extends CrudRepository<Promocion, Integer> {
}
