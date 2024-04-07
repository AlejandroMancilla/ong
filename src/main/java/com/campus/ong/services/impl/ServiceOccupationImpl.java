package com.campus.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryOccupation;
import com.campus.ong.repositories.entities.Occupation;
import com.campus.ong.services.ServiceOccupation;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceOccupationImpl implements ServiceOccupation {
    
    private RepositoryOccupation repositoryOccupation;

    @Override
    @Transactional(readOnly = true)
    public List<Occupation> findAll() {
        return (List<Occupation>) repositoryOccupation.findAll();
    }

    @Override
    public Occupation save(Occupation occupation) {
        return repositoryOccupation.save(occupation);
    }

    @Override
    public Occupation update(Long id, Occupation occupation) {
        Optional<Occupation> occupationCurrentOptional = repositoryOccupation.findById(id);

        if(occupationCurrentOptional.isPresent()){
            Occupation occupationCurrent = occupationCurrentOptional.get();
            occupationCurrent.setName(occupation.getName());
            repositoryOccupation.save(occupationCurrent);
            return occupationCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Occupation> occupationOptional=repositoryOccupation.findById(id);
        if(occupationOptional.isPresent()){
            repositoryOccupation.delete(occupationOptional.get());
        }   
    }

    @Override
    public Occupation findById(Long id) throws BussinesRuleException {
        Optional<Occupation> occupationOptional = repositoryOccupation.findById(id);
        if(!occupationOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1009","Error! Occupation doesn't exist",HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return occupationOptional.get();
    }
    
}
