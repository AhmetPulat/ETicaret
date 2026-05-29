package com.ecommerce.controller;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Order;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");

        // Kullanıcı giriş yapmamışsa login sayfasına yönlendir
        if (loggedUser == null) {
            response.sendRedirect(request.getContextPath() + "/login?error=Lütfen sipariş vermek için giriş yapın.");
            return;
        }

        // Kullanıcının geçmiş siparişlerini getir
        List<Order> myOrders = orderDAO.getOrdersByUserId(loggedUser.getId());
        request.setAttribute("myOrders", myOrders);
        request.getRequestDispatcher("/my-orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        // Giriş yapılmamışsa engelle
        if (loggedUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Sepet boşsa engelle
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        // Sepetteki toplam tutarı hesapla
        double totalAmount = 0;
        for (CartItem item : cart) {
            totalAmount += item.getSubtotal();
        }

        // Siparişi oluştur
        Order order = new Order();
        order.setUserId(loggedUser.getId());
        order.setTotalAmount(totalAmount);

        boolean isSuccess = orderDAO.createOrder(order, cart);

        if (isSuccess) {
            session.removeAttribute("cart");
            String mesaj = java.net.URLEncoder.encode("Siparişiniz başarıyla oluşturuldu.", "UTF-8");
            response.sendRedirect(request.getContextPath() + "/order?success=" + mesaj);
        } else {
            String hata = java.net.URLEncoder.encode("Sipariş oluşturulurken bir hata oluştu.", "UTF-8");
            response.sendRedirect(request.getContextPath() + "/cart?error=" + hata);
        }
        }
}