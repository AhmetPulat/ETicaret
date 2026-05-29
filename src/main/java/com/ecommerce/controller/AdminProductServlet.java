package com.ecommerce.controller;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@WebServlet("/admin/products")
public class AdminProductServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Güvenlik kontrolü
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null || !"admin".equals(loggedUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        // Eğer 'add' parametresi geldiyse ürün ekleme formunu göster
        if ("add".equals(action)) {
            List<Category> categories = categoryDAO.getAllActiveCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/admin/product-form.jsp").forward(request, response);
        } else {
            // Varsayılan olarak tüm ürünleri listele
            List<Product> products = productDAO.getAllActiveProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/admin/products.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // Formdan gelen verileri al [cite: 157-164]
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String imageUrl = request.getParameter("imageUrl");

        // Ürünü veritabanına ekle
        String sql = "INSERT INTO products (category_id, name, description, price, stock, image_url) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setString(2, name);
            ps.setString(3, description);
            ps.setDouble(4, price);
            ps.setInt(5, stock);
            ps.setString(6, imageUrl);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String mesaj = java.net.URLEncoder.encode("Ürün başarıyla eklendi", "UTF-8");
        response.sendRedirect(request.getContextPath() + "/admin/products?success=" + mesaj);
    }
}