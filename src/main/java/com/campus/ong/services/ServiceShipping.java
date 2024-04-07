package com.campus.ong.services;

import java.util.List;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.Shipping;

public interface ServiceShipping {

    List<Shipping> findAll();

    Shipping findById(Long id) throws BussinesRuleException;
    
    List<Shipping> findByFinished(Boolean state) throws BussinesRuleException;

    List<Shipping> findByCampusesId(Long CampusId) throws BussinesRuleException;

    Shipping save(Shipping Shipping);

    Shipping update(Long id, Shipping Shipping);

    void delete(Long id);
    
}
