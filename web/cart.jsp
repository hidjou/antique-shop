<%-- 
    Document   : cart
    Created on : 10-Dec-2017, 01:45:20
    Author     : Hidjou
--%>

<%@page session="true" import="java.util.*, com.antique.model.CartProduct" %>
<%
    ArrayList list = (ArrayList)session.getAttribute("shoppingcart");
    if(list != null && (list.size() > 0)){
%>
<h1>Your shopping cart</h1>
<hr>
<center>
    <table class="table-fill">
        <tr>
            <td>Product</td>
            <td>Price</td>
            <td>Quantity</td>
            <td>Actions</td>
        </tr>
        <%
            for(int i = 0; i < list.size(); i++){
               CartProduct prod = (CartProduct)list.get(i);
        %>
        <tr>
            <td><%= prod.getProduct().getName() %></td>
            <td><%= prod.getProduct().getPrice() %></td>
            <td><%= prod.getQuantity() %></td>
            <td>
                <form action="ProductServlet" method="POST">
                    <input type="hidden" name="action" value="RemoveFromCart"/>
                    <button type="submit" name="deleteIndex" value="<%= i %>" class="btn btn-danger">Delete</button>
                </form>
            </td>
        </tr>
        <% } %>
        <tr>
            <td>Total amount</td>
            <td>£${totalAmount}</td>
        </tr>
    </table>
    <br/>
    <form action="ProductServlet" method="POST">
        <input type="hidden" name="action" value="Checkout">
        <button type="submit" name="checkout" value="Checkout" class="btn btn-success">Checkout</button>
    </form>
</center>
<% } %>