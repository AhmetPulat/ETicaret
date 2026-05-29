package com.ecommerce.controller;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Bu annotation sayesinde web.xml'e gerek kalmadan URL haritalaması yapıyoruz
@WebServlet(name = "HomeServlet", urlPatterns = {"", "/home"})
public class HomeServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // DAO'dan aktif ürünleri çek
        List<Product> products = productDAO.getAllActiveProducts();
        
        // Ürünleri JSP sayfasına göndermek üzere request içine koy
        request.setAttribute("products", products);
        
        // index.jsp sayfasına yönlendir
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}