package com.campus.ong.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.campus.ong.repositories.entities.Product;
import com.campus.ong.repositories.entities.ProductType;

public interface RepositoryProduct extends CrudRepository<Product, Long> {
    
    List<Product> findByType(ProductType type);
    
}
