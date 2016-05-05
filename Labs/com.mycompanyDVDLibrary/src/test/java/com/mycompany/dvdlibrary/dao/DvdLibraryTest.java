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
     
        Dvd dvd = new Dvd();
        NoteDao noteDao = new NoteDao();
        
        // Load the test File
        DvdLibrary instance = new DvdLibrary(true, noteDao);
        Dvd expResult = dvd;
        Dvd result = instance.create(dvd);
        
        // Check to see that create() passed back to same file.
        assertEquals(expResult, result);
        
        
        // Check to see that create() assigned the Object a valid ID.
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Dvd returnedDvd = instance.get(id);
        assertEquals(returnedDvd, result);
        instance.delete(dvd);

        // Test that the Delete method erased it.
        returnedDvd = instance.get(id);
        assertEquals(returnedDvd, null);
    }

    /**
     * Test of getAllAddresses method, of class DvdLibrary.
     */
    @Test
    public void testGetAllDvds() {

        // Instantiate a DvdLibrary object using the test constructor.
        // This constructor loads a seperate test file.
        NoteDao noteDao = new NoteDao();
        DvdLibrary instance = new DvdLibrary(true, noteDao);
        
        Dvd dvdOne = new Dvd();
        Dvd dvdTwo = new Dvd();
        Dvd dvdThree = new Dvd();
        
        // Add three DVDs to the list.
        instance.create(dvdOne);
        instance.create(dvdTwo);
        instance.create(dvdThree);
        
        // Check to see that the list contains one of the added DVDs.
        assertTrue(instance.getAllDvds().contains(dvdOne));
        assertTrue(instance.getAllDvds().contains(dvdTwo));
        assertTrue(instance.getAllDvds().contains(dvdThree));

        int expSizeResult = 11;


        assertEquals(expSizeResult + 3, instance.size());

        
        instance.delete(dvdOne);
        instance.delete(dvdTwo);
        instance.delete(dvdThree);
        
        // Check that the delete method shrank the list size by 3.
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);
        
        // Check to see if a fictional Dvd is on the list.
        String title = "SpongeBob";
        List<Dvd> result = instance.searchByTitle(title);
        assertEquals(true, result.isEmpty());

        // Check to see if a real DVD is on the list.
        title = "Bills movie 3";
        result = instance.searchByTitle(title);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
    }
    
}
