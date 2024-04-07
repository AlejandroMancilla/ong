package com.campus.ong.services;

import java.util.List;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.RolType;
import com.campus.ong.repositories.entities.UserE;

public interface ServiceUser {
    
    List<UserE> findAll();

    UserE findById(Long id) throws BussinesRuleException;

    UserE save(UserE User);

    UserE update(Long id, UserE User);

    void delete(Long id);

    UserE findByEmail(String email);

    List<UserE> findByRole(RolType role);

}
