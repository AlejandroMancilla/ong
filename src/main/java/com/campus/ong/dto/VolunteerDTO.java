package com.campus.ong.dto;

import com.campus.ong.controllers.ShippingController;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerDTO {

    private Long id;

    private Long volunteer_id;
    @JsonView(ShippingController.class)
    private String user_dni;
    @JsonView(ShippingController.class)
    private String user_fullName;
    @JsonView(ShippingController.class)
    private String user_email;
    @JsonView(ShippingController.class)
    private String user_phone;

    private Long occupation_id;
    @JsonView(ShippingController.class)
    private String occupation_name;

    @JsonView(ShippingController.class)
    private Boolean available;

    @JsonView(ShippingController.class)
    private int num_missions;

    private Long campus_id;
    @JsonView(ShippingController.class)
    private String city_name;
    
}
