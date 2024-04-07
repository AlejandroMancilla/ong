package com.campus.ong.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.campus.ong.repositories.entities.Shipping;

public interface RepositoryShipping extends CrudRepository<Shipping, Long> {

    List<Shipping> findByCampusesId(Long campusId);

    List<Shipping> findByFinished(Boolean state);
    
}
