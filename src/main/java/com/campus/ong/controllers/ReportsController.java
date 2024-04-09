package com.campus.ong.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.ong.repositories.entities.Campus;
import com.campus.ong.repositories.entities.Shelter;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

@RestController
@Tag(name = "Reports_Controller", description = "Reports available through the API")
@RequestMapping("/reports/")
@AllArgsConstructor
public class ReportsController {
    
    private EntityManager entityManager;

    @GetMapping("/campuses-by-city/{cityId}")
    public List<Shelter> getCampusesByCity(@PathVariable String cityId) {
        String jpql = "SELECT c FROM Shelter c WHERE c.city.name = :cityId";
        TypedQuery<Shelter> query = entityManager.createQuery(jpql, Shelter.class);
        query.setParameter("cityId", cityId);
        return query.getResultList();
    }
}
