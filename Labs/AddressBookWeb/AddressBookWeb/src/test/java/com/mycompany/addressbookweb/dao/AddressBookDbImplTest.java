/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.dao;

import com.thesoftwareguild.interfaces.dao.AddressBookDao;
import com.thesoftwareguild.interfaces.dto.Address;
import java.util.List;
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
public class AddressBookDbImplTest {

    ApplicationContext ctx;

    //AddressBookDao addressDao = ctx.getBean("addressDao", AddressBookDao.class);
    public AddressBookDbImplTest() {
        ctx = new ClassPathXmlApplicationContext("testDb-applicationContext.xml");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class AddressBookDao.
     */
    @Test
    public void testCreate() {
        System.out.println("create");

        Address address = addressFactory();
        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);
        Address expResult = address;
        //Address result = instance.create(address);

        address.setId(null);
        instance.update(address);

        Address result = instance.get(null);

        //assertEquals(expResult, result);
        assertNull(result);

        AddressBookDao instanceTwo = ctx.getBean("addressDao", AddressBookDao.class);

        Address result2 = instance.get(null);

        assertNull(result2);

        // If it makes it to this point, it passes.
        assertTrue(true);

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testCreateB() {
        System.out.println("create");

        Address address = addressFactory();
        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);
        Address expResult = null;

        Address result = instance.get(null);

        assertEquals(expResult, result);

        // If it makes it to this point, it passes.
        assertTrue(true);

    }

    @Test
    public void testCreateC() {
        System.out.println("create");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        instance.delete(null);

        // If it makes it to this point, it passes.
        assertTrue(true);

    }

    @Test
    public void testCreateD() {
        System.out.println("create");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);
        Address expResult = null;

        Address result = instance.get(null);

        assertEquals(expResult, result);

        // If it makes it to this point, it passes.
        assertTrue(true);

    }

    @Test
    public void testCreateE() {
        System.out.println("create");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);
        Address expResult = null;

        Address result = instance.create(null);

        assertEquals(expResult, result);

        // If it makes it to this point, it passes.
        assertTrue(true);

    }

    @Test
    public void testCreateF() {
        System.out.println("create");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        instance.update(null);

        // If it makes it to this point, it passes.
        assertTrue(true);

    }

//
//    /**
//     * Test of create method, of class AddressBookDao.
//     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");
//        Address address = null;
//        AddressBookDao instance = null;
//        Address expResult = null;
//        Address result = instance.create(address);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get method, of class AddressBookDao.
//     */
//    @Test
//    public void testGet() {
//        System.out.println("get");
//        Integer id = null;
//        AddressBookDao instance = null;
//        Address expResult = null;
//        Address result = instance.get(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of update method, of class AddressBookDao.
//     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        Address address = null;
//        AddressBookDao instance = null;
//        instance.update(address);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete method, of class AddressBookDao.
//     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        Integer id = null;
//        AddressBookDao instance = null;
//        instance.delete(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of list method, of class AddressBookDao.
//     */
//    @Test
//    public void testList() {
//        System.out.println("list");
//        AddressBookDao instance = null;
//        List<Address> expResult = null;
//        List<Address> result = instance.list();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of searchByLastName method, of class AddressBookDao.
     */
    @Test
    public void testSearchByLastNameFlawed() {
        System.out.println("searchByLastName");
        String lastName = "DannnyFakeLastNameGuy";
        Address address = new Address();
        address.setLastName(lastName);

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        try {
            instance.create(address);
            fail("It was not supposed to get here.");
        } catch (org.springframework.dao.DataIntegrityViolationException integrityException) {

        }
    }

    /**
     * Test of searchByLastName method, of class AddressBookDao.
     */
    @Test
    public void testSearchByLastName() {
        System.out.println("searchByLastName");
        String lastName = "DannnyFakeLastNameGuy";
        Address address = new Address();
        address.setLastName(lastName);

        address.setFirstName("first_name");
        //address.setLastName("last_name");
        address.setStreetNumber("street_number");
        address.setStreetName("street_name");
        address.setCity("city");
        address.setState("state");
        address.setZip("zip");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        instance.create(address);

        List<Address> expResult = null;
        List<Address> result = instance.searchByLastName(lastName);

        assertTrue(result.size() > 0);

        for (Address testAddress : result) {
            assertEquals(testAddress.getLastName(), lastName);
        }

        //assertEquals(expResult, result);
    }

    /**
     * Test of searchByLastName method, of class AddressBookDao.
     */
    @Test
    public void testSearchByLastNameParts() {
        System.out.println("searchByLastName");
        String lastName = "DannnyFakeLastNameGuy";
        String lastNameTest = "DannnyFakeLastNam";
        Address address = new Address();
        address.setLastName(lastName);

        address.setFirstName("first_name");
        //address.setLastName("last_name");
        address.setStreetNumber("street_number");
        address.setStreetName("street_name");
        address.setCity("city");
        address.setState("state");
        address.setZip("zip");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        instance.create(address);

        List<Address> expResult = null;
        List<Address> result = instance.searchByLastName(lastNameTest);

        assertTrue(result.size() > 0);

        for (Address testAddress : result) {
            assertTrue(testAddress.getLastName().contains(lastNameTest));
        }

        //assertEquals(expResult, result);
    }

    /**
     * Test of searchByLastName method, of class AddressBookDao.
     */
    @Test
    public void testSearchByLastNamePartsB() {
        System.out.println("searchByLastName");
        String lastName = "DannnyFakeLastNameGuy";
        String lastNameTest = "nyFakeLastNam";
        Address address = new Address();
        address.setLastName(lastName);

        address.setFirstName("first_name");
        //address.setLastName("last_name");
        address.setStreetNumber("street_number");
        address.setStreetName("street_name");
        address.setCity("city");
        address.setState("state");
        address.setZip("zip");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        instance.create(address);

        List<Address> expResult = null;
        List<Address> result = instance.searchByLastName(lastNameTest);

        assertTrue(result.size() > 0);

        for (Address testAddress : result) {
            assertTrue(testAddress.getLastName().contains(lastNameTest));
        }

        //assertEquals(expResult, result);
    }

    /**
     * Test of searchByLastName method, of class AddressBookDao.
     */
    @Test
    public void testSearchByLastNamePartsC() {
        System.out.println("searchByLastName");
        String lastName = "DannnyFakeLastNameGuy";
        String lastNameTest = "nyFakeLastNam";
        Address address = new Address();
        address.setLastName(lastName);

        address.setFirstName("first_name");
        //address.setLastName("last_name");
        address.setStreetNumber("street_number");
        address.setStreetName("street_name");
        address.setCity("city");
        address.setState("state");
        address.setZip("zip");

        Address addressB = new Address();
        addressB.setLastName("nottheanswer");

        addressB.setFirstName("first_name");
        //address.setLastName("last_name");
        addressB.setStreetNumber("street_number");
        addressB.setStreetName("street_name");
        addressB.setCity("city");
        addressB.setState("state");
        addressB.setZip("zip");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        instance.create(address);
        instance.create(addressB);

        List<Address> expResult = null;
        List<Address> result = instance.searchByLastName(lastNameTest);

        assertTrue(result.size() > 0);

        for (Address testAddress : result) {
            assertTrue(testAddress.getLastName().contains(lastNameTest));
        }

        //assertEquals(expResult, result);
    }

    /**
     * Test of searchByLastName method, of class AddressBookDao.
     */
    @Test
    public void testSearchByLastNamePartsD() {
        System.out.println("searchByLastName");
        String lastName = "DannnyFakeLastNameGuy";
        String lastNameTest = "nyFakeLastNam";
        Address address = new Address();
        address.setLastName(lastName);

        address.setFirstName("first_name");
        //address.setLastName("last_name");
        address.setStreetNumber("street_number");
        address.setStreetName("street_name");
        address.setCity("city");
        address.setState("state");
        address.setZip("zip");

        Address addressB = new Address();
        addressB.setLastName("nottheanswer");

        addressB.setFirstName("first_name");
        //address.setLastName("last_name");
        addressB.setStreetNumber("street_number");
        addressB.setStreetName("street_name");
        addressB.setCity("city");
        addressB.setState("state");
        addressB.setZip("zip");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        instance.create(address);
        instance.create(addressB);

        Address addressC = addressFactory();
        addressC.setLastName(lastName);

        instance.create(addressC);

        List<Address> expResult = null;
        List<Address> result = instance.searchByLastName(lastNameTest);

        assertTrue(result.size() > 1);

        for (Address testAddress : result) {
            assertTrue(testAddress.getLastName().contains(lastNameTest));
        }

        //assertEquals(expResult, result);
    }

    private Address addressFactory() {
        Address addressC = new Address();
        addressC.setFirstName("first_name");
        addressC.setLastName("last_name");
        addressC.setStreetNumber("street_number");
        addressC.setStreetName("street_name");
        addressC.setCity("city");
        addressC.setState("state");
        addressC.setZip("zip");
        return addressC;
    }

    /**
     * Test of searchByFirstName method, of class AddressBookDao.
     */
    @Test
    public void testSearchByFirstNameFlawed() {
        System.out.println("searchByFirstName");
        String FirstName = "DannnyFakeFirstNameGuy";
        Address address = new Address();
        address.setFirstName(FirstName);

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        try {
            instance.create(address);
            fail("It was not supposed to get here.");
        } catch (org.springframework.dao.DataIntegrityViolationException integrityException) {

        }
    }

    /**
     * Test of searchByFirstName method, of class AddressBookDao.
     */
    @Test
    public void testSearchByFirstName() {
        System.out.println("searchByFirstName");
        String FirstName = "DannnyFakeFirstNameGuy";
        Address address = new Address();
        address.setFirstName(FirstName);

        //address.setFirstName("first_name");
        address.setLastName("Last_name");
        address.setStreetNumber("street_number");
        address.setStreetName("street_name");
        address.setCity("city");
        address.setState("state");
        address.setZip("zip");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        instance.create(address);

        List<Address> expResult = null;
        List<Address> result = instance.searchByFirstName(FirstName);

        assertTrue(result.size() > 0);

        for (Address testAddress : result) {
            assertEquals(testAddress.getFirstName(), FirstName);
        }

        //assertEquals(expResult, result);
    }

    /**
     * Test of searchByFirstName method, of class AddressBookDao.
     */
    @Test
    public void testSearchByFirstNameParts() {
        System.out.println("searchByFirstName");
        String FirstName = "DannnyFakeFirstNameGuy";
        String FirstNameTest = "DannnyFakeFirstNam";
        Address address = new Address();
        address.setFirstName(FirstName);

        //address.setFirstName("first_name");
        address.setLastName("Last_name");
        address.setStreetNumber("street_number");
        address.setStreetName("street_name");
        address.setCity("city");
        address.setState("state");
        address.setZip("zip");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        instance.create(address);

        List<Address> expResult = null;
        List<Address> result = instance.searchByFirstName(FirstNameTest);

        assertTrue(result.size() > 0);

        for (Address testAddress : result) {
            assertTrue(testAddress.getFirstName().contains(FirstNameTest));
        }

        //assertEquals(expResult, result);
    }

    /**
     * Test of searchByFirstName method, of class AddressBookDao.
     */
    @Test
    public void testSearchByFirstNamePartsB() {
        System.out.println("searchByFirstName");
        String FirstName = "DannnyFakeFirstNameGuy";
        String FirstNameTest = "nyFakeFirstNam";
        Address address = new Address();
        address.setFirstName(FirstName);

        //address.setFirstName("first_name");
        address.setLastName("Last_name");
        address.setStreetNumber("street_number");
        address.setStreetName("street_name");
        address.setCity("city");
        address.setState("state");
        address.setZip("zip");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        instance.create(address);

        List<Address> expResult = null;
        List<Address> result = instance.searchByFirstName(FirstNameTest);

        assertTrue(result.size() > 0);

        for (Address testAddress : result) {
            assertTrue(testAddress.getFirstName().contains(FirstNameTest));
        }

        //assertEquals(expResult, result);
    }

    /**
     * Test of searchByFirstName method, of class AddressBookDao.
     */
    @Test
    public void testSearchByFirstNamePartsC() {
        System.out.println("searchByFirstName");
        String FirstName = "DannnyFakeFirstNameGuy";
        String FirstNameTest = "nyFakeFirstNam";
        Address address = new Address();
        address.setFirstName(FirstName);

        //address.setFirstName("first_name");
        address.setLastName("Last_name");
        address.setStreetNumber("street_number");
        address.setStreetName("street_name");
        address.setCity("city");
        address.setState("state");
        address.setZip("zip");

        Address addressB = new Address();
        addressB.setFirstName("nottheanswer");

        //addressB.setFirstName("first_name");
        addressB.setLastName("Last_name");
        addressB.setStreetNumber("street_number");
        addressB.setStreetName("street_name");
        addressB.setCity("city");
        addressB.setState("state");
        addressB.setZip("zip");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        instance.create(address);
        instance.create(addressB);

        List<Address> expResult = null;
        List<Address> result = instance.searchByFirstName(FirstNameTest);

        assertTrue(result.size() > 0);

        for (Address testAddress : result) {
            assertTrue(testAddress.getFirstName().contains(FirstNameTest));
        }

        //assertEquals(expResult, result);
    }

    /**
     * Test of searchByFirstName method, of class AddressBookDao.
     */
    @Test
    public void testSearchByFirstNamePartsD() {
        System.out.println("searchByFirstName");
        String FirstName = "DannnyFakeFirstNameGuy";
        String FirstNameTest = "nyFakeFirstNam";
        Address address = new Address();
        address.setFirstName(FirstName);

        //address.setFirstName("first_name");
        address.setLastName("Last_name");
        address.setStreetNumber("street_number");
        address.setStreetName("street_name");
        address.setCity("city");
        address.setState("state");
        address.setZip("zip");

        Address addressB = new Address();
        addressB.setFirstName("nottheanswer");

        //addressB.setFirstName("first_name");
        addressB.setLastName("Last_name");
        addressB.setStreetNumber("street_number");
        addressB.setStreetName("street_name");
        addressB.setCity("city");
        addressB.setState("state");
        addressB.setZip("zip");

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        instance.create(address);
        instance.create(addressB);

        Address addressC = new Address();
        addressC.setFirstName(FirstName);

        //addressC.setFirstName("first_name");
        addressC.setLastName("Last_name");
        addressC.setStreetNumber("street_number");
        addressC.setStreetName("street_name");
        addressC.setCity("city");
        addressC.setState("state");
        addressC.setZip("zip");
        instance.create(addressC);

        List<Address> expResult = null;
        List<Address> result = instance.searchByFirstName(FirstNameTest);

        assertTrue(result.size() > 1);

        for (Address testAddress : result) {
            assertTrue(testAddress.getFirstName().contains(FirstNameTest));
        }

        //assertEquals(expResult, result);
    }

    @Test
    public void testCreatez() {
        System.out.println("create");
        Address address = addressFactory();
        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);
        Address expResult = address;
        Address result = instance.create(address);
        //assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.list().size());

        // Test get method.
        Address returnedAddress = instance.get(id);
        assertEquals(returnedAddress.getId(), result.getId());
        instance.delete(address.getId());

        returnedAddress = instance.get(id);
        assertEquals(returnedAddress, null);
    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testGetAllAddresses() {
        System.out.println("getAllAddresses");
        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        String lastName = "test last name";
        int testNumber = 25;
        List<Address> addressList = new java.util.ArrayList();

        for (int x = 0; x < testNumber; x++) {

            Address address = addressFactory();
            address.setLastName(lastName);
            instance.create(address);
            addressList.add(address);

        }

        List<Address> result = instance.searchByLastName(lastName);
        assertEquals(false, result.isEmpty());
        assertEquals(testNumber, result.size());

        for (Address address : addressList) {
            instance.delete(address.getId());

        }

    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testGetList2() {

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        String lastName = "Highly unlikely and very long test last name string.";
        List<Address> result = instance.searchByLastName(lastName);
        assertEquals(true, result.isEmpty());

    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testSearchByCity() {
        System.out.println("getAllAddresses");
        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        String city = "test City name";
        int testNumber = 25;
        List<Address> addressList = new java.util.ArrayList();

        for (int x = 0; x < testNumber; x++) {

            Address address = addressFactory();
            address.setCity(city);
            instance.create(address);
            addressList.add(address);

        }

        List<Address> result = instance.searchByCity(city);
        assertEquals(false, result.isEmpty());
        assertEquals(testNumber, result.size());

        for (Address address : addressList) {
            instance.delete(address.getId());

        }

    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testSearchByCity2() {

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        String city = "Highly unlikely and very long test City name string.";
        List<Address> result = instance.searchByCity(city);
        assertEquals(true, result.isEmpty());

    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testSearchByState() {
        System.out.println("getAllAddresses");
        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        String state = "test State name";
        int testNumber = 25;
        List<Address> addressList = new java.util.ArrayList();

        for (int x = 0; x < testNumber; x++) {

            Address address = addressFactory();
            address.setState(state);
            instance.create(address);
            addressList.add(address);

        }

        List<Address> result = instance.searchByState(state);
        assertEquals(false, result.isEmpty());
        assertEquals(testNumber, result.size());

        for (Address address : addressList) {
            instance.delete(address.getId());

        }

    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testSearchByState2() {

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        String state = "Highly unlikely and very long test State name string.";
        List<Address> result = instance.searchByState(state);
        assertEquals(true, result.isEmpty());

    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testSearchByState3() {

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        String state = null;
        List<Address> result = instance.searchByState(state);
        assertEquals(true, result.isEmpty());

    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testSearchByZip() {
        System.out.println("getAllAddresses");
        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        String zip = "test Zip name";
        int testNumber = 25;
        List<Address> addressList = new java.util.ArrayList();

        for (int x = 0; x < testNumber; x++) {

            Address address = addressFactory();
            address.setZip(zip);
            instance.create(address);
            addressList.add(address);

        }

        List<Address> result = instance.searchByZip(zip);
        assertEquals(false, result.isEmpty());
        assertEquals(testNumber, result.size());

        for (Address address : addressList) {
            instance.delete(address.getId());

        }

    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testSearchByZip2() {

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        String zip = "Highly unlikely and very long test Zip name string.";
        List<Address> result = instance.searchByZip(zip);
        assertEquals(true, result.isEmpty());

    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testSearchByZip3() {

        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);

        String zip = null;
        List<Address> result = instance.searchByZip(zip);
        assertEquals(true, result.isEmpty());

    }

    /**
     * Test of create method, of class AddressBook.
     */
    @Test
    public void testCreateV() {
        System.out.println("create");
        Address address = addressFactory();
        AddressBookDao instance = ctx.getBean("addressDao", AddressBookDao.class);
        Address expResult = address;
        Address result = instance.create(address);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.list().size());

        // Test get method.
        Address returnedAddress = instance.get(id);
        assertEquals(returnedAddress.getId(), result.getId());
        instance.delete(address.getId());

        returnedAddress = instance.get(id);
        assertEquals(returnedAddress, null);
    }

//    /**
//     * Test of searchByFirstName method, of class AddressBookDao.
//     */
//    @Test
//    public void testSearchByFirstName() {
//        System.out.println("searchByFirstName");
//        String firstName = "";
//        AddressBookDao instance = null;
//        List<Address> expResult = null;
//        List<Address> result = instance.searchByFirstName(firstName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchByCity method, of class AddressBookDao.
//     */
//    @Test
//    public void testSearchByCity() {
//        System.out.println("searchByCity");
//        String city = "";
//        AddressBookDao instance = null;
//        List<Address> expResult = null;
//        List<Address> result = instance.searchByCity(city);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchByState method, of class AddressBookDao.
//     */
//    @Test
//    public void testSearchByState() {
//        System.out.println("searchByState");
//        String state = "";
//        AddressBookDao instance = null;
//        List<Address> expResult = null;
//        List<Address> result = instance.searchByState(state);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchByZip method, of class AddressBookDao.
//     */
//    @Test
//    public void testSearchByZip() {
//        System.out.println("searchByZip");
//        String zipcode = "";
//        AddressBookDao instance = null;
//        List<Address> expResult = null;
//        List<Address> result = instance.searchByZip(zipcode);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of fixNull method, of class AddressBookDao.
//     */
//    @Test
//    public void testFixNull() {
//        System.out.println("fixNull");
//        String input = "";
//        AddressBookDao instance = null;
//        String expResult = "";
//        String result = instance.fixNull(input);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
