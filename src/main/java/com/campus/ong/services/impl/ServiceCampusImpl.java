package com.campus.ong.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.config.CampusDTOConverter;
import com.campus.ong.dto.CampusDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryCampus;
import com.campus.ong.repositories.RepositoryCity;
import com.campus.ong.repositories.RepositoryUser;
import com.campus.ong.repositories.entities.Campus;
import com.campus.ong.repositories.entities.City;
import com.campus.ong.repositories.entities.UserE;
import com.campus.ong.services.ServiceCampus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceCampusImpl implements ServiceCampus{
    
    @Autowired
    private RepositoryCampus repositoryCampus;
    private RepositoryUser repositoryUser;
    private RepositoryCity repositoryCity;
    
    @Autowired
    private CampusDTOConverter convert;

    @Override
    @Transactional(readOnly = true)
    public List<CampusDTO> findAll() {
        List<Campus> campuses = (List<Campus>) repositoryCampus.findAll();
        return campuses.stream()
                     .map(campus->convert.convertCampusDTO(campus))
                     .toList();

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
    public CampusDTO save(CampusDTO campus) {
        Optional<UserE> userOptional = repositoryUser.findById(campus.getDirectorId());
        Optional<City> cityOptional = repositoryCity.findById(campus.getCityId());

        if(userOptional.isPresent() && cityOptional.isPresent()){
            Campus campusEntity = convert.convertCampusEntity(campus);
            campusEntity.setDirector(userOptional.get());
            campusEntity.setCity(cityOptional.get());
            repositoryCampus.save(campusEntity);
            return convert.convertCampusDTO(campusEntity);
        }

        return null;
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
