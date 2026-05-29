package com.ecommerce.dao;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.Order;
import com.ecommerce.util.DBUtil;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class OrderDAO {

    // Sipariş oluşturma (Transaction ile güvenli kayıt)
    public boolean createOrder(Order order, List<CartItem> cartItems) {
        boolean isSuccess = false;
        Connection conn = null;
        
        try {
            conn = DBUtil.getConnection();
            // Transaction başlatıyoruz. Hata olursa hiçbir şey veritabanına yazılmayacak.
            conn.setAutoCommit(false); 
            
            // 1. Siparişi 'orders' tablosuna ekle
            String orderSql = "INSERT INTO orders (user_id, total_amount, status) VALUES (?, ?, ?)";
            PreparedStatement orderStmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, order.getUserId());
            orderStmt.setDouble(2, order.getTotalAmount());
            orderStmt.setString(3, "Beklemede");
            orderStmt.executeUpdate();
            
            // Oluşan siparişin ID'sini al
            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            int orderId = 0;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }
            
            // 2. Sepetteki ürünleri 'order_items' tablosuna ekle ve Stokları düşür
            String itemSql = "INSERT INTO order_items (order_id, product_id, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?)";
            String stockUpdateSql = "UPDATE products SET stock = stock - ? WHERE id = ?";
            
            PreparedStatement itemStmt = conn.prepareStatement(itemSql);
            PreparedStatement stockStmt = conn.prepareStatement(stockUpdateSql);
            
            for (CartItem item : cartItems) {
                // Sipariş detayını ekle
                itemStmt.setInt(1, orderId);
                itemStmt.setInt(2, item.getProduct().getId());
                itemStmt.setInt(3, item.getQuantity());
                itemStmt.setDouble(4, item.getProduct().getPrice());
                itemStmt.setDouble(5, item.getSubtotal());
                itemStmt.executeUpdate();
                
                // İlgili ürünün stoğunu düşür
                stockStmt.setInt(1, item.getQuantity());
                stockStmt.setInt(2, item.getProduct().getId());
                stockStmt.executeUpdate();
            }
            
            // Her şey yolundaysa işlemleri veritabanına kalıcı olarak kaydet (Commit)
            conn.commit(); 
            isSuccess = true;
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Bir hata çıkarsa yapılan tüm işlemleri geri al
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }

    // Kullanıcının kendi siparişlerini görüntülemesi için
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}