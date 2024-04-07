package com.campus.ong.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampusDTO {
    
    private Long id;
    private String Address;

    private Long directorId;
    private String directorName;

    private Long cityId;
    private String cityName;

}
