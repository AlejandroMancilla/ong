package com.campus.ong.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campus.ong.repositories.RepositoryRequerimentH;
import com.campus.ong.repositories.entities.RequerimentH;
import com.campus.ong.services.ServiceRequerimentH;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceRequerimentHImpl implements ServiceRequerimentH{

    private RepositoryRequerimentH repositoryRequerimentH;

    @Override
    public List<RequerimentH> findAll() {
        return (List<RequerimentH>) repositoryRequerimentH.findAll();
    }

    // @Override
    // public RequerimentH createRequerimentWithVolunteers(RequerimentHDTO requerimentWithVolunteersDTO) throws BussinesRuleException {
    //     RequerimentH requeriment = requerimentWithVolunteersDTO.getRequeriment();
    //     List<Long> volunteerIds = requerimentWithVolunteersDTO.getVolunteerIds();

    //     // Verificar si el requerimiento y la lista de IDs de voluntarios no son nulos
    //     if (requeriment == null || volunteerIds == null) {
    //         throw new BussinesRuleException("1050", "Requeriment or Volunteer IDs cannot be null", HttpStatus.PRECONDITION_FAILED);
    //     }

    //     // Verificar si la ocupación del voluntario coincide con la del requerimiento
    //     for (Long volunteerId : volunteerIds) {
    //         VolunteerH volunteer = repositoryVolunteerH.findById(volunteerId)
    //                 .orElseThrow(() -> new BussinesRuleException("1051", "Volunteer not found", HttpStatus.PRECONDITION_FAILED));
            
    //         if (!volunteer.getOccupation().equals(requeriment.getOccupation())) {
    //             throw new BussinesRuleException("1052", "Volunteer's occupation does not match the requeriment", HttpStatus.PRECONDITION_FAILED);
    //         }
    //     }

    //     // Asignar voluntarios al requerimiento
    //     List<VolunteerH> volunteers = (List<VolunteerH>) repositoryVolunteerH.findAllById(volunteerIds);
    //     requeriment.setVolunteers(volunteers);

    //     // Guardar el requerimiento en la base de datos
    //     return repositoryRequerimentH.save(requeriment);
    // }

    
    
}
