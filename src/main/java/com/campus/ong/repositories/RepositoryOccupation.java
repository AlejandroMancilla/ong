package com.campus.ong.repositories;

import org.springframework.data.repository.CrudRepository;

import com.campus.ong.repositories.entities.Occupation;

public interface RepositoryOccupation extends CrudRepository<Occupation, Long> {
    
}
