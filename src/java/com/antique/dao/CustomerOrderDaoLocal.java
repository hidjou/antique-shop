/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.dao;

import com.antique.model.CustomerOrder;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hidjou
 */
@Local
public interface CustomerOrderDaoLocal {

    void addCustomerOrder(CustomerOrder customerOrder);

    CustomerOrder getCustomerOrder(Integer customerOrderId);

    List<CustomerOrder> getAllCustomerOrders();

    CustomerOrder getCustomerOrderByNo(long orderNo);
    
}
