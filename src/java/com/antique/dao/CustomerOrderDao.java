/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.dao;

import com.antique.model.CustomerOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hidjou
 */
@Stateless
public class CustomerOrderDao implements CustomerOrderDaoLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addCustomerOrder(CustomerOrder customerOrder) {
        em.persist(customerOrder);
    }

    @Override
    public CustomerOrder getCustomerOrder(Integer customerOrderId) {
        return em.find(CustomerOrder.class, customerOrderId);
    }

    @Override
    public List<CustomerOrder> getAllCustomerOrders() {
        return em.createNamedQuery("CustomerOrder.findAll").getResultList();
    }

    @Override
    public CustomerOrder getCustomerOrderByNo(long orderNo) {
        return (CustomerOrder)em.createNamedQuery("SELECT c FROM CustomerOrder c WHERE C.orderNo = :orderNo").setParameter("orderNo", orderNo).getResultList().get(0);
    }
    
    
}
