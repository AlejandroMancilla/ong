package com.campus.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryQuota;
import com.campus.ong.repositories.entities.QuotaType;
import com.campus.ong.services.ServiceQuota;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ServiceQuotaImpl implements ServiceQuota {

    private RepositoryQuota repositoryQuota;

    @Override
    @Transactional(readOnly = true)
    public List<QuotaType> findAll() {
        return (List<QuotaType>) repositoryQuota.findAll();
    }

    @Override
    public QuotaType save(QuotaType quota) {
        return repositoryQuota.save(quota);
    }

    @Override
    public QuotaType update(Long id, QuotaType quota) {
        Optional<QuotaType> quotaCurrentOptional = repositoryQuota.findById(id);

        if(quotaCurrentOptional.isPresent()){
            QuotaType quotaCurrent = quotaCurrentOptional.get();
            quotaCurrent.setName(quota.getName());
            repositoryQuota.save(quotaCurrent);
            return quotaCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<QuotaType> quotaOptional=repositoryQuota.findById(id);
        if(quotaOptional.isPresent()){
            repositoryQuota.delete(quotaOptional.get());
        }   
    }

    @Override
    public QuotaType findById(Long id) throws BussinesRuleException {
        Optional<QuotaType> quotaOptional = repositoryQuota.findById(id);
        if(!quotaOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1002","Error! Quota doesn't exist",HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return quotaOptional.get();
    }


    
}
