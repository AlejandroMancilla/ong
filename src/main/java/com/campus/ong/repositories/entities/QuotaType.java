package com.campus.ong.repositories.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="quota_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotaType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Can't be Empty")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "Can't be Empty")
    @Column(nullable = false)
    private Float price;

    @OneToMany(mappedBy = "quotaType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partner> partners;

}
