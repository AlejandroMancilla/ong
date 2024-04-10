package com.campus.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.campus.ong.dto.ShelterDTO;
import com.campus.ong.repositories.entities.Shelter;

@Component
public class ShelterDTOConverter {

    @Autowired
    private ModelMapper dbm;

    public ShelterDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public ShelterDTO convertShelterDTO(Shelter shelter) {
        
        ShelterDTO shelterDTO = dbm.map(shelter, ShelterDTO.class);
        shelterDTO.setCity_name(shelter.getCity().getName());
        return shelterDTO;

    }

    public Shelter convertShelter(ShelterDTO shelterDTO) {
        
        Shelter shelter = dbm.map(shelterDTO, Shelter.class);
        return shelter;

    }
    
}
