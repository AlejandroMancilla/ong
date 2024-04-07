package com.campus.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryUser;
import com.campus.ong.repositories.entities.RolType;
import com.campus.ong.repositories.entities.User;
import com.campus.ong.services.ServiceUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceUserImpl implements ServiceUser{
    
    private RepositoryUser repositoryUser;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repositoryUser.findAll();
    }

    @Override
    public User save(User user) {
        return repositoryUser.save(user);
    }

    @Override
    public User update(Long id, User user) {
        Optional<User> userCurrentOptional = repositoryUser.findById(id);

        if(userCurrentOptional.isPresent()){
            User userCurrent = userCurrentOptional.get();
            userCurrent.setName(user.getName());
            repositoryUser.save(userCurrent);
            return userCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<User> userOptional=repositoryUser.findById(id);
        if(userOptional.isPresent()){
            repositoryUser.delete(userOptional.get());
        }   
    }

    @Override
    public User findById(Long id) throws BussinesRuleException {
        Optional<User> userOptional = repositoryUser.findById(id);
        if(!userOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1004","Error! User doesn't exist", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return userOptional.get();
    }

    public User findByEmail(String email) {
        return repositoryUser.findByEmail(email);
    }

    @Override
    public List<User> findByRole(RolType role) {
        return repositoryUser.findByRole(role);
    }
}
