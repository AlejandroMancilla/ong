package com.campus.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.campus.ong.dto.VolunteerDTO;
import com.campus.ong.repositories.entities.VolunteerH;

@Component
public class VolunteerDTOConverter {
    
    @Autowired
    private ModelMapper dbm;

    public VolunteerDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public VolunteerDTO convertVolunteerDTO(VolunteerH volunteer) {
        
        VolunteerDTO volunteerDTO = dbm.map(volunteer, VolunteerDTO.class);
        volunteerDTO.setUser_dni(volunteer.getVolunteer().getUser().getDni());
        volunteerDTO.setUser_fullName(volunteer.getVolunteer().getUser().getName() + " " + volunteer.getVolunteer().getUser().getLastname());
        volunteerDTO.setUser_email(volunteer.getVolunteer().getUser().getEmail());
        volunteerDTO.setUser_phone(volunteer.getVolunteer().getUser().getPhone_number());
        volunteerDTO.setOccupation_name(volunteer.getOccupation().getName());
        volunteerDTO.setCity_name(volunteer.getVolunteer().getCampus().getCity().getName());
        return volunteerDTO;

    }

    public VolunteerH convertVolunteer(VolunteerDTO volunteerDTO) {
        
        VolunteerH volunteer = dbm.map(volunteerDTO, VolunteerH.class);
        return volunteer;

    }
    
}
