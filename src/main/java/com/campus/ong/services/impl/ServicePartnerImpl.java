package com.campus.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.config.PartnerDTOConverter;
import com.campus.ong.dto.PartnerDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryCampus;
import com.campus.ong.repositories.RepositoryPartner;
import com.campus.ong.repositories.RepositoryQuota;
import com.campus.ong.repositories.RepositoryUser;
import com.campus.ong.repositories.entities.Campus;
import com.campus.ong.repositories.entities.Partner;
import com.campus.ong.repositories.entities.QuotaType;
import com.campus.ong.repositories.entities.UserE;
import com.campus.ong.services.ServicePartner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ServicePartnerImpl implements ServicePartner{
    
    @Autowired
    private RepositoryCampus repositoryCampus;
    private RepositoryQuota repositoryQuota;
    private RepositoryPartner repositoryPartner;
    private RepositoryUser repositoryUser;

    @Autowired
    private PartnerDTOConverter convert;
    
    @Override
    @Transactional(readOnly = true)
    public List<PartnerDTO> findAll() {
        List<Partner> partners = (List<Partner>) repositoryPartner.findAll();
        return partners.stream()
                     .map(partner->convert.convertPartnerDTO(partner))
                     .toList();
    }

    @Override
    public PartnerDTO findById(Long id) throws BussinesRuleException {
        Optional<Partner> partnerOptional = repositoryPartner.findById(id);
        if(!partnerOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1013","Error! Partner doesn't exist", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return convert.convertPartnerDTO(partnerOptional.get());
    }

    @Override
    public PartnerDTO save(PartnerDTO partner) {
        Optional<UserE> userOptional = repositoryUser.findById(partner.getUser_id());
        Optional<Campus> campusOptional = repositoryCampus.findById(partner.getCampus_id());
        Optional<QuotaType> quotaOptional = repositoryQuota.findById(partner.getQuota_id());

        if(userOptional.isPresent() && campusOptional.isPresent() && quotaOptional.isPresent()){
            Partner partnerEntity = convert.convertPartnerEntity(partner);
            partnerEntity.setUser(userOptional.get());
            partnerEntity.setCampus(campusOptional.get());
            partnerEntity.setNumAccount(partner.getNumAccount());
            partnerEntity.setPaymeentAt(partner.getPaymeentAt());
            partnerEntity.setQuotaType(quotaOptional.get());
            repositoryPartner.save(partnerEntity);
            return convert.convertPartnerDTO(partnerEntity);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Partner> partnerOptional=repositoryPartner.findById(id);
        if(partnerOptional.isPresent()){
            repositoryPartner.delete(partnerOptional.get());
        }   
    }
    @Override
    public List<PartnerDTO> findByQuotaTypeId(Long id) throws BussinesRuleException {
        List<Partner> partners =(List<Partner>) repositoryPartner.findByQuotaTypeId(id);
        
        if (partners.isEmpty()) {
            BussinesRuleException exception= new BussinesRuleException("1014","Error! Doesn't exist Partners with this Quota Type", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }

        return partners.stream()
                     .map(partner -> convert.convertPartnerDTO(partner))
                     .toList();
    }

    @Override
    public List<PartnerDTO>findByCampusId(Long id) throws BussinesRuleException {
        List<Partner> partnersOptional =(List<Partner>) repositoryPartner.findByCampusId(id);
        if (partnersOptional.isEmpty()) {
            BussinesRuleException exception= new BussinesRuleException("1015","Error! Doesn't exist Partners in this Campus", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }

        return partnersOptional.stream()
                     .map(partner -> convert.convertPartnerDTO(partner))
                     .toList();
    }

    
}
