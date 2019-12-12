package com.sergeb;

import com.sergeb.model.Order;
import com.sergeb.service.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class Terminal {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static boolean isLastChoiceValid = true;

    public static void main(String[] args) throws IOException {
        ProductService productService = new ProductService();
        Order order = new Order();

        System.out.println("Good day! What would you like to eat today?");

        while (true) {
            if (order.isEmpty() || !isLastChoiceValid) {
                productService.printMenu();
            } else {
                order.printOrderSummary();
                suggestSomethingElse(productService, order);
            }

            String input = bufferedReader.readLine();
            if (input.equals("0")) {
                return;
            } else if (!productService.isValid(input)) {
                System.out.println("There aren't product with identifier '" + input + "'. Please choose one from the list.\n");
                isLastChoiceValid = false;
            } else {
                isLastChoiceValid = true;
                System.out.println("How many items you'd like to take?");
                String amount = processInput(bufferedReader.readLine(), StringHelper::isNumeric, "Please enter valid digit.");
                order.add(
                        productService.getProduct(Integer.parseInt(input)),
                        Integer.parseInt(amount)
                );
            }
        }
    }

    private static String processInput(String input, Function<String, Boolean> function, String message) throws IOException {
        while (!function.apply(input)) {
            System.out.println(message);
            input = bufferedReader.readLine();
        }
        return input;
    }

    private static void suggestSomethingElse(ProductService productService, Order order) throws IOException {
        System.out.println("Would you like to take something else? Y/N");
        String answer = processInput(bufferedReader.readLine(), StringHelper::isValidYesNo, "Please enter valid answer: Y/N");
        if (answer.equals("Y")) {
            productService.printMenu();
        } else if (answer.equals("N")) {
            order.setProcessed(true);
            order.printOrderSummary();
            System.out.println("Thank you!");
            System.exit(0);
        }
    }
}
