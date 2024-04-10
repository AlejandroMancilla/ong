package com.campus.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.config.ShelterDTOConverter;
import com.campus.ong.dto.ShelterDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryCity;
import com.campus.ong.repositories.RepositoryShelter;
import com.campus.ong.repositories.entities.City;
import com.campus.ong.repositories.entities.Shelter;
import com.campus.ong.services.ServiceShelter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceShelterImpl implements ServiceShelter {
        
    private RepositoryShelter repositoryShelter;
    private RepositoryCity repositoryCity;

    @Autowired
    private ShelterDTOConverter convert;

    @Override
    @Transactional(readOnly = true)
    public List<ShelterDTO> findAll() {
        List<Shelter> shelters = (List<Shelter>) repositoryShelter.findAll();
        return shelters.stream()
                     .map(shelter -> convert.convertShelterDTO(shelter))
                     .toList();
    }

    @Override
    public ShelterDTO findById(Long id) throws BussinesRuleException {
        Optional<Shelter> shelterOptional = repositoryShelter.findById(id);
        if(!shelterOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1006","Error! Shelter doesn't exist", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return convert.convertShelterDTO(shelterOptional.get());
    }

    @Override
    public List<ShelterDTO> findByCityId(Long id) throws BussinesRuleException {
        List<Shelter> shelters =(List<Shelter>) repositoryShelter.findByCityId(id);
        
        if (shelters.isEmpty()) {
            BussinesRuleException exception= new BussinesRuleException("1014","Error! Doesn't exist Partners with this Quota Type", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }

        return shelters.stream()
                     .map(shelter -> convert.convertShelterDTO(shelter))
                     .toList();
    }

    @Override
    public ShelterDTO save(ShelterDTO shelter) {
        Optional<City> cityOptional = repositoryCity.findById(shelter.getCity_id());

        if(cityOptional.isPresent()){
            Shelter shelterEntity = convert.convertShelter(shelter);
            shelterEntity.setCity(cityOptional.get());
            repositoryShelter.save(shelterEntity);
            return convert.convertShelterDTO(shelterEntity);
        }
        return null;
    }

    @Override
    public ShelterDTO update(Long id, ShelterDTO shelter) {
        Optional<City> cityOptional = repositoryCity.findById(shelter.getCity_id());
        Optional<Shelter> shelterCurrentOptional = repositoryShelter.findById(id);

        if(shelterCurrentOptional.isPresent() && cityOptional.isPresent()){
            Shelter shelterCurrent = shelterCurrentOptional.get();
            shelterCurrent.setName(shelter.getName());
            shelterCurrent.setCity(cityOptional.get());
            repositoryShelter.save(shelterCurrent);
            return convert.convertShelterDTO(shelterCurrent);
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

    

   
}
