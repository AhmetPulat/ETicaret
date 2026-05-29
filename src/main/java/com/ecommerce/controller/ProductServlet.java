package com.ecommerce.controller;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        
        if (idParam != null && !idParam.isEmpty()) {
            int productId = Integer.parseInt(idParam);
            Product product = productDAO.getProductById(productId);
            
            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("/product-detail.jsp").forward(request, response);
                return;
            }
        }
        // Ürün bulunamazsa ana sayfaya yönlendir
        response.sendRedirect(request.getContextPath() + "/home");
    }
}