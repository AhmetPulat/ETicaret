package com.ecommerce.dao;

import com.ecommerce.model.Product;
import com.ecommerce.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Ana sayfa için tüm aktif ürünleri getir
    public List<Product> getAllActiveProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE is_active = TRUE";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                products.add(mapRowToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Kategoriye göre filtreleme için ürünleri getir
    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category_id = ? AND is_active = TRUE";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                products.add(mapRowToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Detay sayfası için ID'ye göre tek bir ürün getir
    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                product = mapRowToProduct(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    // Kod tekrarını önlemek için yardımcı metod
    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setCategoryId(rs.getInt("category_id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setStock(rs.getInt("stock"));
        product.setImageUrl(rs.getString("image_url"));
        product.setActive(rs.getBoolean("is_active"));
        product.setCreatedAt(rs.getTimestamp("created_at"));
        return product;
    }
}