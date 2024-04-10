package com.campus.ong.services;

import java.util.List;

import com.campus.ong.dto.ShippingDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.Shipping;

public interface ServiceShipping {

    List<ShippingDTO> findAll();

    ShippingDTO findById(Long id) throws BussinesRuleException;
    
    List<ShippingDTO> findByFinished(Boolean state) throws BussinesRuleException;

    List<ShippingDTO> findByCampusesId(Long CampusId) throws BussinesRuleException;

    Shipping save(ShippingDTO Shipping) throws BussinesRuleException;

    void delete(Long id);

    ShippingDTO finishShipping(Long shippingId) throws BussinesRuleException;
    
}
