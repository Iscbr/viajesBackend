package com.api.travel.DTO;

import com.api.travel.Entity.Vuelo;
import com.api.travel.Util.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.List;

@Data
public class FlightsDTO {
    @JsonView(View.Summary.class)
    private List<Vuelo> flightsGo;
    @JsonView(View.Summary.class)
    private List<Vuelo> flightsBack;
}
