package com.api.travel.Service;

import com.api.travel.Entity.Ciudad;
import com.api.travel.Repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadService {
    private final CiudadRepository ciudadRepository;
    @Autowired
    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public Ciudad getCityById(Integer idCity) {
        return ciudadRepository.findById(idCity).orElse(null);
    }
}
