<%-- 
    Document   : checkoutCart
    Created on : 10-Dec-2017, 19:05:37
    Author     : Hidjou
--%>

<%@page session="true" import="java.util.*, com.antique.model.CartProduct" %>
<%
    ArrayList list = (ArrayList)session.getAttribute("shoppingcart");
    if(list != null && (list.size() > 0)){
%>
<center>
    <h2>Shopping cart</h2>
    <hr>
    <table class="table-fill">
        <tr>
            <td>Product</td>
            <td>Price</td>
            <td>Quantity</td>
        </tr>
        <%
            for(int i = 0; i < list.size(); i++){
               CartProduct prod = (CartProduct)list.get(i);
        %>
        <tr>
            <td><%= prod.getProduct().getName() %></td>
            <td><%= prod.getProduct().getPrice() %></td>
            <td><%= prod.getQuantity() %></td>
        </tr>
        <% } %>
        <tr>
            <td>Total amount</td>
            <td>£${totalAmount}</td>
        </tr>
    </table>
    <br/>
    <form action="ProductServlet" method="POST">
        <input type="hidden" name="action" value="EditCart"/>
        <button type="submit" name="checkout" value="Checkout" class="btn btn-info">Edit cart</button>
    </form>
</center>
<% } %>