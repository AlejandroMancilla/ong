package com.campus.ong.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.campus.ong.repositories.entities.RolType;
import com.campus.ong.repositories.entities.UserE;

public interface RepositoryUser extends CrudRepository<UserE, Long> {
    
    UserE findByEmail(String email);

    List<UserE> findByRole(RolType role);

}
