package com.ecommerce.dao;

import com.ecommerce.model.Category;
import com.ecommerce.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    // Aktif olan tüm kategorileri getir
    public List<Category> getAllActiveCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories WHERE is_active = TRUE";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setActive(rs.getBoolean("is_active"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    // ID'ye göre kategori getir
    public Category getCategoryById(int id) {
        Category category = null;
        String sql = "SELECT * FROM categories WHERE id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setActive(rs.getBoolean("is_active"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}