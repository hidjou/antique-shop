/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.dao;

import com.antique.model.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hidjou
 */
@Stateless
public class ProductDao implements ProductDaoLocal {

    @PersistenceContext
    private EntityManager em;
    
    
    @Override
    public void addProduct(Product product) {
        em.persist(product);
    }

    @Override
    public void editProduct(Product product) {
        em.merge(product);
    }

    @Override
    public void deleteProduct(int productId) {
        em.remove(getProduct(productId));
    }

    @Override
    public Product getProduct(int productId) {
        return em.find(Product.class, productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return em.createNamedQuery("Product.findAll").getResultList();
    }
    
}

