package com.campus.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.config.VolunteerDTOConverter;
import com.campus.ong.dto.VolunteerDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryCampus;
import com.campus.ong.repositories.RepositoryOccupation;
import com.campus.ong.repositories.RepositoryVolunteer;
import com.campus.ong.repositories.RepositoryVolunteerH;
import com.campus.ong.repositories.entities.Campus;
import com.campus.ong.repositories.entities.Occupation;
import com.campus.ong.repositories.entities.Volunteer;
import com.campus.ong.repositories.entities.VolunteerH;
import com.campus.ong.services.ServiceVolunteer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceVolunteerImpl implements ServiceVolunteer {

    private RepositoryVolunteerH repositoryVolunteerH;
    private RepositoryVolunteer repositoryVolunteer;
    private RepositoryCampus repositoryCampus;
    private RepositoryOccupation repositoryOccupation;

    @Autowired
    private VolunteerDTOConverter convert;

    @Override
    @Transactional(readOnly = true)
    public List<VolunteerDTO> findAll() {
        List<VolunteerH> volunteers = (List<VolunteerH>) repositoryVolunteerH.findAll();
        return volunteers.stream()
                     .map(volunteer -> convert.convertVolunteerDTO(volunteer))
                     .toList();
    }

    @Override
    public VolunteerDTO save(VolunteerDTO volunteer) {
        Optional<Volunteer> volunteerOptional = repositoryVolunteer.findById(volunteer.getVolunteer_id());
        Optional<Campus> campusOptional = repositoryCampus.findById(volunteer.getCampus_id());
        Optional<Occupation> occupationOptional = repositoryOccupation.findById(volunteer.getOccupation_id());

        if(volunteerOptional.isPresent() && campusOptional.isPresent() && occupationOptional.isPresent()){
            VolunteerH volunteerHEntity = convert.convertVolunteer(volunteer);
            volunteerHEntity.setOccupation(occupationOptional.get());
            volunteerHEntity.setVolunteer(volunteerOptional.get());
            repositoryVolunteerH.save(volunteerHEntity);
            return convert.convertVolunteerDTO(volunteerHEntity);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<VolunteerH> volunteerOptional=repositoryVolunteerH.findById(id);
        if(volunteerOptional.isPresent()){
            repositoryVolunteerH.delete(volunteerOptional.get());
        }    
    }

    @Override
    public VolunteerDTO findById(Long id) throws BussinesRuleException {
        Optional<VolunteerH> volunteerOptional = repositoryVolunteerH.findById(id);
        if(!volunteerOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1008","Error! Volunteer doesn't exist", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return convert.convertVolunteerDTO(volunteerOptional.get());
    }
    
}
