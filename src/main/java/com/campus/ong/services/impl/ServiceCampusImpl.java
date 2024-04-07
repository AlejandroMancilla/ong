package com.campus.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryCampus;
import com.campus.ong.repositories.entities.Campus;
import com.campus.ong.services.ServiceCampus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceCampusImpl implements ServiceCampus{
    
    private RepositoryCampus repositoryCampus;

    @Override
    @Transactional(readOnly = true)
    public List<Campus> findAll() {
        return (List<Campus>) repositoryCampus.findAll();
    }

    @Override
    public Campus findById(Long id) throws BussinesRuleException {
        Optional<Campus> campusOptional = repositoryCampus.findById(id);
        if(!campusOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1005","Error! Campus doesn't exist", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return campusOptional.get();
    }

    @Override
    public Campus save(Campus campus) {
        return repositoryCampus.save(campus);
    }

    @Override
    public Campus update(Long id, Campus campus) {
        Optional<Campus> campusCurrentOptional = repositoryCampus.findById(id);

        if(campusCurrentOptional.isPresent()){
            Campus campusCurrent = campusCurrentOptional.get();
            campusCurrent.setAddress(campus.getAddress());
            repositoryCampus.save(campusCurrent);
            return campusCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Campus> campusOptional=repositoryCampus.findById(id);
        if(campusOptional.isPresent()){
            repositoryCampus.delete(campusOptional.get());
        }   
    }


    
}
