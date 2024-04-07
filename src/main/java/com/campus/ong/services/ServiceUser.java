package com.campus.ong.services;

import java.util.List;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.RolType;
import com.campus.ong.repositories.entities.User;

public interface ServiceUser {
    
    List<User> findAll();

    User findById(Long id) throws BussinesRuleException;

    User save(User User);

    User update(Long id, User User);

    void delete(Long id);

    User findByEmail(String email);

    List<User> findByRole(RolType role);

}
