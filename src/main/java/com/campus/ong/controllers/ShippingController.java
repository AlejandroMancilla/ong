package com.campus.ong.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.Shipping;
import com.campus.ong.services.ServiceShipping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@Tag(name = "Shipping_Controller", description = "Methods availables for Shippings")
@RequestMapping("/shippings/")
@AllArgsConstructor
public class ShippingController {

    private ServiceShipping serviceShipping;

    @Operation(summary = "Get a List with Shippings information")
    @GetMapping("/")
    public ResponseEntity<List<Shipping>> getAllShippings() {
        List<Shipping> shippings = serviceShipping.findAll();
        return new ResponseEntity<>(shippings, HttpStatus.OK);
    }

    @Operation(summary = "Get a Shipping by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Shipping> getShippingById(@PathVariable Long id) {
        try {
            Shipping shipping = serviceShipping.findById(id);
            return new ResponseEntity<>(shipping, HttpStatus.OK);
        } catch (BussinesRuleException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get a List with Shippings with a given finished status")
    @GetMapping("/finished/{state}")
    public ResponseEntity<List<Shipping>> getShippingsByFinished(@PathVariable Boolean state) {
        try {
            List<Shipping> shippings = serviceShipping.findByFinished(state);
            return new ResponseEntity<>(shippings, HttpStatus.OK);
        } catch (BussinesRuleException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get a List with Shippings organised by a given campus")
    @GetMapping("/campus/{campusId}")
    public ResponseEntity<List<Shipping>> getShippingsByCampusId(@PathVariable Long campusId) {
        try {
            List<Shipping> shippings = serviceShipping.findByCampusesId(campusId);
            return new ResponseEntity<>(shippings, HttpStatus.OK);
        } catch (BussinesRuleException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new Shipping")
    @PostMapping
    public ResponseEntity<Shipping> createShipping(@RequestBody Shipping shipping) {
        Shipping createdShipping = serviceShipping.save(shipping);
        return new ResponseEntity<>(createdShipping, HttpStatus.CREATED);
    }

    @Operation(summary = "Update the Shipping information by its ID")
    @PutMapping("/{id}")
    public ResponseEntity<Shipping> updateShipping(@PathVariable Long id, @RequestBody Shipping shipping) {
        Shipping updatedShipping = serviceShipping.update(id, shipping);
        if (updatedShipping != null) {
            return new ResponseEntity<>(updatedShipping, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a Shipping by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipping(@PathVariable Long id) {
        serviceShipping.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}