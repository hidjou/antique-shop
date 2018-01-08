/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.dao;

import com.antique.model.Product;
import javax.ejb.Local;
import java.util.List;

/**
 *
 * @author Hidjou
 */
@Local
public interface ProductDaoLocal {

    void addProduct(Product product);

    void editProduct(Product product);

    void deleteProduct(int productId);

    Product getProduct(int productId);

    List<Product> getAllProducts();

}
