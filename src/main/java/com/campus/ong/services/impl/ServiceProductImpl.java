package com.campus.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.ong.exception.BussinesRuleException;
import com.campus.ong.repositories.RepositoryProduct;
import com.campus.ong.repositories.entities.Product;
import com.campus.ong.repositories.entities.ProductType;
import com.campus.ong.services.ServiceProduct;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceProductImpl implements ServiceProduct {

    private RepositoryProduct repositoryProduct;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) repositoryProduct.findAll();
    }

    @Override
    public Product save(Product product) {
        return repositoryProduct.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Optional<Product> productCurrentOptional = repositoryProduct.findById(id);

        if(productCurrentOptional.isPresent()){
            Product productCurrent = productCurrentOptional.get();
            productCurrent.setName(product.getName());
            repositoryProduct.save(productCurrent);
            return productCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Product> productOptional=repositoryProduct.findById(id);
        if(productOptional.isPresent()){
            repositoryProduct.delete(productOptional.get());
        }   
    }

    @Override
    public Product findById(Long id) throws BussinesRuleException {
        Optional<Product> productOptional = repositoryProduct.findById(id);
        if(!productOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1003","Error! Product doesn't exist", HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return productOptional.get();
    }

    public List<Product> findByType(ProductType type) {
        return repositoryProduct.findByType(type);
    }
   
}
