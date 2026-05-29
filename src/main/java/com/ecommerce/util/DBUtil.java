package com.ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // Veritabanı bağlantı bilgileri
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce_db?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String USER = "root";      // Kendi MySQL kullanıcı adını yaz
    private static final String PASSWORD = "";  // Kendi MySQL şifreni yaz

    // JDBC Driver sınıfını yükleme
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("MySQL Driver bulunamadı!");
        }
    }

    // Bağlantıyı döndüren metod
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}