/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contactlist.dao;

import com.mycompany.contactlist.dto.Contact;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class ContactDaoDbImplTest {
    
    public ContactDaoDbImplTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class ContactDaoDbImpl.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Contact contact = null;
        ContactDaoDbImpl instance = null;
        Contact expResult = null;
        Contact result = instance.add(contact);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ContactDaoDbImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Contact contact = null;
        ContactDaoDbImpl instance = null;
        instance.update(contact);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ContactDaoDbImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Contact contact = null;
        ContactDaoDbImpl instance = null;
        instance.remove(contact);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class ContactDaoDbImpl.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Integer id = null;
        ContactDaoDbImpl instance = null;
        Contact expResult = null;
        Contact result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class ContactDaoDbImpl.
     */
    @Test
    public void testList() {
        System.out.println("list");
        ContactDaoDbImpl instance = null;
        List<Contact> expResult = null;
        List<Contact> result = instance.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortByLastName method, of class ContactDaoDbImpl.
     */
    @Test
    public void testSortByLastName() {
        System.out.println("sortByLastName");
        List<Contact> contacts = null;
        ContactDaoDbImpl instance = null;
        List<Contact> expResult = null;
        List<Contact> result = instance.sortByLastName(contacts);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
