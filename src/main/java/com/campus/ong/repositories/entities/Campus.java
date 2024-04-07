package com.campus.ong.repositories.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "campuses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campus implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Can't be Empty")
    @Column(nullable = false)
    private String address;

    @JsonIgnore
    @JoinColumn(name = "id_city")
    @OneToOne(fetch = FetchType.LAZY)
    private City city;

    @JsonIgnoreProperties(value={"campuses", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @JoinColumn(name = "id_director", unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    private UserE director;

    @JsonIgnore
    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partner> partners;

    @JsonIgnore
    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Volunteer> volunteers;

    @JsonIgnore
    @ManyToMany(mappedBy = "campuses", cascade = CascadeType.ALL)
    private List<Shipping> shippings;

}
