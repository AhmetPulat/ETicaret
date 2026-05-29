package com.ecommerce.controller;

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

@WebServlet("/admin/orders")
public class AdminOrderServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Güvenlik kontrolü
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null || !"admin".equals(loggedUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Normalde burada tüm siparişleri çeken bir DAO metodu çağrılır
        // Şimdilik sadece sayfayı yönlendiriyoruz (JSP tarafında halledebiliriz)
        request.getRequestDispatcher("/admin/orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String orderId = request.getParameter("orderId");
        String newStatus = request.getParameter("status");

        // Sipariş durumunu güncelle
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, Integer.parseInt(orderId));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/admin/orders?success=Durum guncellendi");
    }
}