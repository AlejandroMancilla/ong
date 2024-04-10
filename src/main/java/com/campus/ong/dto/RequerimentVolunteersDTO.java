package com.campus.ong.dto;

import java.util.List;

import com.campus.ong.repositories.entities.RequerimentH;

import lombok.Data;

@Data
public class RequerimentVolunteersDTO {
    
    private RequerimentH requeriment;
    private List<Long> volunteerIds;

}
