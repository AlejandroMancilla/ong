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

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.Occupation;
import com.campus.ong.services.ServiceOccupation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/occupations/")
@AllArgsConstructor
public class OccupationController {
    
    private ServiceOccupation serviceOccupation;

    @GetMapping("/")
    public ResponseEntity<List<Occupation>> findAll() {
        List<Occupation> findAll = serviceOccupation.findAll();
        if(findAll == null || findAll.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(findAll);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id)throws BussinesRuleException{
         Map<String,Object> response = new HashMap<>();
         Occupation occupation = serviceOccupation.findById(id);
         response.put("occupation", occupation);
         return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Occupation occupation, BindingResult result) {

        Occupation occupationNew = null;

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
            occupationNew = serviceOccupation.save(occupation);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Occupation has been successfully created");
        response.put("occupation", occupationNew);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Occupation occupation, BindingResult result,
            @PathVariable Long id) {

        Occupation occupationUpdate = null;

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

            occupationUpdate = serviceOccupation.update(id, occupation);

        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "Occupation has been successfully created");
        response.put("cliente", occupationUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            serviceOccupation.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Occupation has been successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}