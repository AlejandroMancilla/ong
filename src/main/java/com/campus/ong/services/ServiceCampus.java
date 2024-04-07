package com.campus.ong.services;

import java.util.List;

import com.campus.ong.dto.CampusDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.Campus;

public interface ServiceCampus {
    
    List<CampusDTO> findAll();

    CampusDTO save(CampusDTO Campus);

    Campus update(Long id, Campus Campus);

    void delete(Long id);

    Campus findById(Long id) throws BussinesRuleException;

}
