/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.model;

/**
 *
 * @author Hidjou
 */
public class CartProduct {
    private Product product;
    private String name;
    private int quantity;
    
    public Product getProduct(){
        return product;
    }
    public void setProduct(Product product){
        this.product = product;
    }
    
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int qty){
        this.quantity = qty;
    }
    public void incrementQuantity(){
        this.quantity = quantity + 1;
    }
    
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
