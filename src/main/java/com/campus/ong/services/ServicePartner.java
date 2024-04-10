package com.campus.ong.services;

import java.util.List;

import com.campus.ong.dto.PartnerDTO;
import com.campus.ong.exception.BussinesRuleException;


public interface ServicePartner {

    List<PartnerDTO> findAll();

    PartnerDTO findById(Long id) throws BussinesRuleException;

    PartnerDTO save(PartnerDTO partner);

    void delete(Long id);

    List<PartnerDTO> findByCampusId(Long id) throws BussinesRuleException;

    List<PartnerDTO> findByQuotaTypeId(Long id) throws BussinesRuleException;
    
}
