package com.api.travel.Service;

import com.api.travel.DTO.FlightsDTO;
import com.api.travel.Entity.Aeropuerto;
import com.api.travel.Entity.Vuelo;
import com.api.travel.Repository.AeropuertoRepository;
import com.api.travel.Repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VueloService {

    private final VueloRepository vueloRepository;
    private final AeropuertoRepository aeropuertoRepository;

    @Autowired
    public VueloService(VueloRepository vueloRepository,
                        AeropuertoRepository aeropuertoRepository) {
        this.vueloRepository = vueloRepository;
        this.aeropuertoRepository = aeropuertoRepository;
    }

    public FlightsDTO getRoundFlights(Integer origin, Integer destination, LocalDateTime dateGo, LocalDateTime dateBack, String category) {
        Aeropuerto origen = this.aeropuertoRepository.findByActivoTrueAndId(origin);
        Aeropuerto destino = this.aeropuertoRepository.findByActivoTrueAndId(destination);

        if (origen == null || destino == null)
            return null;

        List<Vuelo> flightsGo = this.vueloRepository.findAllByActivoTrueAndAeropuertoOrigenEqualsAndAeropuertoDestinoEqualsAndFechaGreaterThanEqual(origen, destino, dateGo);
        List<Vuelo> flightsBack = this.vueloRepository.findAllByActivoTrueAndAeropuertoOrigenEqualsAndAeropuertoDestinoEqualsAndFechaGreaterThanEqual(destino, origen, dateBack);

        if (flightsGo == null && flightsBack == null)
            return null;

        FlightsDTO flightsDTO = new FlightsDTO();
        flightsDTO.setFlightsGo(flightsGo);
        flightsDTO.setFlightsBack(flightsBack);

        return flightsDTO;
    }

    public FlightsDTO getSingleFlights(Integer origin, Integer destination, LocalDateTime dateGo, String category) {
        Aeropuerto origen = this.aeropuertoRepository.findByActivoTrueAndId(origin);
        Aeropuerto destino = this.aeropuertoRepository.findByActivoTrueAndId(destination);

        if (origen == null || destination == null) return null;

        List<Vuelo> flightsGo = this.vueloRepository.findAllByActivoTrueAndAeropuertoOrigenEqualsAndAeropuertoDestinoEqualsAndFechaGreaterThanEqual(origen, destino, dateGo);
        //List<Vuelo> flightsGo = this.vueloRepository.findAllByActivoTrueAndOrigenEqualsAndDestinoEqualsAndFechaIdaEquals(origin, destination, dateGo);

        if (flightsGo == null)
            return null;

        FlightsDTO flightsDTO = new FlightsDTO();
        flightsDTO.setFlightsGo(flightsGo);
        return flightsDTO;
    }
}
