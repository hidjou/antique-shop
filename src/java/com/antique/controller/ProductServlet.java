/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.controller;

import com.antique.dao.CustomerOrderDaoLocal;
import com.antique.dao.OrderProductDaoLocal;
import com.antique.dao.ProductDaoLocal;
import com.antique.model.CartProduct;
import com.antique.model.CustomerOrder;
import com.antique.model.OrderProduct;
import com.antique.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hidjou
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {
    @EJB
    private ProductDaoLocal productDao;
    @EJB
    private CustomerOrderDaoLocal customerOrderDao;
    @EJB
    private OrderProductDaoLocal orderProductDao;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println(" Request Processing ... ");
        
        HttpSession session = request.getSession(true);
        if (session == null){
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
        
        // Get action parameter
        String action = request.getParameter("action");
        
        ArrayList shoppingCartList = (ArrayList)session.getAttribute("shoppingcart");
        
        // If "Add to cart" was clicked
        if("AddToCart".equalsIgnoreCase(action)){
            // Find product and edit its stock quantity in DB
            CartProduct cartProduct = getProduct(request);
            
            if(shoppingCartList == null){
                // If list is null, create list and add cart product
                shoppingCartList = new ArrayList();
                shoppingCartList.add(cartProduct);
            } else {
                // Get product Id and search of it in list
                boolean found = false;
                int productId = cartProduct.getProduct().getProductId();
                for(int i = 0; i < shoppingCartList.size(); i++){
                    // Get list item
                    int listProductId = ((CartProduct)shoppingCartList.get(i)).getProduct().getProductId();
                    CartProduct listCartProduct = (CartProduct)shoppingCartList.get(i);
                    if(productId == listProductId){
                        // If product is in list, increment its quantity
                        found = true;
                        listCartProduct.incrementQuantity();
                    }
                }
                if(!found){
                    // If product is not in list, add it to list
                    shoppingCartList.add(cartProduct);
                }
            }
            List<Product> products = productDao.getAllProducts();
            float total = calculateTotal(shoppingCartList);
            
            // Set sessino and request attribute
            request.setAttribute("totalAmount", String.valueOf(total));
            session.setAttribute("shoppingcart", shoppingCartList);
            request.setAttribute("allProducts", products);
            request.getRequestDispatcher("products.jsp").forward(request, response);
        } 
        
        else if("RemoveFromCart".equalsIgnoreCase(action)){
            // Get index, find item and remove it from list
            int index = Integer.parseInt(request.getParameter("deleteIndex"));
            CartProduct removedItem = (CartProduct)shoppingCartList.get(index);
            shoppingCartList.remove(index);
            
            // Find product and add back the quantity
            Product product = removedItem.getProduct();
            product.incrementQuantity();
            productDao.editProduct(product);
            
            session.setAttribute("shoppingcart", shoppingCartList);
            List<Product> products = productDao.getAllProducts();
            float total = calculateTotal(shoppingCartList);
            
            String page = request.getParameter("page");
            
            // Set sessino and request attribute
            request.setAttribute("totalAmount", String.valueOf(total));
            request.setAttribute("allProducts", products);
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
        
        else if("Checkout".equalsIgnoreCase(action)){
            float total = calculateTotal(shoppingCartList);
            request.setAttribute("totalAmount", String.valueOf(total));
            session.setAttribute("shoppingcart", shoppingCartList);
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }
        else if("EditCart".equalsIgnoreCase(action)){
            List<Product> products = productDao.getAllProducts();
            float total = calculateTotal(shoppingCartList);
            
            request.setAttribute("allProducts", products);
            request.setAttribute("totalAmount", String.valueOf(total));
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
        else if("makeOrder".equalsIgnoreCase(action)){
            // Create and persist customer order
            float total = calculateTotal(shoppingCartList);
            CustomerOrder placedOrder = createCustomerOrder(request, total);
            customerOrderDao.addCustomerOrder(placedOrder);
            
            //CustomerOrder persitedOrder = customerOrderDao.getCustomerOrderByNo(placedOrder.getOrderNo());
            int orderId = placedOrder.getOrderId();
            // Create and persist order products
            for(int i = 0; i < shoppingCartList.size(); i++){
                int orderProductId = 0;
                CartProduct cartProd = (CartProduct)shoppingCartList.get(i);
                int quantity = cartProd.getQuantity();
                Product productId = cartProd.getProduct();
                
                // Create orderProduct and persist it
                OrderProduct orderProduct = new OrderProduct(orderProductId, quantity, placedOrder, productId);
                orderProductDao.addOrderProduct(orderProduct);
            }
            session.invalidate();
            request.getRequestDispatcher("orderComplete.jsp").forward(request, response);
        }
        
        List<Product> products = productDao.getAllProducts();
        
        // Set request attributes and dispatch
        request.setAttribute("allProducts", products);
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private CartProduct getProduct(HttpServletRequest req){
        // Set attributes
        Product productAdded = productDao.getProduct(Integer.parseInt(req.getParameter("productAdded")));
        String name = productAdded.getName();
        int quantity = 1;
        
        // Decrement the quantity of the product in the database
        productAdded.decrementQuantity();
        productDao.editProduct(productAdded);
        
        // Create CartProduct and set its properties
        CartProduct cartProduct = new CartProduct();
        cartProduct.setName(name);
        cartProduct.setQuantity(quantity);
        cartProduct.setProduct(productAdded);
        
        return cartProduct;
    }
    
    private float calculateTotal(ArrayList list){
        float total = 0;
        for(int i = 0; i < list.size(); i++){
            CartProduct prod = (CartProduct)list.get(i);
            total += prod.getQuantity() * prod.getProduct().getPrice().floatValue();
        }
        return total;
    }
    
    private CustomerOrder createCustomerOrder(HttpServletRequest req, float total){
        Integer orderNo = ThreadLocalRandom.current().nextInt(1, 10000000 + 1);
        Integer orderId = 0;
        Date time = new java.util.Date();
        BigDecimal totalAmount = new BigDecimal(total);
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String postCode = req.getParameter("postCode");
        String country = req.getParameter("country");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String shipAddress = req.getParameter("shipAddress");
        String shipCity = req.getParameter("shipCity");
        String shipPostCode = req.getParameter("shipPostCode");
        String shipCountry = req.getParameter("shipCountry");
        String cardNo = req.getParameter("cardNo");
        String cardExpiry = req.getParameter("month") + "/" + req.getParameter("year");
        String cardType = req.getParameter("cardType");
        String cardCVC = req.getParameter("CVC");
        
        CustomerOrder newCustomerOrder = new CustomerOrder(orderId, time, totalAmount, firstName,
        lastName, address, city, postCode, country, phone, email, shipAddress, shipCity, shipPostCode,
        shipCountry, cardNo, cardExpiry, cardType, cardCVC, orderNo);
        
        return newCustomerOrder;
    } 
}
