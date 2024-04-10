package com.campus.ong.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.campus.ong.repositories.entities.Shelter;

public interface RepositoryShelter extends CrudRepository<Shelter, Long> {

    List<Shelter> findByCityId(Long id);
    
}
