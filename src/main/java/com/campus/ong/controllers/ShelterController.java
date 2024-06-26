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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.ong.dto.ShelterDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.services.ServiceShelter;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@Tag(name = "Shelter_Controller", description = "Methods availables for Shelters")
@RequestMapping("/shelters/")
@AllArgsConstructor
public class ShelterController {

    private ServiceShelter serviceShelter;

    @Operation(summary = "Get a List with Shelters information")
    @GetMapping
    @JsonView(ShelterController.class)
    public ResponseEntity<List<ShelterDTO>> findAll() {
        List<ShelterDTO> findAll = serviceShelter.findAll();
        if(findAll == null || findAll.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(findAll);
        }
    }

    @Operation(summary = "Get a Shelter by its ID")
    @GetMapping("/{id}")
    @JsonView(ShelterController.class)
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id) throws BussinesRuleException {
        Map<String,Object> response = new HashMap<>();
        ShelterDTO shelter = serviceShelter.findById(id);
        response.put("shelter", shelter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create a new Shelter")
    @PostMapping
    @JsonView(ShelterController.class)
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody ShelterDTO shelter, BindingResult result){
        ShelterDTO shelterNew = null;

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
            shelterNew = serviceShelter.save(shelter);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Shelter has been successfully created");
        response.put("shelter", shelterNew);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update the Shelter information by its ID")
    @PutMapping("/{id}")
    @JsonView(ShelterController.class)
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody ShelterDTO shelter, BindingResult result,
    @PathVariable Long id) {
       
        ShelterDTO shelterUpdate = null;

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

            shelterUpdate = serviceShelter.update(id, shelter);

        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "Shelter has been successfully created");
        response.put("shelter", shelterUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a Shelter by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            serviceShelter.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Shelter has been successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
