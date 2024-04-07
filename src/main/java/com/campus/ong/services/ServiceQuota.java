package com.campus.ong.services;

import java.util.List;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.QuotaType;

public interface ServiceQuota {
    
    List<QuotaType> findAll();

    QuotaType findById(Long id) throws BussinesRuleException;

    QuotaType save(QuotaType quota);

    QuotaType update(Long id, QuotaType quota);

    void delete(Long id);

}
