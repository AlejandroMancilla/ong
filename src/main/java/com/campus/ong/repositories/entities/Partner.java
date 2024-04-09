package com.campus.ong.repositories.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "partners")
public class Partner implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="paymeent_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date  paymeentAt;
    
    @NotEmpty(message = "Number Account can't be Empty")
    @Column(nullable = false)
    private String numAccount;

    @ManyToOne()
    @JoinColumn(name = "id_quota_type")
    private QuotaType quotaType;

    @JoinColumn(name = "id_user")
    @OneToOne(fetch = FetchType.LAZY)
    private UserE user;

    @ManyToOne()
    @JoinColumn(name = "id_campus")
    private Campus campus;
}
