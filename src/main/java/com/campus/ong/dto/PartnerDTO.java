package com.campus.ong.dto;

import java.util.Date;

import com.campus.ong.controllers.PartnerController;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class PartnerDTO {
    
    private Long id;

    @JsonView(PartnerController.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date paymeentAt;
    @JsonView(PartnerController.class)
    private String numAccount;

    private Long user_id;
    @JsonView(PartnerController.class)
    private String user_dni;
    @JsonView(PartnerController.class)
    private String user_fullName;
    @JsonView(PartnerController.class)
    private String user_email;
    @JsonView(PartnerController.class)
    private String user_phone;

    private Long quota_id;
    @JsonView(PartnerController.class)
    private String quota_name;

    private Long campus_id;
    @JsonView(PartnerController.class)
    private String campus_name;

}
