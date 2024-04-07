package com.campus.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryCity;
import com.campus.ong.repositories.entities.City;
import com.campus.ong.services.ServiceCity;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ServiceCityImpl implements ServiceCity {
    
    private RepositoryCity repositoryCity;

    @Override
    @Transactional(readOnly = true)
    public List<City> findAll() {
        return (List<City>) repositoryCity.findAll();
    }

    @Override
    public City save(City city) {
        return repositoryCity.save(city);
    }

    @Override
    public City update(Long id, City city) {
        Optional<City> cityCurrentOptional = repositoryCity.findById(id);

        if(cityCurrentOptional.isPresent()){
            City cityCurrent = cityCurrentOptional.get();
            cityCurrent.setName(city.getName());
            repositoryCity.save(cityCurrent);
            return cityCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<City> cityOptional=repositoryCity.findById(id);
        if(cityOptional.isPresent()){
            repositoryCity.delete(cityOptional.get());
        }   
    }

    @Override
    public City findById(Long id) throws BussinesRuleException {
        Optional<City> cityOptional = repositoryCity.findById(id);
        if(!cityOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1001","Error! City doesn't exist",HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return cityOptional.get();
    }
    
}
