/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.controller;

import com.antique.dao.CustomerOrderDaoLocal;
import com.antique.dao.OrderProductDaoLocal;
import com.antique.dao.ProductDaoLocal;
import com.antique.dao.UserDaoLocal;
import com.antique.model.CustomerOrder;
import com.antique.model.OrderProduct;
import com.antique.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
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
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {
    @EJB
    private ProductDaoLocal productDao;
    @EJB
    private CustomerOrderDaoLocal customerOrderDao;
    @EJB
    private OrderProductDaoLocal orderProductDao;
    @EJB
    private UserDaoLocal userDao;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        HttpSession session = request.getSession(true);
        if (session == null){
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
        
        if(!"true".equalsIgnoreCase((String)session.getAttribute("loginStatus"))){

            if(!"attempting".equalsIgnoreCase((String)session.getAttribute("loginAttempt"))){

                session.setAttribute("loginAttempt", "attempting");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            } else{
                String user = request.getParameter("user");
                String password = request.getParameter("password");
                if(!userDao.authenticateUser(user, password)){
                    request.setAttribute("wrongPassword", "true");
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                } else {
                    session.setAttribute("loginStatus", "true");
                }
            }
        }
        
        // Product object attributes
        String productIdStr = request.getParameter("productId");
        int productId = 0;
        if(productIdStr != null && !productIdStr.equals(""))
            productId = Integer.parseInt(productIdStr);
        
        String name = request.getParameter("name");
        
        String quantityStr = request.getParameter("quantity");
        int quantity = 0;
        if(quantityStr != null && !quantityStr.equals(""))
            quantity = Integer.parseInt(quantityStr);
        
        String description = request.getParameter("description");
        
        String imageUrl = request.getParameter("imageUrl");
        
        String priceStr = request.getParameter("price");
        BigDecimal price = new BigDecimal(0);
        if(priceStr != null && !priceStr.equals(""))
            price = new BigDecimal(priceStr);
        
        Product product = new Product(productId, name, quantity, price, imageUrl, description);
        List<Product> products = productDao.getAllProducts();
        
        if("products".equalsIgnoreCase(action)){
            products = productDao.getAllProducts();
            
            request.setAttribute("allProducts", products);
            request.getRequestDispatcher("adminProducts.jsp").forward(request, response);
        } else if("orders".equalsIgnoreCase(action)){
            List<CustomerOrder> orders = customerOrderDao.getAllCustomerOrders();
            
            request.setAttribute("allOrders", orders);
            request.getRequestDispatcher("adminOrders.jsp").forward(request, response);
        } else if("orderDetails".equalsIgnoreCase(action)){
            Integer orderId = Integer.parseInt(request.getParameter("orderDetails"));
            CustomerOrder order = customerOrderDao.getCustomerOrder(orderId);
            List<OrderProduct> orderProducts = orderProductDao.getOrderProductsOfOrder(order);

            request.setAttribute("order", order);
            request.setAttribute("orderProducts", orderProducts);
            request.getRequestDispatcher("adminOrderProducts.jsp").forward(request, response);
        }
        
        // Check type of action (which button was clicked)
        if("Add".equalsIgnoreCase(action)){
            productDao.addProduct(product);
            products = productDao.getAllProducts();
        } else if("Edit".equalsIgnoreCase(action)){
            productDao.editProduct(product);
            products = productDao.getAllProducts();
        } else if("Delete".equalsIgnoreCase(action)){
            productDao.deleteProduct(productId);
            products = productDao.getAllProducts();
        } else if("Search".equalsIgnoreCase(action)){
            product = productDao.getProduct(productId);
            products.clear();
            products.add(product);
        }
        
        request.setAttribute("product", product);
        request.setAttribute("allProducts", products);
        request.getRequestDispatcher("adminProducts.jsp").forward(request, response);
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

}
