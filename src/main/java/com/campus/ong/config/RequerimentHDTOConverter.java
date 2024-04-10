package com.campus.ong.config;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.campus.ong.dto.RequerimentHDTO;
import com.campus.ong.dto.VolunteerDTO;
import com.campus.ong.repositories.entities.RequerimentH;

@Component
public class RequerimentHDTOConverter {
    
    @Autowired
    private ModelMapper dbm;

    @Autowired
    private VolunteerDTOConverter volunteerDTOConverter;

    public RequerimentHDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public RequerimentHDTO convertRequerimentDTO(RequerimentH requeriment) {
        
        RequerimentHDTO requerimentDTO = dbm.map(requeriment, RequerimentHDTO.class);
        requerimentDTO.setOccupation_name(requeriment.getOccupation().getName());
        requerimentDTO.setAmount(requeriment.getAmount());

        List<VolunteerDTO> requerimentsDTOList = requeriment.getVolunteers().isEmpty() ?
                    requeriment.getVolunteers()
                                .stream()
                                .map(volunteer -> volunteerDTOConverter.convertVolunteerDTO(volunteer))
                                .toList():
        Collections.emptyList(); 

        requerimentDTO.setVolunteers(requerimentsDTOList);

        return requerimentDTO;
    }

    public RequerimentH convertRequerimentH(RequerimentHDTO requerimentHDTO) {
        
        RequerimentH requeriment = dbm.map(requerimentHDTO, RequerimentH.class);
        return requeriment;
    }

}
