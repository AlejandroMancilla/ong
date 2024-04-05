package com.campus.ong.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "material_aids")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialAid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_shipping")
    private Shipping shipping;

    @ManyToOne()
    @JoinColumn(name = "id_product")
    private Product product;

    @NotEmpty(message = "Amount can't be Empty")
    @Column(nullable = false)
    private Float amount;


}
