package com.campus.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryShelter;
import com.campus.ong.repositories.entities.Shelter;
import com.campus.ong.services.ServiceShelter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceShelterImpl implements ServiceShelter {
        
    private RepositoryShelter repositoryShelter;

    @Override
    @Transactional(readOnly = true)
    public List<Shelter> findAll() {
        return (List<Shelter>) repositoryShelter.findAll();
    }

    @Override
    public Shelter save(Shelter shelter) {
        return repositoryShelter.save(shelter);
    }

    @Override
    public Shelter update(Long id, Shelter shelter) {
        Optional<Shelter> shelterCurrentOptional = repositoryShelter.findById(id);

        if(shelterCurrentOptional.isPresent()){
            Shelter shelterCurrent = shelterCurrentOptional.get();
            shelterCurrent.setName(shelter.getName());
            repositoryShelter.save(shelterCurrent);
            return shelterCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Shelter> shelterOptional=repositoryShelter.findById(id);
        if(shelterOptional.isPresent()){
            repositoryShelter.delete(shelterOptional.get());
        }   
    }

    @Override
    public Shelter findById(Long id) throws BussinesRuleException {
        Optional<Shelter> shelterOptional = repositoryShelter.findById(id);
        if(!shelterOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1006","Error! Shelter doesn't exist", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return shelterOptional.get();
    }

   
}
