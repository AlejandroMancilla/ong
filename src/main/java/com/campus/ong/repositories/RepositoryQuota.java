package com.campus.ong.repositories;

import org.springframework.data.repository.CrudRepository;

import com.campus.ong.repositories.entities.QuotaType;

public interface RepositoryQuota extends CrudRepository<QuotaType, Long> {
    
    
}
