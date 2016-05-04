/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbook.dao;

import com.mycompany.addressbook.dto.Address;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class AddressBookTest {
    
    public AddressBookTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class AddressBook.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Address address = new Address();
        AddressBook instance = new AddressBook(true);
        Address expResult = address;
        Address result = instance.create(address);
        assertEquals(expResult, result);
        
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Address returnedAddress = instance.get(id);
        assertEquals(returnedAddress, result);
        instance.delete(address);

        returnedAddress = instance.get(id);
        assertEquals(returnedAddress, null);
    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testGetAllAddresses() {
        System.out.println("getAllAddresses");
        AddressBook instance = new AddressBook(true);
        
        Address addressOne = new Address();
        Address addressTwo = new Address();
        Address addressThree = new Address();
        
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
        List<Address> result = instance.searchByLastName(lastName);
        assertEquals(true, result.isEmpty());

        lastName = "Jones";
        result = instance.searchByLastName(lastName);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
    }
    
}
