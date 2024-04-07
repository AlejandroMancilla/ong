package com.campus.ong.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.campus.ong.repositories.entities.RolType;
import com.campus.ong.repositories.entities.User;

public interface RepositoryUser extends CrudRepository<User, Long> {
    
    User findByEmail(String email);

    List<User> findByRole(RolType role);

}
