package com.api.travel.Service;

import com.api.travel.Entity.Aeropuerto;
import com.api.travel.Repository.AeropuertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AeropuertoService {
    private final AeropuertoRepository aeropuertoRepository;
    @Autowired
    public AeropuertoService(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    public List<Aeropuerto> getAll() {
        return aeropuertoRepository.findAllByActivoTrue();
    }
}
