package com.api.travel.Controller;

import com.api.travel.Entity.Card;
import com.api.travel.Entity.Rol;
import com.api.travel.Entity.Usuario;
import com.api.travel.Service.MailService;
import com.api.travel.Service.RolService;
import com.api.travel.Service.UsuarioService;
import com.api.travel.Util.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final RolService rolService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, RolService rolService, BCryptPasswordEncoder bCryptPasswordEncoder, MailService mailService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailService = mailService;
    }

    @PostMapping("/addCard/{id}")
    public ResponseEntity<Object> addCard(@Valid @RequestBody Card card,
                                          @PathVariable("id") Integer id,
                                          BindingResult bindingResult) {
        HashMap<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult
                    .getFieldErrors()
                    .forEach(fieldError -> errors.add("Field '" + fieldError.getField() + "' " + fieldError.getDefaultMessage()));
            response.put("error", "existen errores en algunos campos");
            response.put("message", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {

            Usuario usuario = usuarioService.addCard(id, card);
            List<Card> cards = usuario
                    .getCards()
                    .stream()
                    .map(cardLamda -> {
                        Card card1 = new Card();
                        card1.setId(cardLamda.getId());
                        card1.setCardNumber("**** " + cardLamda.getCardNumber().substring(15));
                        card1.setExpirationDate(cardLamda.getExpirationDate());
                        card1.setCvv("***");
                        card1.setType(cardLamda.getType());
                        return card1;
                    }).collect(Collectors.toList());

            usuario.setCards(cards);

            response.put("successful", "Tarjeta guardada");
            response.put("message", "Tarjeta **** " + card.getCardNumber().substring(15) + " guardada.");
            response.put("usuario", usuario);
        } catch (Exception e) {
            response.put("error", "Ocurrió un error.");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody Usuario usuario,
                                           BindingResult bindingResult) {
        HashMap<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult
                    .getFieldErrors()
                    .forEach(fieldError -> errors.add("Field '" + fieldError.getField() + "' " + fieldError.getDefaultMessage()));
            response.put("error", "existen errores en algunos campos.");
            response.put("message", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Rol rol = this.rolService.getRolByName("ROLE_USER");
            if (rol == null) {
                response.put("error", "No se encontraron roles.");
                response.put("message", "No se encontraron roles para asignar al usuario '" + usuario.getNombre() + "', contacte a soporte");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            usuario.setRol(rol);

            /**
             * Se cifra la contraseña del usuario con BCryptPasswordEncoder = SHA256
             */
            String password = bCryptPasswordEncoder.encode(usuario.getPassword());
            usuario.setPassword(password);

            Usuario usuarioGuardado = this.usuarioService.saveUser(usuario);
            if (usuarioGuardado == null) {
                response.put("error", "Usuario no guardado");
                response.put("message", "No se pudo guardar el usuario, inténtalo de nuevo más tarde.");
            }
            /**
             * Se envia la confirmación por email del registro
             */
            this.sendEmailConfirmation(usuarioGuardado);
            response.put("successfull", "Usuario guardado con éxito.");
            response.put("user", usuarioGuardado);
        } catch (Exception e) {
            response.put("error", "Ocurrió un error al guardar el usuario");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update/{id}/{admin}")
    public ResponseEntity<Object> update(@Valid @RequestBody Usuario usuario,
                                         BindingResult bindingResult,
                                         @PathVariable("id") Integer id,
                                         @PathVariable("admin") Boolean admin) {
        HashMap<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult
                    .getFieldErrors()
                    .forEach(fieldError -> errors.add("Field '" + fieldError.getField() + "' " + fieldError.getDefaultMessage()));
            response.put("error", "existen errores en algunos campos.");
            response.put("message", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Usuario usuarioEncontrado = usuarioService.getByID(id);
            if (usuarioEncontrado == null) {
                response.put("error", "Usuario no encontrado");
                response.put("message", "No se ha encontrado un usuario para actualizar con ID " + id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            Rol rol;
            if (admin) rol = rolService.getRolByName("ROLE_ADMIN");
            else rol = rolService.getRolByName("USER");

            if (rol == null) {
                response.put("error", "No se encontraron roles.");
                response.put("message", "No se encontraron roles para asignar al usuario '" + usuarioEncontrado.getNombre() + "', contacte a soporte");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            usuarioEncontrado.setRol(rol);
            usuarioEncontrado.setNombre(usuario.getNombre());
            usuarioEncontrado.setApellidos(usuario.getApellidos());
            usuarioEncontrado.setEmail(usuario.getEmail());
            usuarioEncontrado.setPassword(usuario.getPassword());
            usuarioEncontrado.setActivo(usuario.getActivo());
            usuarioEncontrado = usuarioService.saveUser(usuarioEncontrado);

            response.put("sucessful", "Usuario actualizado con éxito");
            response.put("user", usuarioEncontrado);
        } catch (Exception e) {
            response.put("error", "Ocurrió un error al actualizar el usuario con ID " + id);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);
    }

    private void sendEmailConfirmation(Usuario usuario) throws Exception {
        HashMap<String, Object> contentMail = new HashMap<>();
        contentMail.put("name", usuario.getNombre());

        Mail mail = new Mail();
        mail.setFrom("no-reply@relax-travel.com");
        mail.setTo(usuario.getEmail());
        mail.setSubject("Registro éxitoso");
        mail.setModel(contentMail);

        mailService.sendEmail(mail);
    }
}
