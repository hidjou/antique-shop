<%-- 
    Document   : adminProducts
    Created on : 11-Dec-2017, 01:51:04
    Author     : Hidjou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tag:admin>
    <h1>Products</h1>
    <hr>
    <form action="AdminServlet" method="POST">
        <div class="row">
            <div class="col col-md-2">
                <label>Product ID:</label>
            </div>
            <div class="col col-md-10">
                <input style="margin-top: 0; width:80%;" type="text" name="productId" value="${product.productId}"/>
            </div>
        </div>
        <div class="row">
            <div class="col col-md-2">
                <label>Product Name:</label>
            </div>
            <div class="col col-md-10">
                <input style="margin-top: 0; width:80%;" type="text" name="name" value="${product.name}"/>
            </div>
        </div>
        <div class="row">
            <div class="col col-md-2">
                <label>Quantity:</label>
            </div>
            <div class="col col-md-10">
                <input style="margin-top: 0; width:80%;" type="text" name="quantity" value="${product.quantity}"/>
            </div>
        </div>
        <div class="row">
            <div class="col col-md-2">
                <label>Price:</label>
            </div>
            <div class="col col-md-10">
                <input style="margin-top: 0; width:80%;" type="text" name="price" value="${product.price}"/>
            </div>
        </div>
        <div class="row">
            <div class="col col-md-2">
                <label>Description:</label>
            </div>
            <div class="col col-md-10">
                <input style="margin-top: 0; width:80%;" type="text" name="description" value="${product.description}"/>
            </div>
        </div>
        <div class="row">
            <div class="col col-md-2">
                <label>Image URL:</label>
            </div>
            <div class="col col-md-10">
                <input style="margin-top: 0; width:80%;" type="text" name="imageUrl" value="${product.imageUrl}"/>
            </div>
        </div>
        <br/>
        <center>
            <div>
                <button type="submit" name="action" value="Show" class="btn btn-info">Show all products</button>
                <button type="submit" name="action" value="Add" class="btn btn-success">Add product</button>
                <button type="submit" name="action" value="Edit" class="btn btn-warning">Edit product</button>
                <button type="submit" name="action" value="Delete" class="btn btn-danger">Delete product</button>
                <button type="submit" name="action" value="Search" class="btn btn-primary">Search product</button>
            </div>
        </center>
    </form>
        
    <br/>
    
    <table class="table-fill">
        <th>ID</th>
        <th>Product name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Description</th>
        <th>Picture</th>
        
        <c:forEach items="${allProducts}" var="prod">
            <tr>
                <td>${prod.productId}</td>
                <td>${prod.name}</td>
                <td>${prod.quantity}</td>
                <td>${prod.price}</td>
                <td>${prod.description}</td>
                <td><img src="${prod.imageUrl}"/></td>
            </tr>
        </c:forEach>
        
    </table>
    
</tag:admin>