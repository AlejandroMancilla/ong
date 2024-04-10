package com.campus.ong.repositories.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "requeriments_h")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequerimentH implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_shipping")
    private Shipping shipping;

    @ManyToOne()
    @JoinColumn(name = "id_occupation")
    private Occupation occupation;

    @NotEmpty(message = "Amount can't be Empty")
    @Column(nullable = false)
    private Float amount;

    @ManyToMany
    @JoinTable(
        name = "requeriment_volunteer", 
        joinColumns = @JoinColumn(name = "id_requeriment"), 
        inverseJoinColumns = @JoinColumn(name = "id_volunteerh"))
    private List<VolunteerH> volunteers;


}
