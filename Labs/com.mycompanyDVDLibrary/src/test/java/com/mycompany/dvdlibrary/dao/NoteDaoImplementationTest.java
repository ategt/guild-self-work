/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.dto.DvdImplementation;
import com.mycompany.dvdlibrary.interfaces.Note;
import com.mycompany.dvdlibrary.dto.NoteImplementation;
import com.mycompany.dvdlibrary.interfaces.Dvd;
import com.mycompany.dvdlibrary.interfaces.DvdLibrary;
import com.mycompany.dvdlibrary.interfaces.NoteDao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class NoteDaoImplementationTest {

    NoteDao instance;
    ApplicationContext ctx;

    public NoteDaoImplementationTest() {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Before
    public void setUp() {
          instance = ctx.getBean("noteDao", NoteDaoImplementation.class);
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
       // NoteDaoImplementation instance = ctx.getBean("noteDao", NoteDaoImplementation.class);
        Note expResult = new NoteImplementation();
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
        NoteDaoImplementation noteDao = ctx.getBean("noteDao", NoteDaoImplementation.class);

        Note note1 = new NoteImplementation();
        noteDao.create(note1);

        assertTrue(noteDao.getList().contains(note1));

        int expSizeResult = 5;
        int sizeResult = noteDao.size();
        assertEquals(expSizeResult, sizeResult);

        String title = "note3";

        Note note = noteDao.get(5);

        assertTrue(note.getNoteString().equalsIgnoreCase(title));
        
        // Delete this, and clean up after the test.
        noteDao.delete(note1);
        
    }

    @Test
    public void testEncodeAndDecode() {

        //Dvd dvd = new DvdImplementation();
        NoteDao noteDao = ctx.getBean("productionNoteDao", NoteDaoImplementation.class);
        Note newNote = new NoteImplementation();

        // Create the file in the Dao.
        Note returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);

        // Set some text in the note file.
        returnedNote.setNoteString("This Is a test note.");

        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        NoteDao secondDao = ctx.getBean("productionNoteDao", NoteDaoImplementation.class);

        // Pull a note  using the id number recorded earlier.
        Note thirdNote = secondDao.get(id);
        
        // Check that the update method saved the new text.
        assertEquals("This Is a test note.", thirdNote.getNoteString());

        // Delete the test note.
        secondDao.delete(thirdNote);

        
        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        NoteDao thirdDao = ctx.getBean("productionNoteDao", NoteDaoImplementation.class);
        assertEquals(thirdDao.get(id), null);

    }


    /**
     * Test of create method, of class DvdLibrary.
     */
    @Test
    public void testCreateb() {
        System.out.println("create");
        // NoteDaoImplementation instance = ctx.getBean("noteDao", NoteDaoImplementation.class);
        Note expResult = new NoteImplementation();
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
    public void testGetAllDvdsb() {
        NoteDaoImplementation noteDao = ctx.getBean("noteDao", NoteDaoImplementation.class);

        Note note1 = new NoteImplementation();
        noteDao.create(note1);

        assertTrue(noteDao.getList().contains(note1));

        int expSizeResult = 5;
        int sizeResult = noteDao.size();
        assertEquals(expSizeResult, sizeResult);

        String title = "note3";

        Note note = noteDao.get(5);

        assertTrue(note.getNoteString().equalsIgnoreCase(title));
        
        // Delete this, and clean up after the test.
        noteDao.delete(note1);
        
    }

    @Test
    public void testEncodeAndDecodeb() {

        //Dvd dvd = new DvdImplementation();
        NoteDao noteDao = ctx.getBean("productionNoteDao", NoteDaoImplementation.class);
        Note newNote = new NoteImplementation();

        // Create the file in the Dao.
        Note returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);

        // Set some text in the note file.
        returnedNote.setNoteString("This Is a test note.");

        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        NoteDao secondDao = ctx.getBean("productionNoteDao", NoteDaoImplementation.class);

        // Pull a note  using the id number recorded earlier.
        Note thirdNote = secondDao.get(id);
        
        // Check that the update method saved the new text.
        assertEquals("This Is a test note.", thirdNote.getNoteString());

        // Delete the test note.
        secondDao.delete(thirdNote);

        
        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        NoteDao thirdDao = ctx.getBean("productionNoteDao", NoteDaoImplementation.class);
        assertEquals(thirdDao.get(id), null);

    }



    /**
     * Test of create method, of class DvdLibrary.
     */
    @Test
    public void testCreatec() {
        System.out.println("create");
        // NoteDaoImplementation instance = ctx.getBean("noteDao", NoteDaoImplementation.class);
        Note expResult = new NoteImplementation();
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
    public void testGetAllDvdsc() {
        NoteDaoImplementation noteDao = ctx.getBean("noteDao", NoteDaoImplementation.class);

        Note note1 = new NoteImplementation();
        noteDao.create(note1);

        assertTrue(noteDao.getList().contains(note1));

        int expSizeResult = 5;
        int sizeResult = noteDao.size();
        assertEquals(expSizeResult, sizeResult);

        String title = "note3";

        Note note = noteDao.get(5);

        assertTrue(note.getNoteString().equalsIgnoreCase(title));
        
        // Delete this, and clean up after the test.
        noteDao.delete(note1);
        
    }

    @Test
    public void testEncodeAndDecodec() {

        //Dvd dvd = new DvdImplementation();
        NoteDao noteDao = ctx.getBean("productionNoteDao", NoteDaoImplementation.class);
        Note newNote = new NoteImplementation();

        // Create the file in the Dao.
        Note returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);

        // Set some text in the note file.
        returnedNote.setNoteString("This Is a test note.");

        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        NoteDao secondDao = ctx.getBean("productionNoteDao", NoteDaoImplementation.class);

        // Pull a note  using the id number recorded earlier.
        Note thirdNote = secondDao.get(id);
        
        // Check that the update method saved the new text.
        assertEquals("This Is a test note.", thirdNote.getNoteString());

        // Delete the test note.
        secondDao.delete(thirdNote);

        
        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        NoteDao thirdDao = ctx.getBean("productionNoteDao", NoteDaoImplementation.class);
        assertEquals(thirdDao.get(id), null);

    }


}
