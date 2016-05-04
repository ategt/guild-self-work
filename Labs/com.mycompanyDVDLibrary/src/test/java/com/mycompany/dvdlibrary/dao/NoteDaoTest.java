/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.dto.Dvd;
import com.mycompany.dvdlibrary.dto.Note;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class NoteDaoTest {
    
    public NoteDaoTest() {
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
        NoteDao instance = new NoteDao(true);
        Note expResult = new Note();
        Note result = instance.create(expResult);
        assertEquals(expResult, result);
        
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Note returnedNote = instance.get(id);
        assertEquals(returnedNote, result);
        instance.delete(expResult);

        returnedNote = instance.get(id);
        assertEquals(returnedNote, null);
    }

    /**
     * Test of getAllAddresses method, of class DvdLibrary.
     */
    @Test
    public void testGetAllDvds() {
        NoteDao noteDao = new NoteDao(true);
        
        
        Note note1 = new Note();
        noteDao.create(note1);
        
        assertTrue(noteDao.getList().contains(note1));
                
        int expSizeResult = 5;
        int sizeResult = noteDao.size();
        assertEquals(expSizeResult, sizeResult);
        

        String title = "note3";
        
        Note note = noteDao.get(5);
        
        
        
        assertTrue(note.getNoteString().equalsIgnoreCase(title));
    }
    
    
}
