package com.ecommerce.controller;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    // GET isteği gelirse sadece kayıt formunu göster
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    // POST isteği gelirse (Form submit edildiğinde) kaydı tamamla
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Türkçe karakter sorunu olmaması için
        
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // E-posta daha önce kayıtlı mı kontrolü
        if (userDAO.isEmailExists(email)) {
            request.setAttribute("errorMessage", "Bu e-posta adresi sistemde zaten kayıtlı.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        User newUser = new User(fullName, email, password, phone, address, "customer");
        boolean isSuccess = userDAO.registerUser(newUser);

        if (isSuccess) {
            // Başarılı olursa giriş sayfasına yönlendir
            response.sendRedirect(request.getContextPath() + "/login?message=registered");
        } else {
            request.setAttribute("errorMessage", "Kayıt sırasında bir hata oluştu, lütfen tekrar deneyin.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}