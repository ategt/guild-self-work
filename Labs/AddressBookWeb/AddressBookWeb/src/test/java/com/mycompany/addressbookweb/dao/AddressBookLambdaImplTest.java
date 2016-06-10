/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.dao;

import com.thesoftwareguild.interfaces.dto.Address;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class AddressBookLambdaImplTest {

    public AddressBookLambdaImplTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class AddressBookLambdaImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("create");

        Address address = new Address();
        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
        Address expResult = address;
        //Address result = instance.create(address);

        address.setId(null);
        instance.update(address);
       
        Address result = instance.get(null);
        
        assertEquals(expResult, result);
        
        AddressBookLambdaImpl instanceTwo = new AddressBookLambdaImpl();
        
        Address result2 = instance.get(null);
        
        assertEquals(expResult, result2);
        
        // If it makes it to this point, it passes.
        assertTrue(true);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testCreateB() {
        System.out.println("create");

        Address address = new Address();
        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
        Address expResult = null;
        
        Address result = instance.get(null);
        
        assertEquals(expResult, result);
        
        // If it makes it to this point, it passes.
        assertTrue(true);
        
    }

    
    @Test
    public void testCreateC() {
        System.out.println("create");

        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
        
        instance.delete(null);
        
        // If it makes it to this point, it passes.
        assertTrue(true);
        
    }

    
    @Test
    public void testCreateD() {
        System.out.println("create");

        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
        Address expResult = null;
        
        Address result = instance.get(null);
        
        assertEquals(expResult, result);
        
        // If it makes it to this point, it passes.
        assertTrue(true);
        
    }

    @Test
    public void testCreateE() {
        System.out.println("create");

        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
        Address expResult = null;
        
        Address result = instance.create(null);
        
        assertEquals(expResult, result);
        
        // If it makes it to this point, it passes.
        assertTrue(true);
        
    }

    @Test
    public void testCreateF() {
        System.out.println("create");

        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
        
        instance.update(null);
        
        // If it makes it to this point, it passes.
        assertTrue(true);
        
    }





//
//    /**
//     * Test of get method, of class AddressBookLambdaImpl.
//     */
//    @Test
//    public void testGet() {
//        System.out.println("get");
//        Integer id = null;
//        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
//        Address expResult = null;
//        Address result = instance.get(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of update method, of class AddressBookLambdaImpl.
//     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        Address address = null;
//        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
//        instance.update(address);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete method, of class AddressBookLambdaImpl.
//     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        Integer id = null;
//        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
//        instance.delete(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of list method, of class AddressBookLambdaImpl.
//     */
//    @Test
//    public void testList() {
//        System.out.println("list");
//        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
//        List<Address> expResult = null;
//        List<Address> result = instance.list();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchByLastName method, of class AddressBookLambdaImpl.
//     */
//    @Test
//    public void testSearchByLastName() {
//        System.out.println("searchByLastName");
//        String lastName = "";
//        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
//        List<Address> expResult = null;
//        List<Address> result = instance.searchByLastName(lastName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchByFirstName method, of class AddressBookLambdaImpl.
//     */
//    @Test
//    public void testSearchByFirstName() {
//        System.out.println("searchByFirstName");
//        String firstName = "";
//        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
//        List<Address> expResult = null;
//        List<Address> result = instance.searchByFirstName(firstName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchByCity method, of class AddressBookLambdaImpl.
//     */
//    @Test
//    public void testSearchByCity() {
//        System.out.println("searchByCity");
//        String city = "";
//        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
//        List<Address> expResult = null;
//        List<Address> result = instance.searchByCity(city);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchByState method, of class AddressBookLambdaImpl.
//     */
//    @Test
//    public void testSearchByState() {
//        System.out.println("searchByState");
//        String state = "";
//        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
//        List<Address> expResult = null;
//        List<Address> result = instance.searchByState(state);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchByZip method, of class AddressBookLambdaImpl.
//     */
//    @Test
//    public void testSearchByZip() {
//        System.out.println("searchByZip");
//        String zipcode = "";
//        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
//        List<Address> expResult = null;
//        List<Address> result = instance.searchByZip(zipcode);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of fixNull method, of class AddressBookLambdaImpl.
//     */
//    @Test
//    public void testFixNull() {
//        System.out.println("fixNull");
//        String input = "";
//        AddressBookLambdaImpl instance = new AddressBookLambdaImpl();
//        String expResult = "";
//        String result = instance.fixNull(input);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
