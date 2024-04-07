package com.campus.ong.services;

import java.util.List;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.Shelter;

public interface ServiceShelter {
    
    List<Shelter> findAll();

    Shelter findById(Long id) throws BussinesRuleException;

    Shelter save(Shelter Shelter);

    Shelter update(Long id, Shelter Shelter);

    void delete(Long id);

}
