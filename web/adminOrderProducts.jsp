<%-- 
    Document   : adminOrderProducts
    Created on : 12-Dec-2017, 15:40:59
    Author     : Hidjou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tag:admin>
    <h1>Billing</h1>
    <hr>
    <table class="table-fill">
        <th>Name</th>
        <th>Address</th>
        <th>Country</th>
        <th>Phone No</th>
        <th>Email</th>
        
        <tr>
            <td>${order.firstName} ${order.lastName}</td>
            <td>${order.address}, ${order.city}, ${order.postCode}</td>
            <td>${order.country}</td>
            <td>${order.phone}</td>
            <td>${order.email}</td>
        </tr>
    </table>
        
    <h1>Shipping</h1>
    <hr>
    <table class="table-fill">
        <th>Address</th>
        <th>City</th>
        <th>Post Code</th>
        <th>Country</th>
        
        <tr>
            <td>${order.shipAddress}</td>
            <td>${order.shipCity}</td>
            <td>${order.shipPostCode}</td>
            <td>${order.shipCountry}</td>
        </tr>
    </table>
        
    <h1>Payment information</h1>
    <hr>
    <table class="table-fill">
        <th>Card number</th>
        <th>Expiry date</th>
        <th>Card type</th>
        <th>CVC</th>
        
        <tr>
            <td>${order.cardNo}</td>
            <td>${order.cardExpiry}</td>
            <td>${order.cardType}</td>
            <td>${order.cardCVC}</td>
        </tr>
    </table>
        
    <h1>Order details</h1>
    <hr>
    <table class="table-fill">
        <th>Product Id</th>
        <th>Name</th>
        <th>Quantity</th>
        
        <c:forEach items="${orderProducts}" var="ordProd">
            <tr>
                <td>${ordProd.productId.productId}</td>
                <td>${ordProd.productId.name}</td>
                <td>${ordProd.quantity}</td>
            </tr>
        </c:forEach>
            
            <tr>
                <td>Total</td>
                <td>${order.total}</td>
            </tr>
    </table>
</tag:admin>