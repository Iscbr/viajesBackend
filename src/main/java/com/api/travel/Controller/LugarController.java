package com.api.travel.Controller;

import com.api.travel.Entity.Lugar;
import com.api.travel.Service.LugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/place")
public class LugarController {

    private final LugarService lugarService;

    @Autowired
    public LugarController(LugarService lugarService) {
        this.lugarService = lugarService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllPlaces() {
        HashMap<String, Object> response = new HashMap<>();
        List<Lugar> places = null;
        try {
            places = this.lugarService.getAll();
            if (places != null) {
                response.put("successful", "Se han encontrado " + places.size() + " lugares");
                response.put("places", places);
            } else {
                response.put("error", "no se encontraron lugares disponibles");
                response.put("message", "Es posible que no existan registros en la base de datos.");
            }
        } catch (Exception e) {
            response.put("error", "Ocurrió un error al obtener los lugares.");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getPlacesByState/{idState}")
    public ResponseEntity<Object> getPlacesByState(@PathVariable("idState") Integer idState) {

        HashMap<String, Object> response = new HashMap<>();

        try {
            List<Lugar> places = lugarService.getBySate(idState);
            if (places == null) {
                response.put("error", "Estado inexistente");
                response.put("message", "No se han encontrado lugares para el ID del estado enviado, verifique que exista.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("successful", "Lugares encontrados");
            response.put("places", places);
        } catch (Exception e) {
            response.put("error", "Ocurrió un error al buscar lugares por estado.");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);
    }
}
