package com.api.travel.Service;

import com.api.travel.Entity.Estado;
import com.api.travel.Entity.Lugar;
import com.api.travel.Repository.EstadoRepository;
import com.api.travel.Repository.LugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugarService {

    private final LugarRepository lugarRepository;
    private final EstadoRepository estadoRepository;

    @Autowired
    public LugarService(LugarRepository lugarRepository,
                        EstadoRepository estadoRepository) {
        this.lugarRepository = lugarRepository;
        this.estadoRepository = estadoRepository;
    }

    public List<Lugar> getAll() {
        return this.lugarRepository.getAllByActivoTrue();
    }
}
