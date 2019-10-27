package com.api.travel.Controller;

import com.api.travel.Entity.Ciudad;
import com.api.travel.Entity.Estado;
import com.api.travel.Service.CiudadService;
import com.api.travel.Service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/city")
public class CiudadController {
    private final CiudadService ciudadService;
    private final EstadoService estadoService;
    @Autowired
    public CiudadController(CiudadService ciudadService, EstadoService estadoService) {
        this.ciudadService = ciudadService;
        this.estadoService = estadoService;
    }

    @GetMapping("/getState/{city}")
    public ResponseEntity<Object> getState(@PathVariable("city") String city) {
        HashMap<String, Object> response = new HashMap<>();

        if (city == null) {
            response.put("error", "Ciudad inválida.");
            response.put("message", "El nombre de la ciudad no puede ser null");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Ciudad ciudad = this.ciudadService.getCityByName(city);

            if (ciudad == null) {
                response.put("error", "Ciudad inexistente");
                response.put("message", "No se ha encontrado ninguna ciudad con los parámetros enviados.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            Estado estado = ciudad.getEstado();
            response.put("successful", "Estado encontrado");
            response.put("state", estado);
        } catch (Exception e) {
            response.put("error", "Ocurrió un error al obtener el estado");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);
    }
}
