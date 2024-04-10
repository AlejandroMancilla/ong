package com.campus.ong.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.Partner;

public interface RepositoryPartner extends CrudRepository<Partner, Long> {
    
    List<Partner> findByCampusId(Long id) throws BussinesRuleException;

    List<Partner> findByQuotaTypeId(Long id);

}
