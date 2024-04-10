package com.campus.ong.dto;

import com.campus.ong.controllers.ShippingController;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class MaterialDTO {
    
    private Long id;

    private Long product_id;
    @JsonView(ShippingController.class)
    private String product_name;

    @JsonView(ShippingController.class)
    private float amount;

    private Long shipping_id;

}
