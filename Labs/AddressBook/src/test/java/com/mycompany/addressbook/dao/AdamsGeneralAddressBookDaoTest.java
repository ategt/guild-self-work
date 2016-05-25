/*
 * I started off with a test file and most of my tests are based on it.
 * Hopefully this still works for you.  
 * A good deal got taken out.
 */
package com.mycompany.addressbook.dao;

import com.thesoftwareguild.interfaces.dao.AddressBookDao;
import com.thesoftwareguild.interfaces.dto.Address;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Adam Tegtmeier
 */
public class AdamsGeneralAddressBookDaoTest {

    ApplicationContext ctx;

    public AdamsGeneralAddressBookDaoTest() {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
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
        Address address = new Address();
        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");
        Address expResult = address;
        Address result = instance.create(address);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.list().size());

        // Test get method.
        Address returnedAddress = instance.get(id);
        assertEquals(returnedAddress, result);
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
        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");

        String lastName = "test last name";
        int testNumber = 25;
        List<Address> addressList = new java.util.ArrayList();

        for (int x = 0; x < testNumber; x++) {

            Address address = new Address();
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

        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");

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
        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");

        String city = "test City name";
        int testNumber = 25;
        List<Address> addressList = new java.util.ArrayList();

        for (int x = 0; x < testNumber; x++) {

            Address address = new Address();
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

        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");

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
        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");

        String state = "test State name";
        int testNumber = 25;
        List<Address> addressList = new java.util.ArrayList();

        for (int x = 0; x < testNumber; x++) {

            Address address = new Address();
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

        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");

        String state = "Highly unlikely and very long test State name string.";
        List<Address> result = instance.searchByState(state);
        assertEquals(true, result.isEmpty());

    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testSearchByState3() {

        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");

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
        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");

        String zip = "test Zip name";
        int testNumber = 25;
        List<Address> addressList = new java.util.ArrayList();

        for (int x = 0; x < testNumber; x++) {

            Address address = new Address();
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

        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");

        String zip = "Highly unlikely and very long test Zip name string.";
        List<Address> result = instance.searchByZip(zip);
        assertEquals(true, result.isEmpty());

    }

    /**
     * Test of getAllAddresses method, of class AddressBook.
     */
    @Test
    public void testSearchByZip3() {

        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");

        String zip = null;
        List<Address> result = instance.searchByZip(zip);
        assertEquals(true, result.isEmpty());

    }

    
    /**
     * Test of create method, of class AddressBook.
     */
    @Test
    public void testCreateB() {
        System.out.println("create");
        Address address = new Address();
        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");
        Address expResult = address;
        Address result = instance.create(address);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.list().size());

        // Test get method.
        Address returnedAddress = instance.get(id);
        assertEquals(returnedAddress, result);
        instance.delete(address.getId());

        returnedAddress = instance.get(id);
        assertEquals(returnedAddress, null);
    }


}
