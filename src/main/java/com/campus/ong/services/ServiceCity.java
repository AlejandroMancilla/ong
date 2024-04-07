package com.campus.ong.services;

import java.util.List;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.City;

public interface ServiceCity {
    
    List<City> findAll();

    City findById(Long id) throws BussinesRuleException;

    City save(City city);

    City update(Long id, City city);

    void delete(Long id);

}
