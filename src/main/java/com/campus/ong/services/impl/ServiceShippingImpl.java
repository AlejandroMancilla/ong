package com.campus.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryShipping;
import com.campus.ong.repositories.entities.Shipping;
import com.campus.ong.services.ServiceShipping;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceShippingImpl implements ServiceShipping {
    
    private RepositoryShipping repositoryShipping;

    @Override
    @Transactional(readOnly = true)
    public List<Shipping> findAll() {
        return (List<Shipping>) repositoryShipping.findAll();
    }

    @Override
    public Shipping save(Shipping shipping) {
        return repositoryShipping.save(shipping);
    }

    @Override
    public Shipping update(Long id, Shipping shipping) {
        Optional<Shipping> shippingCurrentOptional = repositoryShipping.findById(id);

        if(shippingCurrentOptional.isPresent()){
            Shipping shippingCurrent = shippingCurrentOptional.get();
            shippingCurrent.setFinished(shipping.isFinished());
            repositoryShipping.save(shippingCurrent);
            return shippingCurrent;
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
    public Shipping findById(Long id) throws BussinesRuleException {
        Optional<Shipping> shippingOptional = repositoryShipping.findById(id);
        if(!shippingOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1010","Error! Shipping doesn't exist", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return shippingOptional.get();
    }

    @Override
    public List<Shipping> findByFinished(Boolean state) throws BussinesRuleException {
        List<Shipping> shippings = repositoryShipping.findByFinished(state);

        if (shippings.isEmpty()) {
            BussinesRuleException exception= new BussinesRuleException("1011","Error! Doesn't exist Shipping with the Status " + state, HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        
        return shippings;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shipping> findByCampusesId(Long campusId) throws BussinesRuleException {
        List<Shipping> shippings = repositoryShipping.findByCampusesId(campusId);
        
        if (shippings.isEmpty()) {
            BussinesRuleException exception= new BussinesRuleException("1012","Error! Doesn't exist Shipping created by Campus" + campusId, HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return shippings;
    }
      

}
