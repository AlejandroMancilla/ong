package com.campus.ong.repositories.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shippings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipping implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="starts_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date  startsAt;

    @NotEmpty(message = "State can't be Empty")
    @Column(nullable = false)
    private boolean finished;

    @ManyToMany
    @JoinTable(
        name = "shipping_campus", 
        joinColumns = @JoinColumn(name = "id_shipping"), 
        inverseJoinColumns = @JoinColumn(name = "id_campus"))
    private List<Campus> campuses;

    @ManyToOne()
    @JoinColumn(name = "id_shelter")
    private Shelter shelter;

    @OneToMany(mappedBy = "shipping", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequerimentH> requeriments;

    @OneToMany(mappedBy = "shipping", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialAid> materialAids;

}
