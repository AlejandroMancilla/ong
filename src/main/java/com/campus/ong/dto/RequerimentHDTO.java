package com.campus.ong.dto;

import java.util.List;

import com.campus.ong.controllers.ShippingController;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class RequerimentHDTO {
    
    private Long id;

    private Long occupation_id;
    @JsonView(ShippingController.class)
    private String occupation_name;
    @JsonView(ShippingController.class)
    private Float amount;
    @JsonView(ShippingController.class)
    private List<VolunteerDTO> volunteers;

}
