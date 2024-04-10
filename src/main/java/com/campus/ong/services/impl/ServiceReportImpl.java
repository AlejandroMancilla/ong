package com.campus.ong.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campus.ong.dto.CampusDTO;
import com.campus.ong.dto.MaterialDTO;
import com.campus.ong.dto.PartnerDTO;
import com.campus.ong.dto.VolunteerDTO;
import com.campus.ong.repositories.entities.RequerimentH;
import com.campus.ong.services.ServiceCampus;
import com.campus.ong.services.ServiceMaterialAid;
import com.campus.ong.services.ServicePartner;
import com.campus.ong.services.ServiceRequerimentH;
import com.campus.ong.services.ServiceVolunteer;
import com.campus.ong.services.ServiceReport;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceReportImpl implements ServiceReport {

    private final ServicePartner partnerService;
    private final ServiceCampus campusService;
    private final ServiceMaterialAid materialAidService;
    private final ServiceRequerimentH requirementHService;
    private final ServiceVolunteer volunteerHService;

    @Override
    public List<PartnerDTO> getPartners() {
        return partnerService.findAll();
    }

    @Override
    public List<CampusDTO> getCampuses() {
        return campusService.findAll();
    }

    @Override
    public List<MaterialDTO> getMaterials() {
        return materialAidService.getAllMaterialAid();
    }

    @Override
    public List<RequerimentH> getRequirements() {
        return requirementHService.findAll();
    }

    @Override
    public List<VolunteerDTO> getVolunteers() {
        return volunteerHService.findAll();
    }

}
