/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contactlist.dao;

import com.mycompany.contactlist.dto.Contact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class ContactDaoImplTest {
    
    public ContactDaoImplTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

//    /**
//     * Test of add method, of class ContactDaoImpl.
//     */
//    @Test
//    public void testAdd() {
//        System.out.println("add");
//        Contact contact = null;
//        ContactDaoImpl instance = new ContactDaoImpl();
//        Contact expResult = null;
//        Contact result = instance.add(contact);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of update method, of class ContactDaoImpl.
//     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        Contact contact = null;
//        ContactDaoImpl instance = new ContactDaoImpl();
//        instance.update(contact);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of remove method, of class ContactDaoImpl.
//     */
//    @Test
//    public void testRemove() {
//        System.out.println("remove");
//        Contact contact = null;
//        ContactDaoImpl instance = new ContactDaoImpl();
//        instance.remove(contact);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of get method, of class ContactDaoImpl.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Integer id = -7;
        ContactDaoImpl instance = new ContactDaoImpl();
        Contact expResult = null;
        Contact result = instance.get(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class ContactDaoImpl.
     */
    @Test
    public void testGetB() {
        System.out.println("get");
        Integer id = 0;
        ContactDaoImpl instance = new ContactDaoImpl();
        Contact expResult = null;
        Contact result = instance.get(id);
        assertEquals(expResult, result);
    }


//    /**
//     * Test of get method, of class ContactDaoImpl.
//     */
//    @Test
//    public void testGetC() {
//        System.out.println("get");
//        Integer id = 1;
//        ContactDaoImpl instance = new ContactDaoImpl();
//        Contact expResult = null;
//        Contact result = instance.get(id);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of list method, of class ContactDaoImpl.
//     */
//    @Test
//    public void testList() {
//        System.out.println("list");
//        ContactDaoImpl instance = new ContactDaoImpl();
//        List<Contact> expResult = null;
//        List<Contact> result = instance.list();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
