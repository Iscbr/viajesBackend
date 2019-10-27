package com.api.travel.Service;

import com.api.travel.Entity.Ciudad;
import com.api.travel.Entity.Estado;
import com.api.travel.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;
    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }
}
