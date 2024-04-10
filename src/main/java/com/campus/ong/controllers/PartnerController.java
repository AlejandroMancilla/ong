package com.campus.ong.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.ong.dto.PartnerDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.services.ServicePartner;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@Tag(name = "Partner_Controller", description = "Methods availables for Partners")
@RequestMapping("/partners/")
@AllArgsConstructor
public class PartnerController {
    
    private ServicePartner servicePartner;

    @Operation(summary = "Get a List with Partners information")
    @GetMapping("/")
    @JsonView(PartnerController.class)
    public ResponseEntity<List<PartnerDTO>> findAll() {
        List<PartnerDTO> findAll = servicePartner.findAll();
        if(findAll == null || findAll.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(findAll);
        }
    }

    @Operation(summary = "Get a List with Partners associated in a given campus")
    @GetMapping("/campus/{campusId}")
    @JsonView(PartnerController.class)
    public ResponseEntity<Map<String,Object>> getPartnerByCampusId(@PathVariable Long campusId) throws BussinesRuleException {
        Map<String,Object> response = new HashMap<>();
        List<PartnerDTO> partners = servicePartner.findByCampusId(campusId);
        response.put("partner", partners);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get a List with Partners with a given quota type")
    @GetMapping("/quota/{quotaId}")
    @JsonView(PartnerController.class)
    public ResponseEntity<Map<String,Object>> getPartnerByQuotaTypeId(@PathVariable Long quotaId) throws BussinesRuleException {
        Map<String,Object> response = new HashMap<>();
        List<PartnerDTO> partners = servicePartner.findByQuotaTypeId(quotaId);
        response.put("partner", partners);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get a Partner by its ID")
    @GetMapping("/{id}")
    @JsonView(PartnerController.class)
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id)throws BussinesRuleException{
         Map<String,Object> response = new HashMap<>();
         PartnerDTO partner = servicePartner.findById(id);
         response.put("partner", partner);
         return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create a new Partner")
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody PartnerDTO partner, BindingResult result){
        PartnerDTO partnerNew = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "Field " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            partnerNew = servicePartner.save(partner);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database PartnerDTO");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Partner has been successfully created");
        response.put("partner", partnerNew);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a Partner by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            servicePartner.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Partner has been successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
