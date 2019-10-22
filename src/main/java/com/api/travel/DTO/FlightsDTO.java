package com.api.travel.DTO;

import com.api.travel.Entity.Vuelo;
import lombok.Data;

import java.util.List;

@Data
public class FlightsDTO {
    private List<Vuelo> flightsGo;
    private List<Vuelo> flightsBack;
}
