package com.ecommerce.controller;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.model.Category;
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

@WebServlet("/admin/categories")
public class AdminCategoryServlet extends HttpServlet {
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Güvenlik: Sadece admin girebilir
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null || !"admin".equals(loggedUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Tüm aktif kategorileri çek ve JSP'ye yolla
        List<Category> categories = categoryDAO.getAllActiveCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/admin/categories.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        // Yeni kategori ekleme işlemi (DAO içine de eklenebilir, pratiklik için burada yazdık)
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/admin/categories?success=Kategori eklendi");
    }
}