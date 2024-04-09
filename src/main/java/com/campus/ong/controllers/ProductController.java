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
import com.campus.ong.repositories.entities.Product;
import com.campus.ong.repositories.entities.ProductType;
import com.campus.ong.services.ServiceProduct;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@Tag(name = "Product_Controller", description = "Methods availables for Products")
@RequestMapping("/products/")
@AllArgsConstructor
public class ProductController {

    private ServiceProduct serviceProduct;
    
    @Operation(summary = "Get a List with Products information")
    @GetMapping("/")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> findAll = serviceProduct.findAll();
        if(findAll == null || findAll.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(findAll);
        }
    }

    @Operation(summary = "Get a Product by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id)throws BussinesRuleException{
         Map<String,Object> response = new HashMap<>();
         Product product = serviceProduct.findById(id);
         response.put("product", product);
         return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/byType/{type}")
    public ResponseEntity<List<Product>> findByType(@PathVariable ProductType type) {
        List<Product> products = serviceProduct.findByType(type);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Create a new Product")
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Product product, BindingResult result) {

        Product productNew = null;

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
            productNew = serviceProduct.save(product);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Product has been successfully created");
        response.put("product", productNew);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update the Product information by its ID")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Product product, BindingResult result,
            @PathVariable Long id) {

        Product productUpdate = null;

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

            productUpdate = serviceProduct.update(id, product);

        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "Product has been successfully created");
        response.put("product", productUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a Product by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            serviceProduct.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Product has been successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }  
}
