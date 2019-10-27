package com.api.travel.Controller;

import com.api.travel.Entity.Rol;
import com.api.travel.Entity.Usuario;
import com.api.travel.Service.RolService;
import com.api.travel.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final RolService rolService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService,
                             RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
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
            Rol rol = this.rolService.getRolByName("USER");
            if (rol == null) {
                response.put("error", "No se encontraron roles.");
                response.put("message", "No se encontraron roles para asignar al usuario '" + usuario.getNombre() + "', contacte a soporte");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            usuario.setRol(rol);
            Usuario usuarioGuardado = this.usuarioService.saveUser(usuario);
            if (usuarioGuardado == null) {
                response.put("error", "Usuario no guardado");
                response.put("message", "No se pudo guardar el usuario, inténtalo de nuevo más tarde.");
            }
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
            if (admin) rol = rolService.getRolByName("ADMIN");
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

}
