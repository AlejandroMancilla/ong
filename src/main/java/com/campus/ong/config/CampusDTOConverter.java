package com.campus.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.campus.ong.dto.CampusDTO;
import com.campus.ong.repositories.entities.Campus;

@Component
public class CampusDTOConverter {
    
    @Autowired
    private ModelMapper dbm;

    @Autowired
    public CampusDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;

    }

    public CampusDTO convertCampusDTO(Campus campus){

        CampusDTO campusDTO = dbm.map(campus, CampusDTO.class);
        if(campus.getDirector() != null){
            campusDTO.setDirectorId(campus.getDirector().getId());
            campusDTO.setDirectorName(campus.getDirector().getName() + " " + campus.getDirector().getLastname());
        }
        if(campus.getCity() != null){
            campusDTO.setCityId(campus.getCity().getId());
            campusDTO.setCityName(campus.getCity().getName());
        }
        return campusDTO;
    }

    public Campus convertCampusEntity(CampusDTO campusDTO){
        return dbm.map(campusDTO, Campus.class);   
    }
}
