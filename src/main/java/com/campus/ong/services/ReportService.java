package com.campus.ong.services;

import java.util.List;

import com.campus.ong.dto.CampusDTO;
import com.campus.ong.dto.MaterialDTO;
import com.campus.ong.dto.PartnerDTO;
import com.campus.ong.dto.RequerimentVolunteersDTO;
import com.campus.ong.dto.VolunteerDTO;
import com.campus.ong.repositories.entities.RequerimentH;
import com.campus.ong.repositories.entities.Volunteer;


public interface ReportService {

    List<PartnerDTO> getPartners();

    List<CampusDTO> getCampuses();

    List<MaterialDTO> getMaterials();

    List<RequerimentH> getRequirements();

    List<Volunteer> getVolunteers();

}
