package com.api.travel.Controller;

import com.api.travel.Entity.Ciudad;
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

    @GetMapping("/getCityDetail/{id}")
    public ResponseEntity<Object> getState(@PathVariable("id") Integer idCity) {
        HashMap<String, Object> response = new HashMap<>();

        try {
            Ciudad ciudad = ciudadService.getCityById(idCity);
            if (ciudad == null) {
                response.put("error", "Ciudad inexistente");
                response.put("message", "No se ha encontrado ninguna ciudad con los parámetros enviados.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("successful", "Detalle de ciudad encontrado");
            response.put("city", ciudad);
        } catch (Exception e) {
            response.put("error", "Ocurrió un error al obtener el detalle de la ciudad.");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);
    }
}
