package com.api.travel.Controller;

import com.api.travel.DTO.FlightsDTO;
import com.api.travel.Service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@RestController
@RequestMapping("/flights")
public class VueloController {

    private final VueloService vueloService;

    @Autowired
    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping("/getFlights/{type}/{origin}/{destination}/{dateGo}/{dateBack}/{category}")
    public ResponseEntity<Object> getFlightsThatMatch(@PathVariable("type") Boolean type,
                                              @PathVariable("origin") String origin,
                                              @PathVariable("destination") String destination,
                                              @PathVariable("dateGo") String dateGo,
                                              @PathVariable("dateBack") String dateBack,
                                              @PathVariable("category") String category) {

        HashMap<String, Object> response = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (type ==null) {
            response.put("error", "parámetro inválido");
            response.put("message", "Variable 'type' no puede ser null. Debe ser 'true' = vuelo redondo o 'false' = vuelo sencillo");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (type) {
            // Redondo
            if (origin == null || destination == null || dateGo == null || dateBack == null || category == null) {
                response.put("error", "parámetros inválidos");
                response.put("message", "Los siguientes parámetros no pueden estar vacios: 'origin' : String, 'destination' : String, 'dateGo' : Date, 'dateBack' : Date.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            try {
                LocalDateTime go = LocalDateTime.parse(dateGo + " 00:00", formatter);
                LocalDateTime back = LocalDateTime.parse(dateBack + " 00:00", formatter);
                FlightsDTO flightsDTO = this.vueloService.getRoundFlights(origin, destination, go, back, category);
                if (flightsDTO == null) {
                    response.put("error", "vuelos no encotrados.");
                    response.put("message", "No se han encontrado vuelos que coincidan con los parámetros enviados.");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }

                response.put("successful", "Se han encontrado " + flightsDTO.getFlightsGo().size() + " vuelos de ida y " + flightsDTO.getFlightsBack().size() + " vuelos de regreso");
                response.put("flights", flightsDTO);
            } catch (Exception e) {
                response.put("error", "Ocurrió un error en el servidor al obtener los vuelos redondos.");
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }


        } else {
            // Sencillo
            if (origin == null || destination == null || dateGo == null || category == null) {
                response.put("error", "parámetros inválidos");
                response.put("message", "Los siguientes parámetros no pueden estar vacios: 'origin' : String, 'destination' : String, 'dateGo' : Date");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            try {
                LocalDateTime go = LocalDateTime.parse(dateGo + " 00:00", formatter);
                FlightsDTO flightsDTO = this.vueloService.getSingleFlights(origin, destination, go, category);
                if (flightsDTO == null) {
                    response.put("error", "vuelos no encontrados");
                    response.put("message", "no se han encontrado vuelos que coincidan con los parametros enviados.");
                }

                response.put("successful", "Se han encontrado " + flightsDTO.getFlightsGo().size() + " vuelos de ida.");
                response.put("flights", flightsDTO);
            } catch (Exception e) {
                response.put("error", "Ocurrió un error en el servidor al obtener los vuelos sencillos.");
                response.put("message", e.getMessage());
            }
        }
        return ResponseEntity.ok(response);
    }
}
