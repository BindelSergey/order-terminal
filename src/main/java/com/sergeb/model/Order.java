package com.sergeb.model;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private Map<Product, Integer> products = new HashMap<Product, Integer>();
    private boolean processed = false;

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public void add(Product product, Integer quantity) {
        if (products.containsKey(product)) {
            quantity += products.get(product);
        }
        products.put(product, quantity);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void printOrderSummary() {
        System.out.println("Your order is: ");
        double totalPrice = 0.0f;
        for (Product product : products.keySet()) {
            System.out.println(products.get(product) + " " + product.getName());
            totalPrice += product.getPrice() * products.get(product);
        }
        System.out.println("Total price is: " + totalPrice + " USD.");
    }

}
