package com.campus.ong.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.config.ShippingDTOConverter;
import com.campus.ong.dto.ShippingDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryCampus;
import com.campus.ong.repositories.RepositoryShelter;
import com.campus.ong.repositories.RepositoryShipping;
import com.campus.ong.repositories.RepositoryVolunteerH;
import com.campus.ong.repositories.entities.Campus;
import com.campus.ong.repositories.entities.RequerimentH;
import com.campus.ong.repositories.entities.Shelter;
import com.campus.ong.repositories.entities.Shipping;
import com.campus.ong.repositories.entities.VolunteerH;
import com.campus.ong.services.ServiceShipping;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceShippingImpl implements ServiceShipping {
    
    private RepositoryShipping repositoryShipping;
    private RepositoryVolunteerH repositoryVolunteerH;
    private RepositoryShelter repositoryShelter;
    private RepositoryCampus repositoryCampus;

    @Autowired
    private ShippingDTOConverter convert;

    @Override
    @Transactional(readOnly = true)
    public List<ShippingDTO> findAll() {
        List<Shipping> shippings = (List<Shipping>) repositoryShipping.findAll();
        return shippings.stream()
                     .map(shipping -> convert.convertShippingDTO(shipping))
                     .toList();
    }

    @Override
    public Shipping save(ShippingDTO shipping) throws BussinesRuleException {
        Optional<Shelter> shelterOptional = repositoryShelter.findById(shipping.getShelter_id());
        List<Campus> campuses = new ArrayList<>();
        for(Long campusId : shipping.getCampus_ids()) {
            Optional<Campus> campusOptional = repositoryCampus.findById(campusId);
            if (campusOptional.isPresent()) {
                campuses.add(campusOptional.get());
            }else{
                BussinesRuleException exception= new BussinesRuleException("1058","Error! Campus doesn't exists" + campusId, HttpStatus.PRECONDITION_FAILED);
                throw exception; 
            }
        }        
        if(shelterOptional.isPresent() && !campuses.isEmpty()){
            Shipping shippingEntity = convert.convertShipping(shipping);
            shippingEntity.setCampuses(campuses);
            shippingEntity.setShelter(shelterOptional.get());
            return shippingEntity;
        }
        return null;
    }


    @Override
    public void delete(Long id) {
        Optional<Shipping> shippingOptional=repositoryShipping.findById(id);
        if(shippingOptional.isPresent()){
            repositoryShipping.delete(shippingOptional.get());
        }   
    }

    @Override
    public ShippingDTO findById(Long id) throws BussinesRuleException {
        Optional<Shipping> shippingOptional = repositoryShipping.findById(id);
        if(!shippingOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1059","Error! Shipping doesn't exist", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return convert.convertShippingDTO(shippingOptional.get());
    }

    @Override
    public List<ShippingDTO> findByFinished(Boolean state) throws BussinesRuleException {
        List<Shipping> shippings = (List<Shipping>) repositoryShipping.findByFinished(state);

        if (shippings.isEmpty()) {
            BussinesRuleException exception= new BussinesRuleException("1011","Error! Doesn't exist Shipping with the Status " + state, HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        
        return shippings.stream()
                     .map(shipping -> convert.convertShippingDTO(shipping))
                     .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShippingDTO> findByCampusesId(Long campusId) throws BussinesRuleException {
        List<Shipping> shippings = (List<Shipping>) repositoryShipping.findByCampusesId(campusId);
        
        if (shippings.isEmpty()) {
            BussinesRuleException exception= new BussinesRuleException("1012","Error! Doesn't exist Shipping created by Campus" + campusId, HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return shippings.stream()
                     .map(shipping -> convert.convertShippingDTO(shipping))
                     .toList();
    }

    @Override
    public ShippingDTO finishShipping(Long shippingId) throws BussinesRuleException {
        Shipping shipping = repositoryShipping.findById(shippingId)
                .orElseThrow(() -> new BussinesRuleException("1055", "Shipping not found", HttpStatus.PRECONDITION_FAILED));

        // Verificar si el envío ya está finalizado
        if (shipping.isFinished()) {
            throw new BussinesRuleException("1056", "Shipping already finished", HttpStatus.PRECONDITION_FAILED);
        }

        // Finalizar el envío
        shipping.setFinished(true);
        repositoryShipping.save(shipping);

        // Actualizar el estado de los voluntarios asociados a los requerimientos de este envío
        List<RequerimentH> requeriments = shipping.getRequeriments();
        for (RequerimentH requeriment : requeriments) {
            List<VolunteerH> volunteers = requeriment.getVolunteers();
            for (VolunteerH volunteer : volunteers) {
                volunteer.setAvailable(true);
                volunteer.setNum_missions(volunteer.getNum_missions() + 1);
                repositoryVolunteerH.save(volunteer);
            }
        }

        return convert.convertShippingDTO(shipping);
    }
      

}
