package com.api.travel.Repository;

import com.api.travel.Entity.Boleto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoRepository extends CrudRepository<Boleto, Integer> {
}
