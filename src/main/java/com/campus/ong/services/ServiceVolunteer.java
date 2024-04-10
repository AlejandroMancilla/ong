package com.campus.ong.services;

import java.util.List;

import com.campus.ong.dto.VolunteerDTO;
import com.campus.ong.exception.BussinesRuleException;

public interface ServiceVolunteer {
    
    List<VolunteerDTO> findAll();

    VolunteerDTO findById(Long id) throws BussinesRuleException;

    VolunteerDTO save(VolunteerDTO Volunteer);

    void delete(Long id);

}
