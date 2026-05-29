package com.ecommerce.dao;

import com.ecommerce.model.User;
import com.ecommerce.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Kullanıcı Girişi (Login)
    public User login(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Yeni Kullanıcı Kaydı (Register)
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (full_name, email, password, phone, address, role) VALUES (?, ?, ?, ?, ?, ?)";
        boolean isSuccess = false;
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getRole() != null ? user.getRole() : "customer");
            
            int rowsAffected = ps.executeUpdate();
            isSuccess = rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // E-posta adresi sistemde kayıtlı mı kontrolü
    public boolean isEmailExists(String email) {
        boolean exists = false;
        String sql = "SELECT id FROM users WHERE email = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
}