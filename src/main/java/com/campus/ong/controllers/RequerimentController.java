package com.campus.ong.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.ong.dto.RequerimentHDTO;
import com.campus.ong.repositories.entities.RequerimentH;
import com.campus.ong.services.ServiceRequerimentH;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/requeriments/")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequerimentController {

    @Autowired
    private ServiceRequerimentH serviceRequerimentH;

    @Operation(summary = "Get a List with Requeriments Humanitarians information")
    @GetMapping("/")
    public ResponseEntity<List<RequerimentH>> findAll() {
        List<RequerimentH> findAll = serviceRequerimentH.findAll();
        if(findAll == null || findAll.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(findAll);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createRequerimentWithVolunteers(@RequestBody RequerimentHDTO requerimentWithVolunteersDTO) {
        return null;
    }
}