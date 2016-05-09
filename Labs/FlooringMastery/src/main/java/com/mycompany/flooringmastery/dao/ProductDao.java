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

    List<Product> products;
    int nextId;
    File productDataFile = new File("ProductsData.txt");

    ;

    public ProductDao() {
        this(false);
    }

    protected ProductDao(boolean isATest) {

        if (isATest) {
            productDataFile = new File("ProductsTestData.txt");
        }

        products = decode();

        if (products == null) {
            products = new ArrayList();
            System.out.println("The list was empty, making a new one.");
        }

        nextId = determineNextId();
    }

    public Product create(Product product) {
        product.setId(nextId);
        nextId++;

        products.add(product);

        encode();

        return product;
    }

    public Product get(Integer id) {

        for (Product product : products) {
            if (product != null) {
                if (product.getId() == id) {
                    return product;
                }
            }
        }

        return null;
    }

    public void update(Product product) {
        Product found = null;

        for (Product currentProduct : products) {
            if (currentProduct.getId() == product.getId()) {
                found = currentProduct;
                break;
            }
        }

        if (found != null) {
            products.remove(found);
            products.add(product);
        }

        encode();

    }

    public void delete(Product product) {
        Product found = null;

        for (Product currentProduct : products) {
            if (currentProduct.getId() == product.getId()) {
                found = currentProduct;
                break;
            }
        }

        if (found != null) {
            products.remove(found);
        }

        encode();

    }

    public List<Product> getList() {

        return products;
    }

    public int size() {
        return products.size();
    }

    private int determineNextId() {
        int highestId = 100;

        for (Product product : products) {
            if (highestId < product.getId()) {
                highestId = product.getId();
            }
        }

        highestId++;
        return highestId++;
    }

    private void encode() {

        final String TOKEN = ",";
        final String DATAHEADER = "ProductType,CostPerSquareFoot,LaborCostPerSquareFoot";
        try {

            try (PrintWriter out = new PrintWriter(new FileWriter(productDataFile))) {
                out.println(DATAHEADER);
                
                for (Product product : products) {
                    
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

    private List<Product> decode() {

        List<Product> productList = new ArrayList<>();

        final String TOKEN = ",";

        try {

            if (!productDataFile.exists()) {
                productDataFile.createNewFile();
            }

            Scanner sc = new Scanner(new BufferedReader(new FileReader(productDataFile)));

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

                    productList.add(product);
                }
            }
            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return productList;
    }

}
