/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.dao;

import com.antique.model.CustomerOrder;
import com.antique.model.OrderProduct;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hidjou
 */
@Stateless
public class OrderProductDao implements OrderProductDaoLocal {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void addOrderProduct(OrderProduct orderProduct) {
        em.persist(orderProduct);
    }

    @Override
    public OrderProduct getOrderProduct(Integer orderProductId) {
        return em.find(OrderProduct.class, orderProductId);
    }

    @Override
    public List<OrderProduct> getAllOrderProducts() {
        return em.createNamedQuery("OrderProduct.findAll").getResultList();
    }

    @Override
    public List<OrderProduct> getOrderProductsOfOrder(CustomerOrder orderId) {
        return em.createNamedQuery("OrderProduct.findByOrderId").setParameter("orderId", orderId).getResultList();
    }

    
    
}
