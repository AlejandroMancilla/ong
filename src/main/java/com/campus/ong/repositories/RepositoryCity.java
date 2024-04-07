package com.campus.ong.repositories;

import org.springframework.data.repository.CrudRepository;

import com.campus.ong.repositories.entities.City;

public interface RepositoryCity extends CrudRepository<City, Long> {
    

}
