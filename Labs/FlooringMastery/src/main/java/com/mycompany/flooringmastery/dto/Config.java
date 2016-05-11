/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dto;

/**
 *
 * @author apprentice
 */
public class Config {
    private java.io.File productFile;
    private java.io.File taxesFile;
    private java.io.File testDirectory;
    
    private boolean inTestMode;

    /**
     * @return the productFile
     */
    public java.io.File getProductFile() {
        return productFile;
    }

    /**
     * @param productFile the productFile to set
     */
    public void setProductFile(java.io.File productFile) {
        this.productFile = productFile;
    }

    /**
     * @return the taxesFile
     */
    public java.io.File getTaxesFile() {
        return taxesFile;
    }

    /**
     * @param taxesFile the taxesFile to set
     */
    public void setTaxesFile(java.io.File taxesFile) {
        this.taxesFile = taxesFile;
    }

    /**
     * @return the testDirectory
     */
    public java.io.File getTestDirectory() {
        return testDirectory;
    }

    /**
     * @param testDirectory the testDirectory to set
     */
    public void setTestDirectory(java.io.File testDirectory) {
        this.testDirectory = testDirectory;
    }

    /**
     * @return the inTestMode
     */
    public boolean isInTestMode() {
        return inTestMode;
    }

    /**
     * @param inTestMode the inTestMode to set
     */
    public void setInTestMode(boolean inTestMode) {
        this.inTestMode = inTestMode;
    }
    
    
    
}
