
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbook.dao;

//import com.mycompany.addressbook.dto.Address;
import com.thesoftwareguild.interfaces.dao.AddressBookDao;
import com.thesoftwareguild.interfaces.dto.Address;
import java.util.List;
import java.util.Map;
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
public class AddressBookLambdaImplTest {

    ApplicationContext ctx;

    public AddressBookLambdaImplTest() {
        ctx = new ClassPathXmlApplicationContext("lambdaApplicationContext.xml");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    public void TestMessyLambdaMethod() {

        //AddressBookLambdaImpl mess = ctx.getBean("productionAddressDao" , AddressBookLambdaImpl.class);
        AddressBookDao mess = ctx.getBean("productionAddressDao", AddressBookLambdaImpl.class);
        //= ctx.getBean("testAddressDao" , AddressBookLambdaImpl.class);

        //java.util.Map<String, List<Address>> messyResults = mess.searchByState("OH");
//        //messyResults.forEach();
//        assertEquals(messyResults.size(), 3);
//
//        //for (String city : messyResults.keySet()) {
//
//            if (city.equalsIgnoreCase("wooster")) {
//                assertEquals(messyResults.get(city).size(), 3);
//            }
//
//            if (city.equalsIgnoreCase("Medina")) {
//                assertEquals(messyResults.get(city).size(), 1);
//            }
//
////            for ( Address address : messyResults.get(city)){
////                
////                System.out.println(address.getFirstName() + " " + address.getLastName() + "\t" + address.getCity() + "\t" + address.getState());
//        }
    }

    /**
     * Test of create method, of class AddressBook.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Address address = new Address();
        AddressBookDao instance = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);
        Address expResult = address;
        Address result = instance.create(address);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        //assertTrue(result.getId() >= instance.size());

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
        AddressBookDao instance = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);

        Address addressOne = new Address();
        Address addressTwo = new Address();
        Address addressThree = new Address();

        instance.create(addressOne);
        instance.create(addressTwo);
        instance.create(addressThree);

        assertTrue(instance.list().contains(addressTwo));

        instance.delete(addressOne.getId());
        instance.delete(addressTwo.getId());
        instance.delete(addressThree.getId());

        int expSizeResult = 3;
        int sizeResult = instance.list().size();
        assertEquals(expSizeResult, sizeResult);
        String lastName = "Steve";
        List<Address> result = instance.searchByLastName(lastName);
        assertEquals(true, result.isEmpty());

        lastName = "Jones";
        result = instance.searchByLastName(lastName);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void testEncodeAndDecode() {

        // The true parameter in the Address Book constructor signifies a test.
        AddressBookDao noteDao = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);
        Address newNote = new Address();

        // Create the file in the Dao.
        Address returnedNote = noteDao.create(newNote);

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
        String lastName = "Now with last name support!";
        String street = "3589 Street Road";
        String state = "Michigan";

        // Set some text in the note file.
        //returnedNote.setNoteString("This Is a test note.");
        returnedNote.setCity(city);
//        returnedNote.setCountry(country);
        //      returnedNote.setType(type);
        returnedNote.setZip(zipCode);
        //returnedNote.setPoBox(poBox);
        //returnedNote.setStreetName(poBox);
        returnedNote.setFirstName(firstName);
        returnedNote.setLastName(lastName);
        returnedNote.setStreetName(street);
        returnedNote.setStreetNumber(street);
        returnedNote.setState(state);

        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        AddressBookDao secondDao = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);

        // Pull a note  using the id number recorded earlier.
        Address thirdNote = secondDao.get(id);

        assertTrue(thirdNote != null);

        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
        assertEquals(city, thirdNote.getCity());
        //assertEquals(country, thirdNote.getCountry());
        //assertEquals(type, thirdNote.getType());
        assertEquals(zipCode, thirdNote.getZip());
        //assertEquals(poBox, thirdNote.getPoBox());
        assertEquals(firstName, thirdNote.getFirstName());
        assertEquals(street, thirdNote.getStreetName());
        assertEquals(street, thirdNote.getStreetNumber());
        assertEquals(state, thirdNote.getState());

        // Delete the test note.
        secondDao.delete(thirdNote.getId());

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        AddressBookDao thirdDao = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);
        assertEquals(thirdDao.get(id), null);

    }

    /**
     * Test of create method, of class AddressBook.
     */
    @Test
    public void testCreateB() {
        System.out.println("create");
        Address address = new Address();
        AddressBookDao instance = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);
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
    public void testGetAllAddressesB() {
        System.out.println("getAllAddresses");
        AddressBookDao instance = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);

        Address addressOne = new Address();
        Address addressTwo = new Address();
        Address addressThree = new Address();

        instance.create(addressOne);
        instance.create(addressTwo);
        instance.create(addressThree);

        assertTrue(instance.list().contains(addressTwo));

        instance.delete(addressOne.getId());
        instance.delete(addressTwo.getId());
        instance.delete(addressThree.getId());

        int expSizeResult = 3;
        int sizeResult = instance.list().size();
        assertEquals(expSizeResult, sizeResult);

        String lastName = "Steve";
        List<Address> result = instance.searchByLastName(lastName);
        assertEquals(true, result.isEmpty());

        lastName = "Jones";
        result = instance.searchByLastName(lastName);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void testEncodeAndDecodeB() {

        //Dvd dvd = new DvdImplementation();
        AddressBookDao noteDao = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);
        Address newNote = new Address();

        // Create the file in the Dao.
        Address returnedNote = noteDao.create(newNote);

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
//        returnedNote.setCity(city);
//        returnedNote.setCountry(country);
//        returnedNote.setType(type);
//        returnedNote.setZipcode(zipCode);
//        returnedNote.setPoBox(poBox);
//        returnedNote.setFirstName(firstName);
//        returnedNote.setStreetAddress(street);
//        returnedNote.setState(state);
        String lastName = "Now With Last Names!";
        returnedNote.setCity(city);
//        returnedNote.setCountry(country);
        //      returnedNote.setType(type);
        returnedNote.setZip(zipCode);
        //returnedNote.setPoBox(poBox);
        //returnedNote.setStreetName(poBox);
        returnedNote.setFirstName(firstName);
        returnedNote.setLastName(lastName);
        returnedNote.setStreetName(street);
        returnedNote.setStreetNumber(street);
        returnedNote.setState(state);

// Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        AddressBookDao secondDao = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);

        // Pull a note  using the id number recorded earlier.
        Address thirdNote = secondDao.get(id);

        assertTrue(thirdNote != null);

        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
//        assertEquals(city, thirdNote.getCity());
//        assertEquals(country, thirdNote.getCountry());
//        assertEquals(type, thirdNote.getType());
//        assertEquals(zipCode, thirdNote.getZipcode());
//        assertEquals(poBox, thirdNote.getPoBox());
//        assertEquals(firstName, thirdNote.getFirstName());
//        assertEquals(street, thirdNote.getStreetAddress());
//        assertEquals(state, thirdNote.getState());




 // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
        assertEquals(city, thirdNote.getCity());
        //assertEquals(country, thirdNote.getCountry());
        //assertEquals(type, thirdNote.getType());
        assertEquals(zipCode, thirdNote.getZip());
        //assertEquals(poBox, thirdNote.getPoBox());
        assertEquals(firstName, thirdNote.getFirstName());
        assertEquals(street, thirdNote.getStreetName());
        assertEquals(street, thirdNote.getStreetNumber());
        assertEquals(state, thirdNote.getState());



// Delete the test note.
        secondDao.delete(thirdNote.getId());

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        AddressBookDao thirdDao = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);
        assertEquals(thirdDao.get(id), null);

    }

    /**
     * Test of create method, of class AddressBook.
     */
    @Test
    public void testCreateC() {
        System.out.println("create");
        Address address = new Address();
        AddressBookDao instance = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);
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
    public void testGetAllAddressesC() {
        System.out.println("getAllAddresses");
        AddressBookDao instance = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);

        Address addressOne = new Address();
        Address addressTwo = new Address();
        Address addressThree = new Address();

        instance.create(addressOne);
        instance.create(addressTwo);
        instance.create(addressThree);

        assertTrue(instance.list().contains(addressTwo));

        instance.delete(addressOne.getId());
        instance.delete(addressTwo.getId());
        instance.delete(addressThree.getId());

        int expSizeResult = 3;
        int sizeResult = instance.list().size();
        assertEquals(expSizeResult, sizeResult);

        String lastName = "Steve";
        List<Address> result = instance.searchByLastName(lastName);
        assertEquals(true, result.isEmpty());

        lastName = "Jones";
        result = instance.searchByLastName(lastName);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void testEncodeAndDecodeC() {

        //Dvd dvd = new DvdImplementation();
        AddressBookDao noteDao = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);
        Address newNote = new Address();

        // Create the file in the Dao.
        Address returnedNote = noteDao.create(newNote);

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
//        returnedNote.setCity(city);
//        returnedNote.setCountry(country);
//        returnedNote.setType(type);
//        returnedNote.setZipcode(zipCode);
//        returnedNote.setPoBox(poBox);
//        returnedNote.setFirstName(firstName);
//        returnedNote.setStreetAddress(street);
//        returnedNote.setState(state);

    String lastName = "Now With Last Names!";
        returnedNote.setCity(city);
//        returnedNote.setCountry(country);
        //      returnedNote.setType(type);
        returnedNote.setZip(zipCode);
        //returnedNote.setPoBox(poBox);
        //returnedNote.setStreetName(poBox);
        returnedNote.setFirstName(firstName);
        returnedNote.setLastName(lastName);
        returnedNote.setStreetName(street);
        returnedNote.setStreetNumber(street);
        returnedNote.setState(state);

        // Use the update method to save this new text to file.
        noteDao.update(newNote);

        // Load a new instance of the NoteDao.
        AddressBookDao secondDao = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);

        // Pull a note  using the id number recorded earlier.
        Address thirdNote = secondDao.get(id);

        assertTrue(thirdNote != null);

        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
//        assertEquals(city, thirdNote.getCity());
//        assertEquals(country, thirdNote.getCountry());
//        assertEquals(type, thirdNote.getType());
//        assertEquals(zipCode, thirdNote.getZipcode());
//        assertEquals(poBox, thirdNote.getPoBox());
//        assertEquals(firstName, thirdNote.getFirstName());
//        assertEquals(street, thirdNote.getStreetAddress());
//        assertEquals(state, thirdNote.getState());


 // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdNote.getNoteString());
        assertEquals(city, thirdNote.getCity());
        //assertEquals(country, thirdNote.getCountry());
        //assertEquals(type, thirdNote.getType());
        assertEquals(zipCode, thirdNote.getZip());
        //assertEquals(poBox, thirdNote.getPoBox());
        assertEquals(firstName, thirdNote.getFirstName());
        assertEquals(street, thirdNote.getStreetName());
        assertEquals(street, thirdNote.getStreetNumber());
        assertEquals(state, thirdNote.getState());

        // Delete the test note.
        secondDao.delete(thirdNote.getId());

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        AddressBookDao thirdDao = ctx.getBean("testAddressDao", AddressBookLambdaImpl.class);
        assertEquals(thirdDao.get(id), null);

    }

    @Test
    public void testTheSearchByCity() {
        //Dvd dvd = new DvdImplementation();
        AddressBookDao noteDao = ctx.getBean("productionAddressDao", AddressBookLambdaImpl.class);
        Address newNote = new Address();

        assertEquals(noteDao.searchByCity("wooster").size(), 4);

    }

    @Test
    public void testTheSearchByZipcode() {
        //Dvd dvd = new DvdImplementation();
        AddressBookDao noteDao = ctx.getBean("productionAddressDao", AddressBookLambdaImpl.class);
        Address newNote = new Address();

        assertEquals(noteDao.searchByZip("44287").size(), 3);

    }

    @Test
    public void testTheSearchByState() {
        //Dvd dvd = new DvdImplementation();
        AddressBookDao noteDao = ctx.getBean("productionAddressDao", AddressBookLambdaImpl.class);
        Address newNote = new Address();

        //List<List<Address>> mess = noteDao.searchByState("OH");
        //Map<String /* City */, List<Address>> mess = noteDao.searchByState("OH");
        List<Address> mess = noteDao.searchByState("OH");
        assertEquals(mess.size(), 6);

        // System.out.println(mess);
    }

}
