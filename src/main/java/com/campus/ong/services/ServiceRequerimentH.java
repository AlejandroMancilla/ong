package com.campus.ong.services;

import java.util.List;

import com.campus.ong.dto.RequerimentVolunteersDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.RequerimentH;

public interface ServiceRequerimentH {

    List<RequerimentH> findAll();

    RequerimentH createRequerimentWithVolunteers(RequerimentVolunteersDTO requerimentWithVolunteersDTO) throws BussinesRuleException;

}
