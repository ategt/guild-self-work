/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryweb.dao;

import com.mycompany.flooringmasteryweb.dto.Product;
import com.mycompany.flooringmasteryweb.utilities.ProductFileIO;
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

    java.util.Map<String, Product> productsMap;
    int nextId;
    File productDataFile;
    private ProductFileIO fileIo;

    public ProductDao(ConfigDao configDao) {
        
        this.fileIo = new com.mycompany.flooringmastery.utilities.ProductFileIOImpl(this);

        productDataFile = configDao.get().getProductFile();

        productsMap = fileIo.decode(productDataFile);

        if (productsMap == null) {
            productsMap = new java.util.HashMap();
            System.out.println("The list was empty, making a new one.");
        }

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
                fileIo.encode(productDataFile, getList());

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

        if (foundProduct != null) {

            if (foundProduct.getType().equals(product.getType())) {
                productsMap.remove(foundProduct.getType());
                productsMap.put(product.getType(), product);

                fileIo.encode(productDataFile, getList());

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

        if (productsMap.containsKey(product.getType())) {
            productsMap.remove(product.getType());
            fileIo.encode(productDataFile, getList());

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

}
