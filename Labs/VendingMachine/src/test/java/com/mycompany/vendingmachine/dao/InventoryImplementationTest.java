/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.ItemImplementation;
import com.mycompany.vendingmachine.interfaces.Inventory;
import com.mycompany.vendingmachine.interfaces.Item;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class InventoryImplementationTest {
    
    public InventoryImplementationTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class Inventory.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Item address = new ItemImplementation();
        Inventory instance = new InventoryImplementation(true);
        Item expResult = address;
        Item result = instance.create(address);
        assertEquals(expResult, result);
        
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Item returnedItem = instance.get(id);
        assertEquals(returnedItem, result);
        instance.delete(address);
        
        returnedItem = instance.get(id);
        assertEquals(returnedItem, null);
    }

    /**
     * Test of getAllItemes method, of class Inventory.
     */
    @Test
    public void testGetAllItemes() {
        System.out.println("getAllItemes");
        Inventory instance = new InventoryImplementation(true);
        
        Item addressOne = new ItemImplementation();
        Item addressTwo = new ItemImplementation();
        Item addressThree = new ItemImplementation();
        
        instance.create(addressOne);
        instance.create(addressTwo);
        instance.create(addressThree);
        
        assertTrue(instance.getList().contains(addressOne));
        assertTrue(instance.getList().contains(addressTwo));
        assertTrue(instance.getList().contains(addressThree));

        int expSizeResult = 5;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);
        
        instance.delete(addressOne);
        instance.delete(addressTwo);
        instance.delete(addressThree);
        
        expSizeResult = 2;
        sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);
        
    }
    
    @Test
    public void testEncodeAndDecode() {

        //Dvd dvd = new DvdImplementation();
        Inventory noteDao = new InventoryImplementation(true);
        Item newNote = new ItemImplementation();

        // Create the file in the Dao.
        Item returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);
        
        String itemName = "Seville Special";
        int itemCost = 1000;
        int quantityInInventory = 90;
        
        // Set some text in the note file.
        returnedNote.setItemCost(itemCost);
        returnedNote.setItemName(itemName);
        returnedNote.setQuantityInInventory(quantityInInventory);

        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        Inventory secondDao = new InventoryImplementation(true);

        // Pull a note  using the id number recorded earlier.
        Item thirdNote = secondDao.get(id);
        
        assertTrue(thirdNote != null);

        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
        assertEquals(itemCost, thirdNote.getItemCost());
        assertEquals(itemName, thirdNote.getItemName());
        assertEquals(quantityInInventory, thirdNote.getQuantityInInventory());
        
        // Delete the test note.
        secondDao.delete(thirdNote);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        Inventory thirdDao = new InventoryImplementation(true);
        assertEquals(thirdDao.get(id), null);
        
    }

    /**
     * Test of create method, of class Inventory.
     */
    @Test
    public void testCreateB() {
        System.out.println("create");
        Item address = new ItemImplementation();
        Inventory instance = new InventoryImplementation(true);
        Item expResult = address;
        Item result = instance.create(address);
        assertEquals(expResult, result);
        
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Item returnedItem = instance.get(id);
        assertEquals(returnedItem, result);
        instance.delete(address);
        
        returnedItem = instance.get(id);
        assertEquals(returnedItem, null);
    }

    /**
     * Test of create method, of class Inventory.
     */
    @Test
    public void testCreateF() {
        System.out.println("create");
        Item address = new ItemImplementation();
        Inventory instance = new InventoryImplementation(true);
        Item expResult = address;
        Item result = instance.create(address);
        assertEquals(expResult, result);
        
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Item returnedItem = instance.get(id);
        assertEquals(returnedItem, result);
        instance.delete(address);
        
        returnedItem = instance.get(id);
        assertEquals(returnedItem, null);
    }

    /**
     * Test of getAllItemes method, of class Inventory.
     */
    @Test
    public void testGetAllItemesB() {
        System.out.println("getAllItemes");
        Inventory instance = new InventoryImplementation(true);
        
        Item addressOne = new ItemImplementation();
        Item addressTwo = new ItemImplementation();
        Item addressThree = new ItemImplementation();
        
        instance.create(addressOne);
        instance.create(addressTwo);
        instance.create(addressThree);
        
        assertTrue(instance.getList().contains(addressOne));
        assertTrue(instance.getList().contains(addressTwo));
        assertTrue(instance.getList().contains(addressThree));

        int expSizeResult = 5;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);
        
        instance.delete(addressOne);
        instance.delete(addressTwo);
        instance.delete(addressThree);

        expSizeResult = 2;
        sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);
        
        
    }
    
    @Test
    public void testEncodeAndDecodeB() {

        //Dvd dvd = new DvdImplementation();
        Inventory noteDao = new InventoryImplementation(true);
        Item newNote = new ItemImplementation();

        // Create the file in the Dao.
        Item returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);
        
        String itemName = "Seville Special";
        int itemCost = 1000;
        int quantityInInventory = 90;
        
        // Set some text in the note file.
        returnedNote.setItemCost(itemCost);
        returnedNote.setItemName(itemName);
        returnedNote.setQuantityInInventory(quantityInInventory);

        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        Inventory secondDao = new InventoryImplementation(true);

        // Pull a note  using the id number recorded earlier.
        Item thirdNote = secondDao.get(id);
        
        assertTrue(thirdNote != null);

        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
        assertEquals(itemCost, thirdNote.getItemCost());
        assertEquals(itemName, thirdNote.getItemName());
        assertEquals(quantityInInventory, thirdNote.getQuantityInInventory());
        
        // Delete the test note.
        secondDao.delete(thirdNote);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        Inventory thirdDao = new InventoryImplementation(true);
        assertEquals(thirdDao.get(id), null);
        
    }

    /**
     * Test of create method, of class Inventory.
     */
    @Test
    public void testCreateC() {
        System.out.println("create");
        Item address = new ItemImplementation();
        Inventory instance = new InventoryImplementation(true);
        Item expResult = address;
        Item result = instance.create(address);
        assertEquals(expResult, result);
        
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Item returnedItem = instance.get(id);
        assertEquals(returnedItem, result);
        instance.delete(address);
        
        returnedItem = instance.get(id);
        assertEquals(returnedItem, null);
    }

    /**
     * Test of create method, of class Inventory.
     */
    @Test
    public void testCreateD() {
        System.out.println("create");
        Item address = new ItemImplementation();
        Inventory instance = new InventoryImplementation(true);
        Item expResult = address;
        Item result = instance.create(address);
        assertEquals(expResult, result);
        
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Item returnedItem = instance.get(id);
        assertEquals(returnedItem, result);
        instance.delete(address);
        
        returnedItem = instance.get(id);
        assertEquals(returnedItem, null);
    }

    /**
     * Test of getAllItemes method, of class Inventory.
     */
    @Test
    public void testGetAllItemesC() {
        System.out.println("getAllItemes");
        Inventory instance = new InventoryImplementation(true);
        
        Item addressOne = new ItemImplementation();
        Item addressTwo = new ItemImplementation();
        Item addressThree = new ItemImplementation();
        
        instance.create(addressOne);
        instance.create(addressTwo);
        instance.create(addressThree);
        
        assertTrue(instance.getList().contains(addressOne));
        assertTrue(instance.getList().contains(addressTwo));
        assertTrue(instance.getList().contains(addressThree));
        
        int expSizeResult = 5;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);
        
        instance.delete(addressOne);
        instance.delete(addressTwo);
        instance.delete(addressThree);

        expSizeResult = 2;
        sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);
    }
    
    @Test
    public void testEncodeAndDecodeC() {

        //Dvd dvd = new DvdImplementation();
        Inventory noteDao = new InventoryImplementation(true);
        Item newNote = new ItemImplementation();

        // Create the file in the Dao.
        Item returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);
        
        String itemName = "Seville Special";
        int itemCost = 1000;
        int quantityInInventory = 90;
        
        // Set some text in the note file.
        returnedNote.setItemCost(itemCost);
        returnedNote.setItemName(itemName);
        returnedNote.setQuantityInInventory(quantityInInventory);

        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        Inventory secondDao = new InventoryImplementation(true);

        // Pull a note  using the id number recorded earlier.
        Item thirdNote = secondDao.get(id);
        
        assertTrue(thirdNote != null);

        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
        assertEquals(itemCost, thirdNote.getItemCost());
        assertEquals(itemName, thirdNote.getItemName());
        assertEquals(quantityInInventory, thirdNote.getQuantityInInventory());
        
        // Delete the test note.
        secondDao.delete(thirdNote);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        Inventory thirdDao = new InventoryImplementation(true);
        assertEquals(thirdDao.get(id), null);
        
    }

    /**
     * Test of create method, of class Inventory.
     */
    @Test
    public void testCreateE() {
        System.out.println("create");
        Item address = new ItemImplementation();
        Inventory instance = new InventoryImplementation(true);
        Item expResult = address;
        Item result = instance.create(address);
        assertEquals(expResult, result);
        
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Item returnedItem = instance.get(id);
        assertEquals(returnedItem, result);
        instance.delete(address);
        
        returnedItem = instance.get(id);
        assertEquals(returnedItem, null);
    }

}
