/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.dao;

import com.antique.model.CustomerOrder;
import com.antique.model.OrderProduct;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hidjou
 */
@Local
public interface OrderProductDaoLocal {

    void addOrderProduct(OrderProduct orderProduct);

    OrderProduct getOrderProduct(Integer orderProductId);

    List<OrderProduct> getAllOrderProducts();

    List<OrderProduct> getOrderProductsOfOrder(CustomerOrder orderId);
    
}
