package com.campus.ong.services;

import java.util.List;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.Occupation;

public interface ServiceOccupation {
    
    List<Occupation> findAll();

    Occupation findById(Long id) throws BussinesRuleException;

    Occupation save(Occupation occupation);

    Occupation update(Long id, Occupation occupation);

    void delete(Long id);

}
