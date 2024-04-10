package com.campus.ong.dto;

import java.sql.Date;
import java.util.List;

import com.campus.ong.controllers.ShippingController;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class ShippingDTO {
    
    private Long id;

    @JsonView(ShippingController.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startsAt;
    
    @JsonView(ShippingController.class)
    private boolean state;

    private Long shelter_id;
    @JsonView(ShippingController.class)
    private String shelter_name;

    private List<Long> campus_ids;
    @JsonView(ShippingController.class)
    private List<String> campus_names;

    @JsonView(ShippingController.class)
    private List<MaterialDTO> materials;

}
