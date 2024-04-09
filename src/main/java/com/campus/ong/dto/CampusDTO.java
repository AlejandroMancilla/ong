package com.campus.ong.dto;

import com.campus.ong.controllers.CampusController;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampusDTO {
    
    private Long id;
    @JsonView(CampusController.class)
    private String address;

    private Long directorId;
    @JsonView(CampusController.class)
    private String directorName;

    private Long cityId;
    @JsonView(CampusController.class)
    private String cityName;

}
