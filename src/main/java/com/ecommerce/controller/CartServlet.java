package com.ecommerce.controller;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Sepet sayfasını görüntüle
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        // Session'da sepet yoksa yeni bir tane oluştur
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        if ("add".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Product product = productDAO.getProductById(productId);

            if (product != null && product.getStock() >= quantity) {
                boolean exists = false;
                // Ürün sepette zaten varsa miktarını artır
                for (CartItem item : cart) {
                    if (item.getProduct().getId() == productId) {
                        item.setQuantity(item.getQuantity() + quantity);
                        exists = true;
                        break;
                    }
                }
                // Yoksa yeni olarak ekle
                if (!exists) {
                    cart.add(new CartItem(product, quantity));
                }
            }
        } else if ("remove".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            cart.removeIf(item -> item.getProduct().getId() == productId);
        }

        // Sepeti güncelle ve sepet sayfasına geri dön
        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}