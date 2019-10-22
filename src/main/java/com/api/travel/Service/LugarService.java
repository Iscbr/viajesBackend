package com.api.travel.Service;

import com.api.travel.Entity.Lugar;
import com.api.travel.Repository.LugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugarService {

    private final LugarRepository lugarRepository;

    @Autowired
    public LugarService(LugarRepository lugarRepository) {
        this.lugarRepository = lugarRepository;
    }

    public List<Lugar> getAll() {
        return this.lugarRepository.getAllByActivoTrue();
    }
}
