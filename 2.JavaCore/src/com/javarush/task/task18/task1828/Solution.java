package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Прайсы 2
*/


public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = bufferedReader.readLine();
        List<Product> productMap = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.ready()) {
                String read = reader.readLine();
                productMap.add(new Product(read.substring(0, 8), read.substring(8, 38), read.substring(38, 46), read.substring(46, 50)));
            }
        }

        if (args[0] != null) {
            switch (args[0]) {
                case "-d":
                    Product deleteProduct = null;
                    for (Product product : productMap) {
                        if (product.getId() == Integer.parseInt(args[1])) {
                            deleteProduct = product;
                        }
                    }
                    if (deleteProduct != null) productMap.remove(deleteProduct);

                    break;
                case "-u":
                    Product updateProduct = null;
                    for (Product p : productMap) {
                        if (p.getId() == Integer.parseInt(args[1])) {
                            updateProduct = p;
                        }
                    }
                    if (updateProduct != null) {
                        updateProduct.setProductName(args[2]);
                        updateProduct.setPrice(args[3]);
                        updateProduct.setQuantity(args[4]);
                    }

                    break;

            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Product p : productMap) {
                    writer.write(p.toString());
                    writer.write("\n");
                }
            }
        }
    }

    public static class Product {
        private int id;
        private String productName;
        private String price;
        private String quantity;

        public Product(String id, String productName, String price, String quantity) {
            setId(id);
            setProductName(productName);
            setPrice(price);
            setQuantity(quantity);
        }

        public int getId() {
            return id;
        }

        public void setId(String id) {
            this.id = Integer.parseInt(id.trim());
        }

        public void setProductName(String productName) {
            this.productName = productName.length() > 30 ? productName.trim().substring(0, 30) : productName.trim();
        }

        public void setPrice(String price) {
            this.price = price.length() > 8 ? price.trim().substring(0, 8) : price.trim();
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity.length() > 4 ? quantity.trim().substring(0, 4) : quantity.trim();
        }

        @Override
        public String toString() {
            return String.format("%-8d%-30s%-8s%-4s", id, productName, price, quantity);
        }
    }
}







