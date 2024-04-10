package com.campus.ong.services;

import java.util.List;

import com.campus.ong.dto.ShelterDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.Shelter;

public interface ServiceShelter {
    
    List<ShelterDTO> findAll();

    ShelterDTO findById(Long id) throws BussinesRuleException;

    ShelterDTO save(Shelter Shelter);

    ShelterDTO update(Long id, Shelter Shelter);

    void delete(Long id);

    List<ShelterDTO> findByCityId(Long id);

}
