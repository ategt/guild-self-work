/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseballleague.dao;

import com.mycompany.baseballleague.dto.Player;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class PlayerDaoTest {
    
    public PlayerDaoTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Test of create method, of class PlayerDao.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Player address = new Player();
        PlayerDao instance = new PlayerDao(true);
        Player expResult = address;
        Player result = instance.create(address);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Player returnedPlayer = instance.get(id);
        assertEquals(returnedPlayer, result);
        instance.delete(address);

        returnedPlayer = instance.get(id);
        assertEquals(returnedPlayer, null);
    }

   /**
     * Test of getAllPlayeres method, of class PlayerDao.
     */
    @Test
    public void testGetAllPlayeres() {
        System.out.println("getAllPlayeres");
        PlayerDao instance = new PlayerDao(true);

        Player addressOne = new Player();
        Player addressTwo = new Player();
        Player addressThree = new Player();

        instance.create(addressOne);
        instance.create(addressTwo);
        instance.create(addressThree);

        assertTrue(instance.getList().contains(addressOne));
        assertTrue(instance.getList().contains(addressTwo));
        assertTrue(instance.getList().contains(addressThree));

        instance.delete(addressOne);
        instance.delete(addressTwo);
        instance.delete(addressThree);

        int expSizeResult = 14;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);

    }

    @Test
    public void testEncodeAndDecode() {

        // The true parameter in the Player Book constructor signifies a test.
        PlayerDao noteDao = new PlayerDao(true);
        Player newNote = new Player();

        // Create the file in the Dao.
        Player returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);

        String firstName = "First and last name here";
        int playerNumber = 7;

        // Set some text in the note file.
        //returnedNote.setNoteString("This Is a test note.");
        returnedNote.setPlayerName(firstName);
        returnedNote.setPlayerNumber(playerNumber);
        int playerId = returnedNote.getId();
        
        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        PlayerDao secondDao = new PlayerDao(true);

        // Pull a note  using the id number recorded earlier.
        Player thirdNote = secondDao.get(id);

        assertTrue(thirdNote != null);
        
        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
        assertEquals(firstName, thirdNote.getPlayerName());
        assertEquals(playerNumber, thirdNote.getPlayerNumber());
        assertEquals(playerId, thirdNote.getId());
        
        
        // Delete the test note.
        secondDao.delete(thirdNote);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        PlayerDao thirdDao = new PlayerDao(true);
        assertEquals(thirdDao.get(id), null);

    }

    
    
    /**
     * Test of create method, of class PlayerDao.
     */
    @Test
    public void testCreateA() {
        System.out.println("create");
        Player address = new Player();
        PlayerDao instance = new PlayerDao(true);
        Player expResult = address;
        Player result = instance.create(address);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Player returnedPlayer = instance.get(id);
        assertEquals(returnedPlayer, result);
        instance.delete(address);

        returnedPlayer = instance.get(id);
        assertEquals(returnedPlayer, null);
    }

   /**
     * Test of getAllPlayeres method, of class PlayerDao.
     */
    @Test
    public void testGetAllPlayeresA() {
        System.out.println("getAllPlayeres");
        PlayerDao instance = new PlayerDao(true);

        Player addressOne = new Player();
        Player addressTwo = new Player();
        Player addressThree = new Player();

        instance.create(addressOne);
        instance.create(addressTwo);
        instance.create(addressThree);

        assertTrue(instance.getList().contains(addressOne));
        assertTrue(instance.getList().contains(addressTwo));
        assertTrue(instance.getList().contains(addressThree));

        instance.delete(addressOne);
        instance.delete(addressTwo);
        instance.delete(addressThree);

        int expSizeResult = 14;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);

    }

    @Test
    public void testEncodeAndDecodeA() {

        // The true parameter in the Player Book constructor signifies a test.
        PlayerDao noteDao = new PlayerDao(true);
        Player newNote = new Player();

        // Create the file in the Dao.
        Player returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);

        String firstName = "First and last name here";
        int playerNumber = 7;

        // Set some text in the note file.
        //returnedNote.setNoteString("This Is a test note.");
        returnedNote.setPlayerName(firstName);
        returnedNote.setPlayerNumber(playerNumber);
        int playerId = returnedNote.getId();
        
        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        PlayerDao secondDao = new PlayerDao(true);

        // Pull a note  using the id number recorded earlier.
        Player thirdNote = secondDao.get(id);

        assertTrue(thirdNote != null);
        
        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
        assertEquals(firstName, thirdNote.getPlayerName());
        assertEquals(playerNumber, thirdNote.getPlayerNumber());
        assertEquals(playerId, thirdNote.getId());
        
        
        // Delete the test note.
        secondDao.delete(thirdNote);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        PlayerDao thirdDao = new PlayerDao(true);
        assertEquals(thirdDao.get(id), null);

    }

    
    
    /**
     * Test of create method, of class PlayerDao.
     */
    @Test
    public void testCreateB() {
        System.out.println("create");
        Player address = new Player();
        PlayerDao instance = new PlayerDao(true);
        Player expResult = address;
        Player result = instance.create(address);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Player returnedPlayer = instance.get(id);
        assertEquals(returnedPlayer, result);
        instance.delete(address);

        returnedPlayer = instance.get(id);
        assertEquals(returnedPlayer, null);
    }

   /**
     * Test of getAllPlayeres method, of class PlayerDao.
     */
    @Test
    public void testGetAllPlayeresB() {
        System.out.println("getAllPlayeres");
        PlayerDao instance = new PlayerDao(true);

        Player addressOne = new Player();
        Player addressTwo = new Player();
        Player addressThree = new Player();

        instance.create(addressOne);
        instance.create(addressTwo);
        instance.create(addressThree);

        assertTrue(instance.getList().contains(addressOne));
        assertTrue(instance.getList().contains(addressTwo));
        assertTrue(instance.getList().contains(addressThree));

        instance.delete(addressOne);
        instance.delete(addressTwo);
        instance.delete(addressThree);

        int expSizeResult = 14;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);

    }

    @Test
    public void testEncodeAndDecodeB() {

        // The true parameter in the Player Book constructor signifies a test.
        PlayerDao noteDao = new PlayerDao(true);
        Player newNote = new Player();

        // Create the file in the Dao.
        Player returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);

        String firstName = "First and last name here";
        int playerNumber = 7;

        // Set some text in the note file.
        //returnedNote.setNoteString("This Is a test note.");
        returnedNote.setPlayerName(firstName);
        returnedNote.setPlayerNumber(playerNumber);
        int playerId = returnedNote.getId();
        
        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        PlayerDao secondDao = new PlayerDao(true);

        // Pull a note  using the id number recorded earlier.
        Player thirdNote = secondDao.get(id);

        assertTrue(thirdNote != null);
        
        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
        assertEquals(firstName, thirdNote.getPlayerName());
        assertEquals(playerNumber, thirdNote.getPlayerNumber());
        assertEquals(playerId, thirdNote.getId());
        
        
        // Delete the test note.
        secondDao.delete(thirdNote);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        PlayerDao thirdDao = new PlayerDao(true);
        assertEquals(thirdDao.get(id), null);

    }

    
    
}
