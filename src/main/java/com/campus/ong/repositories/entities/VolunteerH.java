package com.campus.ong.repositories.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "volunteers_h")
public class VolunteerH implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Availability Can't be Empty")
    @Column(nullable = false)
    private boolean available;

    @Column(nullable = false)
    private int num_missions;

    @JoinColumn(name = "id_occupation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Occupation occupation;

    @JoinColumn(name = "id_volunteer")
    @OneToOne(fetch = FetchType.LAZY)
    private Volunteer volunteer;

    @ManyToMany(mappedBy = "volunteers", cascade = CascadeType.ALL)
    private List<RequerimentH> requeriments_h;

}
