package com.campus.ong.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.campus.ong.dto.ShippingDTO;
import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.Shipping;
import com.campus.ong.services.ServiceShipping;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Shipping_Controller", description = "Methods availables for Shippings")
@RequestMapping("/shippings/")
@AllArgsConstructor
public class ShippingController {

    private ServiceShipping serviceShipping;

    @Operation(summary = "Get a List with Shippings information")
    @GetMapping("/")
    @JsonView(ShippingController.class)
    public ResponseEntity<List<ShippingDTO>> getAllShippings() {
        List<ShippingDTO> shippings = serviceShipping.findAll();
        return new ResponseEntity<>(shippings, HttpStatus.OK);
    }

    @Operation(summary = "Get a Shipping by its ID")
    @GetMapping("/{id}")
    @JsonView(ShippingController.class)
    public ResponseEntity<Map<String,Object>> getShippingById(@PathVariable Long id) throws BussinesRuleException {
        Map<String,Object> response = new HashMap<>();
        ShippingDTO shipping = serviceShipping.findById(id);
        response.put("shipping", shipping);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get a List with Shippings with a given finished status")
    @GetMapping("/finished/{state}")
    @JsonView(ShippingController.class)
    public ResponseEntity<Map<String,Object>> getShippingsByFinished(@PathVariable Boolean state) throws BussinesRuleException {
        Map<String,Object> response = new HashMap<>();
        List<ShippingDTO> shippings = serviceShipping.findByFinished(state);
        response.put("shipping", shippings);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get a List with Shippings organised by a given campus")
    @GetMapping("/campus/{campusId}")
    @JsonView(ShippingController.class)
    public ResponseEntity<Map<String,Object>> getShippingsByCampusId(@PathVariable Long campusId) throws BussinesRuleException {
        Map<String,Object> response = new HashMap<>();
        List<ShippingDTO> shippings = serviceShipping.findByCampusesId(campusId);
        response.put("shipping", shippings);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create a new Shipping")
    @PostMapping
    @JsonView(ShippingController.class)
    public ResponseEntity<Map<String, Object>> createShipping(@RequestBody ShippingDTO shipping, BindingResult result) throws BussinesRuleException {
        Shipping shippingNew = null;

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
            shippingNew = serviceShipping.save(shipping);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database ShippingDTO");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Shipping has been successfully created");
        response.put("shipping", shippingNew);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @Operation(summary = "Delete a Shipping by its ID")
    @DeleteMapping("/{id}")
    @JsonView(ShippingController.class)
    public ResponseEntity<Void> deleteShipping(@PathVariable Long id) {
        serviceShipping.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Change State of a Shipping to finished")
    @PutMapping("/{shippingId}/finish")
    public ResponseEntity<?> finishShipping(@PathVariable Long shippingId) {
        return null;
        
    }
}