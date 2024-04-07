package com.campus.ong.repositories.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "volunteers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volunteer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties(value={"volunteers", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @JoinColumn(name = "id_user")
    @OneToOne(fetch = FetchType.LAZY)
    private UserE user;

    @JsonIgnoreProperties(value={"volunteers", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @ManyToOne()
    @JoinColumn(name = "id_campus")
    private Campus campus;

    @JsonIgnore
    @OneToOne(mappedBy = "volunteer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VolunteerH volunteer;
}
