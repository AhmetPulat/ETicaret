package com.ecommerce.controller;

import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");

        // Güvenlik Kontrolü: Giriş yapılmamışsa veya rol admin değilse engelle
        if (loggedUser == null || !"admin".equals(loggedUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login?error=Bu sayfaya erisim yetkiniz yok.");
            return;
        }

        // Admin dashboard JSP sayfasına yönlendir
        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
    }
}