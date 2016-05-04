/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.dao.DvdLibrary;
import com.mycompany.dvdlibrary.dto.Dvd;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class DvdLibraryTest {
    
    public DvdLibraryTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class DvdLibrary.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Dvd address = new Dvd();
        DvdLibrary instance = new DvdLibrary(true);
        Dvd expResult = address;
        Dvd result = instance.create(address);
        assertEquals(expResult, result);
        
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Dvd returnedAddress = instance.get(id);
        assertEquals(returnedAddress, result);
        instance.delete(address);

        returnedAddress = instance.get(id);
        assertEquals(returnedAddress, null);
    }

    /**
     * Test of getAllAddresses method, of class DvdLibrary.
     */
    @Test
    public void testGetAllAddresses() {
        System.out.println("getAllAddresses");
        DvdLibrary instance = new DvdLibrary(true);
        
        Dvd addressOne = new Dvd();
        Dvd addressTwo = new Dvd();
        Dvd addressThree = new Dvd();
        
        instance.create(addressOne);
        instance.create(addressTwo);
        instance.create(addressThree);
        
        assertTrue(instance.getAllAddresses().contains(addressTwo));
        
        instance.delete(addressOne);
        instance.delete(addressTwo);
        instance.delete(addressThree);
        
        int expSizeResult = 3;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);
        
        String lastName = "Steve";
        List<Dvd> result = instance.searchByLastName(lastName);
        assertEquals(true, result.isEmpty());

        lastName = "Jones";
        result = instance.searchByLastName(lastName);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
    }
    
}
