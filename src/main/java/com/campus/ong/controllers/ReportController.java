package com.campus.ong.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.ong.dto.PartnerDTO;
import com.campus.ong.dto.VolunteerDTO;
import com.campus.ong.services.ServiceReport;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@Tag(name = "Report_Controller", description = "Reports availables in API REST")
@RequestMapping("/reports/")
@AllArgsConstructor
public class ReportController {

    private final ServiceReport reportService;

    @Operation(summary = "Get the List of Partners in the API")
    @GetMapping("/partners")
    @JsonView(PartnerController.class)
    public List<PartnerDTO> getPartners() {
        return reportService.getPartners();
    }

    @Operation(summary = "Get the List of Campuses in the API")
    @GetMapping("/campuses")
    @JsonView(CampusController.class)
    public Object getCampuses() {
        return reportService.getCampuses();
    }

    @Operation(summary = "Get the List of Materials sended in Shippings")
    @GetMapping("/materials")
    @JsonView(ShippingController.class)
    public Object getMaterials() {
        return reportService.getMaterials();
    }

    @Operation(summary = "Get the List of Requirements solicited by Shippings")
    @GetMapping("/requirements")
    @JsonView(ShippingController.class)
    public Object getRequirements() {
        return reportService.getRequirements();
    }

    @Operation(summary = "Get the List of Volunteers")
    @GetMapping("/volunteers")
    @JsonView(ShippingController.class)
    public List<VolunteerDTO> getVolunteers() {
        return reportService.getVolunteers();
    }
}
