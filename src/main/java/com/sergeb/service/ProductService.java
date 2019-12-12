package com.sergeb.service;

import com.sergeb.StringHelper;
import com.sergeb.model.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductService {

    private final static MySqlService MY_SQL_SERVICE = new MySqlService();
    private Map<Integer, Product> allProductsMap = new HashMap<Integer, Product>();

    public ProductService() {
        for (Product product : MY_SQL_SERVICE.getAllProducts()) {
            allProductsMap.put(product.getId(), product);
        }
    }

    public void printMenu() {
        for (Product product : allProductsMap.values()) {
            System.out.println(product.toString());
        }
        System.out.println("#0: Exit");
    }

    public boolean isValid(String input) {
        return StringHelper.isNumeric(input) && allProductsMap.containsKey(Integer.parseInt(input));
    }

    public Product getProduct(Integer id) {
        return allProductsMap.get(id);
    }
}
