/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OULADHAH
 */
@Entity
@Table(name = "orderProducts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderProduct.findAll", query = "SELECT o FROM OrderProduct o")
    , @NamedQuery(name = "OrderProduct.findByOrderProductId", query = "SELECT o FROM OrderProduct o WHERE o.orderProductId = :orderProductId")
    , @NamedQuery(name = "OrderProduct.findByQuantity", query = "SELECT o FROM OrderProduct o WHERE o.quantity = :quantity")
    , @NamedQuery(name = "OrderProduct.findByOrderId", query = "SELECT o FROM OrderProduct o WHERE o.orderId = :orderId")})
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orderProductId")
    private Integer orderProductId;
    @Column(name = "quantity")
    private Integer quantity;
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    @ManyToOne
    private CustomerOrder orderId;
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    @ManyToOne
    private Product productId;

    public OrderProduct() {
    }

    public OrderProduct(Integer orderProductId, Integer quantity, CustomerOrder orderId, Product productId) {
        this.orderProductId = orderProductId;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }
    
    

    public OrderProduct(Integer orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Integer getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Integer orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CustomerOrder getOrderId() {
        return orderId;
    }

    public void setOrderId(CustomerOrder orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderProductId != null ? orderProductId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderProduct)) {
            return false;
        }
        OrderProduct other = (OrderProduct) object;
        if ((this.orderProductId == null && other.orderProductId != null) || (this.orderProductId != null && !this.orderProductId.equals(other.orderProductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.antique.model.OrderProduct[ orderProductId=" + orderProductId + " ]";
    }
    
}
