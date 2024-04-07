package com.campus.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryVolunteer;
import com.campus.ong.repositories.entities.Volunteer;
import com.campus.ong.services.ServiceVolunteer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceVolunteerImpl implements ServiceVolunteer {

    private RepositoryVolunteer repositoryVolunteer;

    @Override
    @Transactional(readOnly = true)
    public List<Volunteer> findAll() {
        return (List<Volunteer>) repositoryVolunteer.findAll();
    }

    @Override
    public Volunteer save(Volunteer volunteer) {
        return repositoryVolunteer.save(volunteer);
    }

    @Override
    public void delete(Long id) {
        Optional<Volunteer> volunteerOptional=repositoryVolunteer.findById(id);
        if(volunteerOptional.isPresent()){
            repositoryVolunteer.delete(volunteerOptional.get());
        }   
    }

    @Override
    public Volunteer findById(Long id) throws BussinesRuleException {
        Optional<Volunteer> volunteerOptional = repositoryVolunteer.findById(id);
        if(!volunteerOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1008","Error! Volunteer doesn't exist", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return volunteerOptional.get();
    }
    
}
