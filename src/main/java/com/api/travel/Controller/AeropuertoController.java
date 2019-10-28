package com.api.travel.Controller;

import com.api.travel.Entity.Aeropuerto;
import com.api.travel.Service.AeropuertoService;
import com.api.travel.Util.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/airport")
public class AeropuertoController {
    private final AeropuertoService aeropuertoService;

    @Autowired
    public AeropuertoController(AeropuertoService aeropuertoService) {
        this.aeropuertoService = aeropuertoService;
    }

    @JsonView(View.Airport.class)
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {
        HashMap<String, Object> response = new HashMap<>();
        try {
            List<Aeropuerto> airports = aeropuertoService.getAll();

            if (airports == null) {
                response.put("error", "No se encontraron aeropuertos.");
                response.put("message", "No fue posible recuperar aeropuertos, contacte a soporte.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("successful", "Aeropuertos encontrados.");
            response.put("airports", airports);
        } catch (Exception e) {
            response.put("error", "Ocurrió un error al recuperar los aeropuertos.");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);
    }
}
