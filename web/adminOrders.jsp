<%-- 
    Document   : adminOrders
    Created on : 11-Dec-2017, 01:54:46
    Author     : Hidjou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tag:admin>
    <h1>Orders</h1>
    <hr>
    <table class="table-fill">
        <th>Id</th>
        <th>Date</th>
        <th>Name</th>
        <th>Total</th>
        <th>Actions</th>
        
        <form action="AdminServlet" method="POST">
            <c:forEach items="${allOrders}" var="order">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.time}</td>
                    <td>${order.firstName} ${order.lastName}</td>
                    <td>${order.total}</td>
                    <td>
                        <input type="hidden" name="action" value="orderDetails"/>
                        <button type="submit" name="orderDetails" value="${order.orderId}" class="btn btn-info">Details</button>
                    </td>
                </tr>
            </c:forEach>
        </form>
    </table>
</tag:admin>