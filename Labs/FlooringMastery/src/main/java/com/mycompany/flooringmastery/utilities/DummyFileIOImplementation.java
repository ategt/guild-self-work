/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.utilities;

import com.mycompany.flooringmastery.dto.Product;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class DummyFileIOImplementation implements com.mycompany.flooringmastery.utilities.GenericMapFileIO<Product> {
    
    @Override
    public Map<String, Product> decode(File dataFile) {

        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(DummyFileIOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }

    @Override
    public void encode(File dataFile, List<Product> encodingList) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(DummyFileIOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
