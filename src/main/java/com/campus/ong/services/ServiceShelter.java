package com.campus.ong.services;

import java.util.List;

import com.campus.ong.dto.ShelterDTO;
import com.campus.ong.exception.BussinesRuleException;

public interface ServiceShelter {
    
    List<ShelterDTO> findAll();

    ShelterDTO findById(Long id) throws BussinesRuleException;

    ShelterDTO save(ShelterDTO Shelter);

    ShelterDTO update(Long id, ShelterDTO Shelter);

    void delete(Long id);

    List<ShelterDTO> findByCityId(Long id) throws BussinesRuleException;

}
