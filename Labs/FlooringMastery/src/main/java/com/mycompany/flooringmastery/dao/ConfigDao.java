/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class ConfigDao {

    java.io.File configFile;
    com.mycompany.flooringmastery.dto.Config config;
    java.io.File defaultTestDirectory = new java.io.File("Test");
    java.io.File defaultTaxesFile = new java.io.File("Data\\Taxes.txt");
    java.io.File defaultProductsFile = new java.io.File("Data\\Products.txt");
    
    public ConfigDao() {
        this(new java.io.File("config.txt"));
    }

    public ConfigDao(java.io.File configFile) {
        this.configFile = configFile;

        this.config = buildConfig(configFile);
    }

    private com.mycompany.flooringmastery.dto.Config buildConfig(java.io.File configFile) {
        com.mycompany.flooringmastery.dto.Config tempConfig = null;
        if (configFile.exists()) {
            tempConfig = decode(configFile);
        } else {
            tempConfig = makeConfig();
            encode();
        }

        return tempConfig;
    }

    private com.mycompany.flooringmastery.dto.Config decode(java.io.File dataFile) {

        com.mycompany.flooringmastery.dto.Config tempConfig = new com.mycompany.flooringmastery.dto.Config();

        java.util.List<String> configList = new java.util.ArrayList();

        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(dataFile)))) {
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                if (!currentLine.trim().isEmpty()) {
                    configList.add(currentLine);
                }
            }

            for (String configLine : configList) {
                if (configLine.contains("ProductFile:")) {
                    String productFilePath = configLine.replace("ProductFile:", "");
                    java.io.File productFile = new java.io.File(productFilePath);
                    tempConfig.setProductFile(productFile);
                } else if (configLine.contains("TaxesFile:")) {
                    String taxesFilePath = configLine.replace("TaxesFile:", "");
                    java.io.File taxesFile = new java.io.File(taxesFilePath);
                    tempConfig.setTaxesFile(taxesFile);
                } else if (configLine.contains("TestDirectory:")) {
                    String testDirectoryPath = configLine.replace("TestDirectory:", "");
                    java.io.File testDirectory = new java.io.File(testDirectoryPath);
                    tempConfig.setTestDirectory(testDirectory);
                } else if (configLine.contains("InTestMode:")) {
                    String inTestModeString = configLine.replace("InTestMode:", "");
                    boolean inTestMode = true;
                    if (inTestModeString.equalsIgnoreCase("false")) {
                        inTestMode = false;
                    }
                    
                    tempConfig.setInTestMode(inTestMode);
                } else {
                    System.out.println("Throw unknown line in config file EXCEPTION!!");
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tempConfig;
    }
    
        private com.mycompany.flooringmastery.dto.Config makeConfig() {

        com.mycompany.flooringmastery.dto.Config tempConfig = new com.mycompany.flooringmastery.dto.Config();

        tempConfig.setInTestMode(true);
        tempConfig.setProductFile(defaultProductsFile);
        tempConfig.setTaxesFile(defaultTaxesFile);
        tempConfig.setTestDirectory(defaultTestDirectory);
        
        return tempConfig;
        }
    

        private void encode(){
            
        try (PrintWriter out = new PrintWriter(new FileWriter(configFile))) {
                
                String configString = "ProductFile:" + config.getProductFile().getPath() + "\n" +
                                    "TaxesFile:" + config.getTaxesFile().getPath() + "\n" +
                                    "TestDirectory:" + config.getTestDirectory().getPath() + "\n" +
                                    "InTestMode:" + config.isInTestMode();
                
                
                out.print(configString);
                out.println("");
            
            
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ConfigDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
            
            
        
        
}
