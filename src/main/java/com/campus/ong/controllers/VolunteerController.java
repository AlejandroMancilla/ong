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

import com.campus.ong.dto.VolunteerDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.services.ServiceVolunteer;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@Tag(name = "Volunteer_Controller", description = "Methods availables for Volunteers")
@RequestMapping("/volunteers/")
@AllArgsConstructor
public class VolunteerController {

    private ServiceVolunteer serviceVolunteer;
    
    @Operation(summary = "Get a List with Volunteers information")
    @GetMapping("/")
    @JsonView(ShippingController.class)
    public ResponseEntity<List<VolunteerDTO>> findAll() {
        List<VolunteerDTO> findAll = serviceVolunteer.findAll();
        if(findAll == null || findAll.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(findAll);
        }
    }

    @Operation(summary = "Get a Volunteer by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id)throws BussinesRuleException{
         Map<String,Object> response = new HashMap<>();
         VolunteerDTO volunteer = serviceVolunteer.findById(id);
         response.put("volunteer",  volunteer);
         return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create a new Volunteer")
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody VolunteerDTO volunteer, BindingResult result) {

        VolunteerDTO volunteerNew = null;

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
            volunteerNew = serviceVolunteer.save(volunteer);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Volunteer has been successfully created");
        response.put("volunteer", volunteerNew);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a Volunteer by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            serviceVolunteer.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Volunteer has been successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
