/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hidjou
 */
@Entity
@Table(name = "customerOrders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerOrder.findAll", query = "SELECT c FROM CustomerOrder c")
    , @NamedQuery(name = "CustomerOrder.findByOrderId", query = "SELECT c FROM CustomerOrder c WHERE c.orderId = :orderId")
    , @NamedQuery(name = "CustomerOrder.findByTime", query = "SELECT c FROM CustomerOrder c WHERE c.time = :time")
    , @NamedQuery(name = "CustomerOrder.findByTotal", query = "SELECT c FROM CustomerOrder c WHERE c.total = :total")
    , @NamedQuery(name = "CustomerOrder.findByFirstName", query = "SELECT c FROM CustomerOrder c WHERE c.firstName = :firstName")
    , @NamedQuery(name = "CustomerOrder.findByLastName", query = "SELECT c FROM CustomerOrder c WHERE c.lastName = :lastName")
    , @NamedQuery(name = "CustomerOrder.findByAddress", query = "SELECT c FROM CustomerOrder c WHERE c.address = :address")
    , @NamedQuery(name = "CustomerOrder.findByCity", query = "SELECT c FROM CustomerOrder c WHERE c.city = :city")
    , @NamedQuery(name = "CustomerOrder.findByPostCode", query = "SELECT c FROM CustomerOrder c WHERE c.postCode = :postCode")
    , @NamedQuery(name = "CustomerOrder.findByCountry", query = "SELECT c FROM CustomerOrder c WHERE c.country = :country")
    , @NamedQuery(name = "CustomerOrder.findByPhone", query = "SELECT c FROM CustomerOrder c WHERE c.phone = :phone")
    , @NamedQuery(name = "CustomerOrder.findByEmail", query = "SELECT c FROM CustomerOrder c WHERE c.email = :email")
    , @NamedQuery(name = "CustomerOrder.findByShipAddress", query = "SELECT c FROM CustomerOrder c WHERE c.shipAddress = :shipAddress")
    , @NamedQuery(name = "CustomerOrder.findByShipCity", query = "SELECT c FROM CustomerOrder c WHERE c.shipCity = :shipCity")
    , @NamedQuery(name = "CustomerOrder.findByShipPostCode", query = "SELECT c FROM CustomerOrder c WHERE c.shipPostCode = :shipPostCode")
    , @NamedQuery(name = "CustomerOrder.findByShipCountry", query = "SELECT c FROM CustomerOrder c WHERE c.shipCountry = :shipCountry")
    , @NamedQuery(name = "CustomerOrder.findByCardNo", query = "SELECT c FROM CustomerOrder c WHERE c.cardNo = :cardNo")
    , @NamedQuery(name = "CustomerOrder.findByCardExpiry", query = "SELECT c FROM CustomerOrder c WHERE c.cardExpiry = :cardExpiry")
    , @NamedQuery(name = "CustomerOrder.findByCardType", query = "SELECT c FROM CustomerOrder c WHERE c.cardType = :cardType")
    , @NamedQuery(name = "CustomerOrder.findByCardCVC", query = "SELECT c FROM CustomerOrder c WHERE c.cardCVC = :cardCVC")
    , @NamedQuery(name = "CustomerOrder.findByOrderNo", query = "SELECT c FROM CustomerOrder c WHERE c.orderNo = :orderNo")})
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orderId")
    private Integer orderId;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private BigDecimal total;
    @Size(max = 255)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 255)
    @Column(name = "lastName")
    private String lastName;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Size(max = 255)
    @Column(name = "city")
    private String city;
    @Size(max = 255)
    @Column(name = "postCode")
    private String postCode;
    @Size(max = 255)
    @Column(name = "country")
    private String country;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "shipAddress")
    private String shipAddress;
    @Size(max = 255)
    @Column(name = "shipCity")
    private String shipCity;
    @Size(max = 255)
    @Column(name = "shipPostCode")
    private String shipPostCode;
    @Size(max = 255)
    @Column(name = "shipCountry")
    private String shipCountry;
    @Size(max = 255)
    @Column(name = "cardNo")
    private String cardNo;
    @Size(max = 255)
    @Column(name = "cardExpiry")
    private String cardExpiry;
    @Size(max = 255)
    @Column(name = "cardType")
    private String cardType;
    @Size(max = 255)
    @Column(name = "cardCVC")
    private String cardCVC;
    @Column(name = "orderNo")
    private Integer orderNo;

    public CustomerOrder() {
    }

    public CustomerOrder(Integer orderId, Date time, BigDecimal total, String firstName, String lastName, String address, String city, String postCode, String country, String phone, String email, String shipAddress, String shipCity, String shipPostCode, String shipCountry, String cardNo, String cardExpiry, String cardType, String cardCVC, Integer orderNo) {
        this.orderId = orderId;
        this.time = time;
        this.total = total;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipPostCode = shipPostCode;
        this.shipCountry = shipCountry;
        this.cardNo = cardNo;
        this.cardExpiry = cardExpiry;
        this.cardType = cardType;
        this.cardCVC = cardCVC;
        this.orderNo = orderNo;
    }
    
    

    public CustomerOrder(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipPostCode() {
        return shipPostCode;
    }

    public void setShipPostCode(String shipPostCode) {
        this.shipPostCode = shipPostCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardCVC() {
        return cardCVC;
    }

    public void setCardCVC(String cardCVC) {
        this.cardCVC = cardCVC;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrder)) {
            return false;
        }
        CustomerOrder other = (CustomerOrder) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.antique.model.CustomerOrder[ orderId=" + orderId + " ]";
    }
    
}
