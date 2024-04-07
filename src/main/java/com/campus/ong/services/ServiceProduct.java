package com.campus.ong.services;

import java.util.List;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.entities.Product;
import com.campus.ong.repositories.entities.ProductType;

public interface ServiceProduct {
    
    List<Product> findAll();

    Product findById(Long id) throws BussinesRuleException;

    Product save(Product Product);

    Product update(Long id, Product Product);

    void delete(Long id);

    List<Product> findByType(ProductType type);

}
