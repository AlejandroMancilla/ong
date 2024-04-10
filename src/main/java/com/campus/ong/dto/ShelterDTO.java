package com.campus.ong.dto;

import com.campus.ong.controllers.ShelterController;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class ShelterDTO {
    
    @JsonView(ShelterController.class)
    private Long id;

    @JsonView(ShelterController.class)
    private String name;

    private Long city_id;
    @JsonView(ShelterController.class)
    private String city_name;
    
}
