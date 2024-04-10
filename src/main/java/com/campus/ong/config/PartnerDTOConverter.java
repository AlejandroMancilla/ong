package com.campus.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.campus.ong.dto.PartnerDTO;
import com.campus.ong.repositories.entities.Partner;

@Component
public class PartnerDTOConverter {
    
    @Autowired
    private ModelMapper dbm;

    @Autowired
    public PartnerDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;

    }

    public PartnerDTO convertPartnerDTO(Partner partner){

        PartnerDTO partnerDTO = dbm.map(partner, PartnerDTO.class);
        partnerDTO.setCampus_name(partner.getCampus().getCity().getName());
        partnerDTO.setQuota_name(partner.getQuotaType().getName());
        partnerDTO.setUser_dni(partner.getUser().getDni());
        partnerDTO.setUser_fullName(partner.getUser().getName() + " " + partner.getUser().getLastname());
        partnerDTO.setUser_email(partner.getUser().getEmail());
        partnerDTO.setUser_phone(partner.getUser().getPhone_number());
        return partnerDTO;
    }

    public Partner convertPartnerEntity(PartnerDTO partnerDTO){
        return dbm.map(partnerDTO, Partner.class);   
    }

}
