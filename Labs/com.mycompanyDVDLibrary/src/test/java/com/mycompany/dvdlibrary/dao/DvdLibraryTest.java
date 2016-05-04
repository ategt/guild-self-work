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
        NoteDao noteDao = new NoteDao();
        DvdLibrary instance = new DvdLibrary(true, noteDao);
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
    public void testGetAllDvds() {
        System.out.println("getAllDvds");
        NoteDao noteDao = new NoteDao();
        DvdLibrary instance = new DvdLibrary(true, noteDao);
        
        Dvd dvdOne = new Dvd();
        Dvd dvdTwo = new Dvd();
        Dvd dvdThree = new Dvd();
        
        instance.create(dvdOne);
        instance.create(dvdTwo);
        instance.create(dvdThree);
        
        assertTrue(instance.getAllDvds().contains(dvdTwo));
        
        instance.delete(dvdOne);
        instance.delete(dvdTwo);
        instance.delete(dvdThree);
        
        int expSizeResult = 3;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);
        
        String title = "SpongeBob";
        List<Dvd> result = instance.searchByTitle(title);
        assertEquals(true, result.isEmpty());

        title = "Jones";
        result = instance.searchByTitle(title);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
    }
    
}
