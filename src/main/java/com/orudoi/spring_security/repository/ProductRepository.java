package com.orudoi.spring_security.repository;

import com.orudoi.spring_security.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

//import org.springframework.data.repository.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private static final Connection connection;

    /* JDBC driver and URL */
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   // private static final String DB_URL = "jdbc:mysql:/s_security_web_app/ss_db";
    //localhost:3306
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/s_security_web_app/ss_db";

    /* database credentials */
    private static final String USER = "root";
    private static final String PASS = "root";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductRepository() {
    }

    public Product getById(Long id) {
        Product product;
        String sql = "SELECT \n" +
                "`p`.`id` AS productID, \n" +
                "`p`.`name` AS name, \n" +
                "`p`.`cost` AS cost, \n" +
                "FROM `products` AS `p`";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            Long productID = rs.getLong("productID");
            String name = rs.getString("name");
            Double cost = rs.getDouble("cost");

            product = new Product(name, cost);
            product.setId(productID);

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return product;
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT \n" +
                "`p`.`id` AS productID, \n" +
                "`p`.`name` AS name, \n" +
                "`p`.`cost` AS cost, \n" +
                "FROM `ss_db.products` AS `p`";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Long productID = rs.getLong("productID");
                String name = rs.getString("name");
                Double cost = rs.getDouble("cost");

                Product product = new Product(name, cost);
                product.setId(productID);

                products.add(product);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(products);

        return !products.isEmpty()
                ? products
                : null;
    }

    public boolean add(Product product) {
        String sql = "INSERT INTO `products` (`name`, `cost`) VALUE (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getCost());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM products WHERE productID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
