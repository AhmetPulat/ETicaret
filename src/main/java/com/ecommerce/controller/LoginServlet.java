package com.ecommerce.controller;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.login(email, password);

        if (user != null) {
            // Giriş başarılı, Session oluştur
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);
            
            // Kullanıcı rolüne göre yönlendirme yap
            if ("admin".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            // Hata durumunda tekrar login sayfasına dön
            request.setAttribute("errorMessage", "E-posta veya şifre hatalı.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}