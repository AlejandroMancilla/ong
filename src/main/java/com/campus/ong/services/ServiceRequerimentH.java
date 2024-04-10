package com.campus.ong.services;

import java.util.List;

import com.campus.ong.repositories.entities.RequerimentH;

public interface ServiceRequerimentH {

    List<RequerimentH> findAll();

    // RequerimentH createRequerimentWithVolunteers(RequerimentHDTO requerimentWithVolunteersDTO) throws BussinesRuleException;

}
