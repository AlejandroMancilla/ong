package com.campus.ong.services;

import java.util.List;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.Volunteer;

public interface ServiceVolunteer {
    
    List<Volunteer> findAll();

    Volunteer findById(Long id) throws BussinesRuleException;

    Volunteer save(Volunteer Volunteer);

    void delete(Long id);

}
