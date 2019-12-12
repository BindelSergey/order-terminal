package com.sergeb.service;

import com.sergeb.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlService {

    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/order-terminal";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root123#";

    private static final String GET_ALL_PRODUCTS = "SELECT * FROM `order-terminal`.Product;";

    private Connection connection = null;

    public List<Product> getAllProducts() {
        ResultSet productsResultSet;
        List<Product> products = new ArrayList<Product>();
        try {
            productsResultSet = getConnection().createStatement().executeQuery(GET_ALL_PRODUCTS);
            while (productsResultSet.next()) {
                products.add(
                        new Product(
                                productsResultSet.getInt("id"),
                                productsResultSet.getString("name"),
                                productsResultSet.getString("description"),
                                productsResultSet.getDouble("price")
                        )
                );
            }
            getConnection().close();
        } catch (SQLException e) {
            System.out.println("Cannot get All Products. Reason: " + e.getMessage());
        }
        return products;
    }

    private Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                System.out.println("Cannot connect to DB. Reason: " + e.getMessage());
            }
        }
        return connection;
    }
}
