package com.campus.ong.dto;

import com.campus.ong.repositories.entities.VolunteerH;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class VolunteerDTO {

    private Long id;

    private Long volunteer_id;
    @JsonView(VolunteerH.class)
    private String user_dni;
    @JsonView(VolunteerH.class)
    private String user_fullName;
    @JsonView(VolunteerH.class)
    private String user_email;
    @JsonView(VolunteerH.class)
    private String user_phone;

    private Long occupation_id;
    @JsonView(VolunteerH.class)
    private String occupation_name;

    @JsonView(VolunteerH.class)
    private Boolean available;

    @JsonView(VolunteerH.class)
    private int num_missions;

    private Long campus_id;
    @JsonView(VolunteerH.class)
    private String city_name;
    
}
