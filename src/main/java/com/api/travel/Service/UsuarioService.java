package com.api.travel.Service;

import com.api.travel.Entity.Card;
import com.api.travel.Entity.Usuario;
import com.api.travel.Repository.CardRepository;
import com.api.travel.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;
    private CardRepository cardRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, CardRepository cardRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cardRepository = cardRepository;
    }

    public Usuario getByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario saveUser(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    public Usuario getByID(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario addCard(Integer id, Card card) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) return null;

        card.setUsuario(usuario);

        List<Card> cards = usuario.getCards();
        if (card == null)
            cards = new ArrayList<>();
        cards.add(card);

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) throw new UsernameNotFoundException("No se ha encontrado el usuario con email: " + email);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));

        return new User(
                email,
                usuario.getPassword(),
                usuario.getActivo(),
                true,
                true,
                true,
                authorities);
    }
}
