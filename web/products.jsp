<%-- 
    Document   : products.jsp
    Created on : 07-Dec-2017, 20:22:13
    Author     : Hidjou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 

%>

<tag:layout>
    <jsp:include page="cart.jsp" flush="true" />
    <h1>Our products</h1>
    <hr>
        
    <br/>
    
    <table class="table-fill">
        <th>ID</th>
        <th>Product name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Description</th>
        <th>Picture</th>
        <th>Actions</th>
        
        <form action="ProductServlet" method="POST">
            <c:forEach items="${allProducts}" var="prod">
                <tr>
                    <td>${prod.productId}</td>
                    <td>${prod.name}</td>
                    <td>${prod.quantity}</td>
                    <td>${prod.price}</td>
                    <td>${prod.description}</td>
                    <td><img src="${prod.imageUrl}"/></td>
                    <td>
                        <input type="hidden" name="action" value="AddToCart"/>
                        <button type="submit" name="productAdded" value="${prod.productId}" class="btn btn-info">Add to cart</button>
                    </td>
                </tr>
            </c:forEach>
        </form>
        
    </table>
    
</tag:layout>