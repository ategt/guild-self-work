/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class ProductDao {

    //List<Product> products;
    java.util.Map<String, Product> productsMap;
    int nextId;
    File productDataFile;   // = new File("ProductsData.txt");

//    public ProductDao() {
//        this(false);
//    }

    public ProductDao(ConfigDao configDao) {

        productDataFile = configDao.get().getProductFile();
        
        productsMap = decode();

        if (productsMap == null) {
            productsMap = new java.util.HashMap();
            System.out.println("The list was empty, making a new one.");
        }

        //nextId = determineNextId();
    }

    public Product create(Product product) {
        if (product != null) {
            return create(product.getType(), product);
        } else {
            return null;
        }
    }

    public Product create(Product product, String productName) {
        return create(productName, product);
    }

    public Product create(String productName, Product product) {

        if (product == null) {
            return null;
        } else if (product.getType() == null) {
            return null;
        } else if (productName.equals(product.getType())) {

            String titleCaseName = com.mycompany.flooringmastery.utilities.TextUtilities.toTitleCase(productName);

            if (!productsMap.containsKey(titleCaseName)) {
                productsMap.put(titleCaseName, product);
                product.setType(titleCaseName);
                encode();

                return product;
            } else {
                return null;
            }
        } else {
            return null;  // Look up how to throw exceptions and consider that instead.
        }
    }

    public Product get(String name) {

        String input = null;

        for (String productTest : productsMap.keySet()) {
            if (name.equalsIgnoreCase(productTest)) {
                input = productTest;
                break;
            }
        }
        return productsMap.get(input);

    }

    public void update(Product product) {
        Product foundProduct = productsMap.get(product.getType());

//        Product found = null;
//
//        for (Product currentProduct : products) {
//            if (currentProduct.getId() == product.getId()) {
//                found = currentProduct;
//                break;
//            }
//        }
        if (foundProduct != null) {

            if (foundProduct.getType().equals(product.getType())) {
                productsMap.remove(foundProduct.getType());
                productsMap.put(product.getType(), product);

                encode();
            } else {
                System.out.println("Throwing a Product Not Found exception!!!!");
                // Look up exception throwing and consider putting one here, too!
            }
        } else {
            System.out.println("Throwing a Product is null exception!!!!");
            // Look up exception throwing and consider putting one here, too!
        }
    }

    public void delete(Product product) {
//        Product found = null;
//
//        for (Product currentProduct : products) {
//            if (currentProduct.getId() == product.getId()) {
//                found = currentProduct;
//                break;
//            }
//        }
//
//        if (found != null) {
//            products.remove(found);
//        }

        if (productsMap.containsKey(product.getType())) {
            productsMap.remove(product.getType());
            encode();
        } else {
            System.out.println("Throwing a Product Not Found exception!!!!");
            // Look up exception throwing and consider putting one here, too!

        }
    }

    public List<String> getList() {
        return new ArrayList(productsMap.keySet());
    }

    public int size() {
        return productsMap.size();
    }

    private void encode() {

        final String TOKEN = ",";
        final String DATAHEADER = "ProductType,CostPerSquareFoot,LaborCostPerSquareFoot";
        try {

            try (PrintWriter out = new PrintWriter(new FileWriter(productDataFile))) {
                out.println(DATAHEADER);

                //for (Product product : products) {
                for (String productType : getList()) {

                    Product product = get(productType);

                    out.print(product.getType());
                    out.print(TOKEN);
                    out.print(product.getCost());
                    out.print(TOKEN);
                    out.print(product.getLaborCost());
                    out.println("");
                }

                out.flush();
            }

        } catch (IOException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //private List<Product> decode() {
    private java.util.Map<String, Product> decode() {

        //List<Product> productList = new ArrayList<>();
        java.util.Map<String, Product> productMap = new java.util.HashMap();

        final String TOKEN = ",";

        try {

            if (!productDataFile.exists()) {
                productDataFile.createNewFile();
            }

            try (Scanner sc = new Scanner(new BufferedReader(new FileReader(productDataFile)))) {
                while (sc.hasNextLine()) {
                    String currentLine = sc.nextLine();
                    if (!currentLine.trim().isEmpty()) {
                        String[] stringParts = currentLine.split(TOKEN);

                        Product product = new Product();

                        String content = stringParts[0];

                        product.setType(content);

                        String costPerSquareFootString = stringParts[1];

                        try {
                            double costPerSquareFoot = Double.parseDouble(costPerSquareFootString);
                            product.setCost(costPerSquareFoot);
                        } catch (NumberFormatException numFmtEx) {

                        }

                        String laborCostPerSquareFootString = stringParts[2];

                        try {

                            double laborCostPerSquareFoot = Double.parseDouble(laborCostPerSquareFootString);

                            product.setLaborCost(laborCostPerSquareFoot);

                        } catch (NumberFormatException numFmtEx) {

                        }

                        productMap.put(product.getType(), product);
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return productMap;
    }

}
