/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.exceptions.ConfigurationFileCorruptException;
import com.mycompany.flooringmastery.exceptions.FileCreationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class ConfigDaoTest {

    public ConfigDaoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTheFileConstructor() {

        java.io.File testConfigFile = new java.io.File("testConfigFile.txt");

        ConfigDao configDao = null;
        try {
            configDao = new ConfigDao(testConfigFile);
            fail("The test was supposed to throw an exception here.");
        } catch (ConfigurationFileCorruptException ex) {
            System.out.println("This Test Checks to see if the exception thrower works right.");
            System.out.println(" Exception Message: " + ex.getMessage());
            System.out.println("End Exception Thrower test.");
        } catch (FileCreationException ex) {
            fail("The test was not supposed to throw an exception here.");
            Logger.getLogger(ConfigDaoTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" Exception Message: " + ex.getMessage());
        }

        assertEquals(null, configDao);

    }

    @Test
    public void testTheFileConstructorB() {
        try {

            java.io.File defaultTestDirectory = new java.io.File("Test");
            java.io.File defaultTaxesFile = new java.io.File("Data/Taxes.txt");
            java.io.File defaultProductsFile = new java.io.File("Data/Products.txt");
            java.io.File testConfigFile = new java.io.File("testConfigFile2.txt");
            testConfigFile.deleteOnExit();

            ConfigDao configDao = new ConfigDao(testConfigFile);

            com.mycompany.flooringmastery.dto.Config config = configDao.get();

            String productFile = defaultProductsFile.getPath();

            assertEquals(productFile, config.getProductFile().getPath());

            String taxesFile = defaultTaxesFile.getPath();

            assertEquals(taxesFile, config.getTaxesFile().getPath());

            String testDirectory = defaultTestDirectory.getPath();

            assertEquals(testDirectory, config.getTestDirectory().getPath());
        } catch (ConfigurationFileCorruptException ex) {
            Logger.getLogger(ConfigDaoTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" Exception Message: " + ex.getMessage());
            fail("The Test Threw an exception.");
        } catch (FileCreationException ex) {
            Logger.getLogger(ConfigDaoTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" Exception Message: " + ex.getMessage());
            fail("The Test Threw an exception.");
        }

    }

    @Test
    public void testEncodeAndDecode() {
        try {

            java.io.File defaultTestDirectory = new java.io.File("Test");
            java.io.File defaultTaxesFile = new java.io.File("Data/Taxes.txt");
            java.io.File defaultProductsFile = new java.io.File("Data/Products.txt");
            java.io.File testConfigFile = new java.io.File("testConfigFile3.txt");
            testConfigFile.deleteOnExit();

            ConfigDao configDao = new ConfigDao(testConfigFile);

            com.mycompany.flooringmastery.dto.Config config = configDao.get();

            String productFile = defaultProductsFile.getPath();

            assertEquals(productFile, config.getProductFile().getPath());

            String taxesFile = defaultTaxesFile.getPath();

            assertEquals(taxesFile, config.getTaxesFile().getPath());

            String testDirectory = defaultTestDirectory.getPath();

            assertEquals(testDirectory, config.getTestDirectory().getPath());

            // Load a new instance and see if it was able to load the correct values.
            ConfigDao secondConfigDao = new ConfigDao(testConfigFile);

            com.mycompany.flooringmastery.dto.Config secondConfig = secondConfigDao.get();

            assertEquals(productFile, secondConfig.getProductFile().getPath());
            assertEquals(taxesFile, secondConfig.getTaxesFile().getPath());
            assertEquals(testDirectory, secondConfig.getTestDirectory().getPath());

        } catch (ConfigurationFileCorruptException ex) {
            Logger.getLogger(ConfigDaoTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" Exception Message: " + ex.getMessage());
            fail("The Test Threw an exception.");
        } catch (FileCreationException ex) {
            Logger.getLogger(ConfigDaoTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" Exception Message: " + ex.getMessage());
            fail("The Test Threw an exception.");
        }

    }

    @Test
    public void testEncodeAndDecodeWithUpdate() {
        try {

            java.io.File defaultTestDirectory = new java.io.File("Test");
            java.io.File defaultTaxesFile = new java.io.File("Data/Taxes.txt");
            java.io.File defaultProductsFile = new java.io.File("Data/Products.txt");
            java.io.File testConfigFile = new java.io.File("testConfigFile4.txt");
            testConfigFile.deleteOnExit();

            ConfigDao configDao = new ConfigDao(testConfigFile);

            com.mycompany.flooringmastery.dto.Config config = configDao.get();

            String productFile = defaultProductsFile.getPath();

            assertEquals(productFile, config.getProductFile().getPath());

            String taxesFile = defaultTaxesFile.getPath();

            assertEquals(taxesFile, config.getTaxesFile().getPath());

            String testDirectory = defaultTestDirectory.getPath();

            assertEquals(testDirectory, config.getTestDirectory().getPath());

            java.io.File differentTaxesFile = new java.io.File("ExtraTestTaxes.txt");

            config.setTaxesFile(differentTaxesFile);
            config.setInTestMode(false);
            configDao.update();

            // Load a new instance and see if it was able to load the correct values.
            ConfigDao secondConfigDao = new ConfigDao(testConfigFile);

            com.mycompany.flooringmastery.dto.Config secondConfig = secondConfigDao.get();

            assertEquals(productFile, secondConfig.getProductFile().getPath());
            assertEquals(differentTaxesFile.getPath(), secondConfig.getTaxesFile().getPath());
            assertEquals(testDirectory, secondConfig.getTestDirectory().getPath());
            assertEquals(false, secondConfig.isInTestMode());

        } catch (ConfigurationFileCorruptException ex) {
            Logger.getLogger(ConfigDaoTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" Exception Message: " + ex.getMessage());
            fail("The Test Threw an exception.");
        } catch (FileCreationException ex) {
            Logger.getLogger(ConfigDaoTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" Exception Message: " + ex.getMessage());
            fail("The Test Threw an exception.");
        }

    }

    @Test
    public void testDecode() {
        try {

            java.io.File differentTaxesFile = new java.io.File("Data/ExtraTestTaxes.txt");
            java.io.File defaultTestDirectory = new java.io.File("Test");
            java.io.File defaultProductsFile = new java.io.File("Data/ProductsGarbal.txt");
            java.io.File testConfigFile = new java.io.File("testConfigFile5.txt");
            //java.io.File dataDirectory = new java.io.File("testConfigFile5.txt");
            

            String productFile = defaultProductsFile.getPath();
            String testDirectory = defaultTestDirectory.getPath();

            // Load a new instance and see if it was able to load the correct values.
            ConfigDao secondConfigDao = new ConfigDao(testConfigFile);

            com.mycompany.flooringmastery.dto.Config secondConfig = secondConfigDao.get();

            assertEquals(productFile, secondConfig.getProductFile().getPath());
            assertEquals(differentTaxesFile.getPath(), secondConfig.getTaxesFile().getPath());
            assertEquals(testDirectory, secondConfig.getTestDirectory().getPath());
            assertEquals(false, secondConfig.isInTestMode());

        } catch (ConfigurationFileCorruptException ex) {
            Logger.getLogger(ConfigDaoTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" Exception Message: " + ex.getMessage());
            fail("The Test Threw an exception.");
        } catch (FileCreationException ex) {
            Logger.getLogger(ConfigDaoTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" Exception Message: " + ex.getMessage());
            fail("The Test Threw an exception.");
        }

    }

}
