package com.api.travel.Service;

import com.api.travel.Entity.Rol;
import com.api.travel.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {
    private final RolRepository rolRepository;
    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol getRolByName(String name) {
        return rolRepository.getByActivoTrueAndNombreEquals(name);
    }
}
