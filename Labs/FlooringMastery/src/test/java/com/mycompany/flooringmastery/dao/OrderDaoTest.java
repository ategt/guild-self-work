/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class OrderDaoTest {
    
        java.io.File testFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/OrdersTestData.txt");

    
    public OrderDaoTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    
    
        @Test
    public void testCreate() {
        System.out.println("create");
        
        ProductDao productDao = new ProductDao(true);
        StateDao stateDao = new StateDao(true);
        OrderDao instance = new OrderDao(productDao, stateDao, true);

        Order order = new Order();
        Order expResult = order;
        Order result = instance.create(order);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Order returnedOrder = instance.get(id);
        assertEquals(returnedOrder, result);
        instance.delete(order);

        returnedOrder = instance.get(id);
        assertEquals(returnedOrder, null);
    }

    /**
     * Test of getAllOrderes method, of class OrderDao.
     */
    @Test
    public void testGetAllOrderes() {
        System.out.println("getAllOrderes");

        java.io.File tempFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/OrdersTestData.txt");
        testFile.renameTo(tempFile);
        
        ProductDao productDao = new ProductDao(true);
        StateDao stateDao = new StateDao(true);
        OrderDao instance = new OrderDao(productDao, stateDao, true);

        Order orderOne = new Order();
        Order orderTwo = new Order();
        Order orderThree = new Order();

        Date date = new Date();
        Date secondDate = new Date();
        
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyyMMdd");
        secondDate = new Date(fmt.format(secondDate));
        
        orderOne.setDate(date);
        orderOne.setDate(secondDate);
        
        
        instance.create(orderOne);
        instance.create(orderTwo);
        instance.create(orderThree);

        assertTrue(instance.getList().contains(orderOne));
        assertTrue(instance.getList().contains(orderTwo));
        assertTrue(instance.getList().contains(orderThree));

                List<Order> result = instance.searchByDate(date);

        
        
        
        
        instance.delete(orderOne);
        instance.delete(orderTwo);
        instance.delete(orderThree);

        int expSizeResult = 3;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);

        

        String lastName = "Steve";
        //List<Order> result = instance.searchByDate(date);
                //(lastName);
        assertEquals(true, result.isEmpty());

        lastName = "Jones";
        result = instance.searchByLastName(lastName);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
        
        tempFile.renameTo(testFile);
        
    }

    @Test
    public void testEncodeAndDecode() {

        // The true parameter in the Order Book constructor signifies a test.
        OrderDao noteDao = new OrderDao(true);
        Order newNote = new Order();

        // Create the file in the Dao.
        Order returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);

        String city = "Seville";
        String country = "USA";
        String type = "mailing";
        String zipCode = "88775";
        String poBox = "21";
        String firstName = "First and last name here";
        String street = "3589 Street Road";
        String state = "Michigan";

        // Set some text in the note file.
        //returnedNote.setNoteString("This Is a test note.");
        returnedNote.setCity(city);
        returnedNote.setCountry(country);
        returnedNote.setType(type);
        returnedNote.setZipcode(zipCode);
        returnedNote.setPoBox(poBox);
        returnedNote.setFirstName(firstName);
        returnedNote.setStreetOrder(street);
        returnedNote.setState(state);

        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        OrderDao secondDao = new OrderDao(true);

        // Pull a note  using the id number recorded earlier.
        Order thirdNote = secondDao.get(id);

        assertTrue(thirdNote != null);
        
        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
        assertEquals(city, thirdNote.getCity());
        assertEquals(country, thirdNote.getCountry());
        assertEquals(type, thirdNote.getType());
        assertEquals(zipCode, thirdNote.getZipcode());
        assertEquals(poBox, thirdNote.getPoBox());
        assertEquals(firstName, thirdNote.getFirstName());
        assertEquals(street, thirdNote.getStreetOrder());
        assertEquals(state, thirdNote.getState());

        // Delete the test note.
        secondDao.delete(thirdNote);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        OrderDao thirdDao = new OrderDao(true);
        assertEquals(thirdDao.get(id), null);

    }

    /**
     * Test of create method, of class OrderDao.
     */
    @Test
    public void testCreateB() {
        System.out.println("create");
        Order order = new Order();
        OrderDao instance = new OrderDao(true);
        Order expResult = order;
        Order result = instance.create(order);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Order returnedOrder = instance.get(id);
        assertEquals(returnedOrder, result);
        instance.delete(order);

        returnedOrder = instance.get(id);
        assertEquals(returnedOrder, null);
    }

    /**
     * Test of getAllOrderes method, of class OrderDao.
     */
    @Test
    public void testGetAllOrderesB() {
        System.out.println("getAllOrderes");
        OrderDao instance = new OrderDao(true);

        Order orderOne = new Order();
        Order orderTwo = new Order();
        Order orderThree = new Order();

        instance.create(orderOne);
        instance.create(orderTwo);
        instance.create(orderThree);

        assertTrue(instance.getAllOrderes().contains(orderTwo));

        instance.delete(orderOne);
        instance.delete(orderTwo);
        instance.delete(orderThree);

        int expSizeResult = 3;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);

        String lastName = "Steve";
        List<Order> result = instance.searchByLastName(lastName);
        assertEquals(true, result.isEmpty());

        lastName = "Jones";
        result = instance.searchByLastName(lastName);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void testEncodeAndDecodeB() {

        //Dvd dvd = new DvdImplementation();
        OrderDao noteDao = new OrderDao(true);
        Order newNote = new Order();

        // Create the file in the Dao.
        Order returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);

        String city = "Seville";
        String country = "USA";
        String type = "mailing";
        String zipCode = "88775";
        String poBox = "21";
        String firstName = "First and last name here";
        String street = "3589 Street Road";
        String state = "Michigan";

        // Set some text in the note file.
        //returnedNote.setNoteString("This Is a test note.");
        returnedNote.setCity(city);
        returnedNote.setCountry(country);
        returnedNote.setType(type);
        returnedNote.setZipcode(zipCode);
        returnedNote.setPoBox(poBox);
        returnedNote.setFirstName(firstName);
        returnedNote.setStreetOrder(street);
        returnedNote.setState(state);

        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        OrderDao secondDao = new OrderDao(true);

        // Pull a note  using the id number recorded earlier.
        Order thirdNote = secondDao.get(id);

        assertTrue(thirdNote != null);
        
        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
        assertEquals(city, thirdNote.getCity());
        assertEquals(country, thirdNote.getCountry());
        assertEquals(type, thirdNote.getType());
        assertEquals(zipCode, thirdNote.getZipcode());
        assertEquals(poBox, thirdNote.getPoBox());
        assertEquals(firstName, thirdNote.getFirstName());
        assertEquals(street, thirdNote.getStreetOrder());
        assertEquals(state, thirdNote.getState());

        // Delete the test note.
        secondDao.delete(thirdNote);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        OrderDao thirdDao = new OrderDao(true);
        assertEquals(thirdDao.get(id), null);

    }
    /**
     * Test of create method, of class OrderDao.
     */
    @Test
    public void testCreateC() {
        System.out.println("create");
        Order order = new Order();
        OrderDao instance = new OrderDao(true);
        Order expResult = order;
        Order result = instance.create(order);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Order returnedOrder = instance.get(id);
        assertEquals(returnedOrder, result);
        instance.delete(order);

        returnedOrder = instance.get(id);
        assertEquals(returnedOrder, null);
    }

    /**
     * Test of getAllOrderes method, of class OrderDao.
     */
    @Test
    public void testGetAllOrderesC() {
        System.out.println("getAllOrderes");
        OrderDao instance = new OrderDao(true);

        Order orderOne = new Order();
        Order orderTwo = new Order();
        Order orderThree = new Order();

        instance.create(orderOne);
        instance.create(orderTwo);
        instance.create(orderThree);

        assertTrue(instance.getAllOrderes().contains(orderTwo));

        instance.delete(orderOne);
        instance.delete(orderTwo);
        instance.delete(orderThree);

        int expSizeResult = 3;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);

        String lastName = "Steve";
        List<Order> result = instance.searchByLastName(lastName);
        assertEquals(true, result.isEmpty());

        lastName = "Jones";
        result = instance.searchByLastName(lastName);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void testEncodeAndDecodeC() {

        //Dvd dvd = new DvdImplementation();
        OrderDao noteDao = new OrderDao(true);
        Order newNote = new Order();

        // Create the file in the Dao.
        Order returnedNote = noteDao.create(newNote);

        // Record the notes id number.
        int id = newNote.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(newNote, returnedNote);

        String city = "Seville";
        String country = "USA";
        String type = "mailing";
        String zipCode = "88775";
        String poBox = "21";
        String firstName = "First and last name here";
        String street = "3589 Street Road";
        String state = "Michigan";

        // Set some text in the note file.
        //returnedNote.setNoteString("This Is a test note.");
        returnedNote.setCity(city);
        returnedNote.setCountry(country);
        returnedNote.setType(type);
        returnedNote.setZipcode(zipCode);
        returnedNote.setPoBox(poBox);
        returnedNote.setFirstName(firstName);
        returnedNote.setStreetOrder(street);
        returnedNote.setState(state);

        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        OrderDao secondDao = new OrderDao(true);

        // Pull a note  using the id number recorded earlier.
        Order thirdNote = secondDao.get(id);

        assertTrue(thirdNote != null);
        
        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
        assertEquals(city, thirdNote.getCity());
        assertEquals(country, thirdNote.getCountry());
        assertEquals(type, thirdNote.getType());
        assertEquals(zipCode, thirdNote.getZipcode());
        assertEquals(poBox, thirdNote.getPoBox());
        assertEquals(firstName, thirdNote.getFirstName());
        assertEquals(street, thirdNote.getStreetOrder());
        assertEquals(state, thirdNote.getState());

        // Delete the test note.
        secondDao.delete(thirdNote);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        OrderDao thirdDao = new OrderDao(true);
        assertEquals(thirdDao.get(id), null);

    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    /**
//     * Test of create method, of class OrderDao.
//     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");
//        Order order = null;
//        OrderDao instance = null;
//        Order expResult = null;
//        Order result = instance.create(order);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get method, of class OrderDao.
//     */
//    @Test
//    public void testGet() {
//        System.out.println("get");
//        Integer id = null;
//        OrderDao instance = null;
//        Order expResult = null;
//        Order result = instance.get(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of update method, of class OrderDao.
//     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        Order order = null;
//        OrderDao instance = null;
//        instance.update(order);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete method, of class OrderDao.
//     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        Order order = null;
//        OrderDao instance = null;
//        instance.delete(order);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getList method, of class OrderDao.
//     */
//    @Test
//    public void testGetList() {
//        System.out.println("getList");
//        OrderDao instance = null;
//        List<Order> expResult = null;
//        List<Order> result = instance.getList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of size method, of class OrderDao.
//     */
//    @Test
//    public void testSize() {
//        System.out.println("size");
//        OrderDao instance = null;
//        int expResult = 0;
//        int result = instance.size();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchByDate method, of class OrderDao.
//     */
//    @Test
//    public void testSearchByDate() {
//        System.out.println("searchByDate");
//        Date date = null;
//        OrderDao instance = null;
//        List<Order> expResult = null;
//        List<Order> result = instance.searchByDate(date);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isSameDay method, of class OrderDao.
//     */
//    @Test
//    public void testIsSameDay() {
//        System.out.println("isSameDay");
//        Date date1 = null;
//        Date date2 = null;
//        boolean expResult = false;
//        boolean result = OrderDao.isSameDay(date1, date2);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
