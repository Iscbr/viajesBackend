package com.api.travel.Service;

import com.api.travel.Entity.Usuario;
import com.api.travel.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario saveUser(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    public Usuario getByID(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
