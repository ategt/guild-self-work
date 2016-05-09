/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseballleague.dao;

import com.mycompany.baseballleague.dto.Team;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class TeamDaoTest {

    public TeamDaoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class TeamDao.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Team address = new Team();
        PlayerDao playerDao = new PlayerDao(true);
        TeamDao instance = new TeamDao(playerDao, true);
        Team expResult = address;
        Team result = instance.create(address);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Team returnedTeam = instance.get(id);
        assertEquals(returnedTeam, result);
        instance.delete(address);

        returnedTeam = instance.get(id);
        assertEquals(returnedTeam, null);
    }

    /**
     * Test of getAllTeames method, of class TeamDao.
     */
    @Test
    public void testGetAllTeames() {
        System.out.println("getAllTeames");
        PlayerDao playerDao = new PlayerDao(true);
        TeamDao instance = new TeamDao(playerDao, true);

        Team addressOne = new Team();
        Team addressTwo = new Team();
        Team addressThree = new Team();

        instance.create(addressOne);
        instance.create(addressTwo);
        instance.create(addressThree);

        assertTrue(instance.getLeague().contains(addressOne));
        assertTrue(instance.getLeague().contains(addressTwo));
        assertTrue(instance.getLeague().contains(addressThree));

        instance.delete(addressOne);
        instance.delete(addressTwo);
        instance.delete(addressThree);

        int expSizeResult = 13;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);

    }

    @Test
    public void testEncodeAndDecode() {

        // The true parameter in the Team Book constructor signifies a test.
        PlayerDao playerDao = new PlayerDao(true);
        TeamDao noteDao = new TeamDao(playerDao, true);

        //TeamDao noteDao = new TeamDao(true);
        Team newNote = new Team();

        // Create the file in the Dao.
        Team returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);

        String firstName = "First and last name here";
        
        // Set some text in the note file.
        returnedNote.setTeamName(firstName);
        int playerId = returnedNote.getId();

        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        PlayerDao secondPlayerDao = new PlayerDao(true);

        TeamDao secondDao = new TeamDao(secondPlayerDao, true);

        // Pull a note  using the id number recorded earlier.
        Team thirdNote = secondDao.get(id);

        assertTrue(thirdNote != null);

        // Check that the update method saved the new text.
        assertEquals(firstName, thirdNote.getTeamName());
        assertEquals(playerId, thirdNote.getId());

        // Delete the test note.
        secondDao.delete(thirdNote);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        PlayerDao thirdPlayerDao = new PlayerDao(true);
        TeamDao thirdDao = new TeamDao(thirdPlayerDao, true);
        assertEquals(thirdDao.get(id), null);

    }

}
