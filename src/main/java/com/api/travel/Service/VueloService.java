package com.api.travel.Service;

import com.api.travel.DTO.FlightsDTO;
import com.api.travel.Entity.Vuelo;
import com.api.travel.Repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VueloService {

    private final VueloRepository vueloRepository;

    @Autowired
    public VueloService(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    public FlightsDTO getRoundFlights(String origin, String destination, LocalDateTime dateGo, LocalDateTime dateBack, String category) {
        List<Vuelo> flightsGo = this.vueloRepository.findAllByActivoTrueAndOrigenEqualsAndDestinoEqualsAndFechaIdaEquals(origin, destination, dateGo);
        List<Vuelo> flightsBack = this.vueloRepository.findAllByActivoTrueAndOrigenEqualsAndDestinoEqualsAndFechaIdaEquals(destination, origin, dateBack);

        if (flightsGo == null && flightsBack == null)
            return null;

        FlightsDTO flightsDTO = new FlightsDTO();
        flightsDTO.setFlightsGo(flightsGo);
        flightsDTO.setFlightsBack(flightsBack);

        return flightsDTO;
    }

    public FlightsDTO getSingleFlights(String origin, String destination, LocalDateTime dateGo, String category) {
        List<Vuelo> flightsGo = this.vueloRepository.findAllByActivoTrueAndOrigenEqualsAndDestinoEqualsAndFechaIdaEquals(origin, destination, dateGo);

        if (flightsGo == null)
            return null;

        FlightsDTO flightsDTO = new FlightsDTO();
        flightsDTO.setFlightsGo(flightsGo);
        return flightsDTO;
    }
}
