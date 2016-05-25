
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbook.dao;

////import com.mycompany.addressbook.dto.Address;
//import com.thesoftwareguild.interfaces.dao.AddressBookDao;
//import com.thesoftwareguild.interfaces.dto.Address;
//import java.util.List;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// *
// * @author apprentice
// */
public class AddressBookImplTest {
//
//    ApplicationContext ctx;
//
//    public AddressBookImplTest() {
//        ctx = new ClassPathXmlApplicationContext("forLoopApplicationContext.xml");
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
////    public void TestMessyLambdaMethod() {
////
////        //AddressBookLambdaImpl mess = ctx.getBean("productionAddressDao" , AddressBookLambdaImpl.class);
////        AddressBookDao mess = ctx.getBean("productionAddressDao", AddressBookLambdaImpl.class);
////        //= ctx.getBean("testAddressDao" , AddressBookLambdaImpl.class);
////
////        //java.util.Map<String, List<Address>> messyResults = mess.searchByState("OH");
//////        //messyResults.forEach();
//////        assertEquals(messyResults.size(), 3);
//////
//////        //for (String city : messyResults.keySet()) {
//////
//////            if (city.equalsIgnoreCase("wooster")) {
//////                assertEquals(messyResults.get(city).size(), 3);
//////            }
//////
//////            if (city.equalsIgnoreCase("Medina")) {
//////                assertEquals(messyResults.get(city).size(), 1);
//////            }
//////
////////            for ( Address address : messyResults.get(city)){
////////                
////////                System.out.println(address.getFirstName() + " " + address.getLastName() + "\t" + address.getCity() + "\t" + address.getState());
//////        }
////    }
//
//    /**
//     * Test of create method, of class AddressBook.
//     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");
//        Address address = new Address();
//        //AddressBookDao instance = () (AddressBookDao) ctx.getBean("addressDao");
//        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");
//        Address expResult = address;
//        Address result = instance.create(address);
//        assertEquals(expResult, result);
//
//        int id = result.getId();
//        assertTrue(result.getId() != 0);
//        //assertTrue(result.getId() >= instance.size());
//
//        // Test get method.
//        Address returnedAddress = instance.get(id);
//        assertEquals(returnedAddress, result);
//        instance.delete(address.getId());
//
//        returnedAddress = instance.get(id);
//        assertEquals(returnedAddress, null);
//    }
//
//    /**
//     * Test of getAllAddresses method, of class AddressBook.
//     */
//    @Test
//    public void testGetAllAddresses() {
//        System.out.println("getAllAddresses");
//        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");
//
//        Address addressOne = new Address();
//        Address addressTwo = new Address();
//        Address addressThree = new Address();
//
//        instance.create(addressOne);
//        instance.create(addressTwo);
//        instance.create(addressThree);
//
//        assertTrue(instance.list().contains(addressTwo));
//
//        instance.delete(addressOne.getId());
//        instance.delete(addressTwo.getId());
//        instance.delete(addressThree.getId());
//
//        int expSizeResult = 3;
//        int sizeResult = instance.list().size();
//        assertEquals(expSizeResult, sizeResult);
//        
////        String lastName = "::adfasdf awsfasdjfo sdjfSteve";
////        List<Address> result = instance.searchByLastName(lastName);
////        assertEquals(true, result.isEmpty());
////
////        lastName = "Jones";
////        result = instance.searchByLastName(lastName);
////        assertEquals(false, result.isEmpty());
////        assertEquals(2, result.size());
//    }
//
//    @Test
//    public void testEncodeAndDecode() {
//
//        // The true parameter in the Address Book constructor signifies a test.
//        AddressBookDao addressBookDao = (AddressBookDao) ctx.getBean("addressDao");
//        Address newNote = new Address();
//
//        // Create the file in the Dao.
//        Address returnedNote = addressBookDao.create(newNote);
//
//        // Record the notes id number.
//        int id = newNote.getId();
//
//        // Verify that the note object that the create method passed back
//        // was the same one it was given.
//        assertEquals(newNote, returnedNote);
//
//        String city = "Seville";
//        String country = "USA";
//        String type = "mailing";
//        String zipCode = "88775";
//        String poBox = "21";
//        String firstName = "First and last name here";
//        String lastName = "Now with last name support!";
//        String street = "3589 Street Road";
//        String state = "Michigan";
//
//        // Set some text in the note file.
//        //returnedNote.setNoteString("This Is a test note.");
//        returnedNote.setCity(city);
////        returnedNote.setCountry(country);
//        //      returnedNote.setType(type);
//        returnedNote.setZip(zipCode);
//        //returnedNote.setPoBox(poBox);
//        //returnedNote.setStreetName(poBox);
//        returnedNote.setFirstName(firstName);
//        returnedNote.setLastName(lastName);
//        returnedNote.setStreetName(street);
//        returnedNote.setStreetNumber(street);
//        returnedNote.setState(state);
//
//        // Use the update method to save this new text to file.
//        addressBookDao.update(newNote);
//
//        // Load a new instance of the NoteDao.
//        AddressBookDao secondDao = (AddressBookDao) ctx.getBean("addressDao");
//
//        // Pull a note  using the id number recorded earlier.
//        Address thirdAddress = secondDao.get(id);
//
//        assertTrue(thirdAddress != null);
//
//        // Check that the update method saved the new text.
//        //assertEquals("This Is a test note.", thirdAddress.getNoteString());
//        assertEquals(city, thirdAddress.getCity());
//        //assertEquals(country, thirdAddress.getCountry());
//        //assertEquals(type, thirdAddress.getType());
//        assertEquals(zipCode, thirdAddress.getZip());
//        //assertEquals(poBox, thirdAddress.getPoBox());
//        assertEquals(firstName, thirdAddress.getFirstName());
//        assertEquals(street, thirdAddress.getStreetName());
//        assertEquals(street, thirdAddress.getStreetNumber());
//        assertEquals(state, thirdAddress.getState());
//
//        // Delete the test note.
//        secondDao.delete(thirdAddress.getId());
//
//        // Load a third instance of the Dao and verify that 
//        // the note was deleted from the file.
//        AddressBookDao thirdDao = (AddressBookDao) ctx.getBean("addressDao");
//        assertEquals(thirdDao.get(id), null);
//
//    }
//
//    /**
//     * Test of create method, of class AddressBook.
//     */
//    @Test
//    public void testCreateB() {
//        System.out.println("create");
//        Address address = new Address();
//        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");
//        Address expResult = address;
//        Address result = instance.create(address);
//        assertEquals(expResult, result);
//
//        int id = result.getId();
//        assertTrue(result.getId() != 0);
//        assertTrue(result.getId() >= instance.list().size());
//
//        // Test get method.
//        Address returnedAddress = instance.get(id);
//        assertEquals(returnedAddress, result);
//        instance.delete(address.getId());
//
//        returnedAddress = instance.get(id);
//        assertEquals(returnedAddress, null);
//    }
//
//    /**
//     * Test of getAllAddresses method, of class AddressBook.
//     */
//    @Test
//    public void testGetAllAddressesB() {
//        System.out.println("getAllAddresses");
//        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");
//
//                int initialSize = instance.list().size();
//
//        Address addressOne = new Address();
//        Address addressTwo = new Address();
//        Address addressThree = new Address();
//
//        instance.create(addressOne);
//        instance.create(addressTwo);
//        instance.create(addressThree);
//
//        assertTrue(instance.list().contains(addressOne));
//        assertTrue(instance.list().contains(addressTwo));
//        assertTrue(instance.list().contains(addressThree));
//
//                int firstExpSizeResult = initialSize + 3;
//        int sizeResult = instance.list().size();
//        assertEquals(firstExpSizeResult, sizeResult);
//
//        
//        instance.delete(addressOne.getId());
//        instance.delete(addressTwo.getId());
//        instance.delete(addressThree.getId());
//
//        int secondExpSizeResult = initialSize;
//         sizeResult = instance.list().size();
//        assertEquals(secondExpSizeResult, sizeResult);
//
////        String lastName = "Steve";
////        List<Address> result = instance.searchByLastName(lastName);
////        assertEquals(true, result.isEmpty());
////
////        lastName = "Jones";
////        result = instance.searchByLastName(lastName);
////        assertEquals(false, result.isEmpty());
////        assertEquals(2, result.size());
//    }
//
//    @Test
//    public void testEncodeAndDecodeB() {
//
//        //Dvd dvd = new DvdImplementation();
//        AddressBookDao addressBookDao = (AddressBookDao) ctx.getBean("addressDao");
//        Address newNote = new Address();
//
//        // Create the file in the Dao.
//        Address returnedNote = addressBookDao.create(newNote);
//
//        // Record the notes id number.
//        int id = newNote.getId();
//
//        // Verify that the note object that the create method passed back
//        // was the same one it was given.
//        assertEquals(newNote, returnedNote);
//
//        String city = "Seville";
//        String zipCode = "88775";
//        String firstName = "First and last name here";
//        String street = "3589 Street Road";
//        String state = "Michigan";
//
//        // Set some text in the note file.
//        //returnedNote.setNoteString("This Is a test note.");
////        returnedNote.setCity(city);
////        returnedNote.setCountry(country);
////        returnedNote.setType(type);
////        returnedNote.setZipcode(zipCode);
////        returnedNote.setPoBox(poBox);
////        returnedNote.setFirstName(firstName);
////        returnedNote.setStreetAddress(street);
////        returnedNote.setState(state);
//        String lastName = "Now With Last Names!";
//        returnedNote.setCity(city);
////        returnedNote.setCountry(country);
//        //      returnedNote.setType(type);
//        returnedNote.setZip(zipCode);
//        //returnedNote.setPoBox(poBox);
//        //returnedNote.setStreetName(poBox);
//        returnedNote.setFirstName(firstName);
//        returnedNote.setLastName(lastName);
//        returnedNote.setStreetName(street);
//        returnedNote.setStreetNumber(street);
//        returnedNote.setState(state);
//
//// Use the update method to save this new text to file.
//        addressBookDao.update(newNote);
//
//        // Load a new instance of the NoteDao.
//        AddressBookDao secondDao = (AddressBookDao) ctx.getBean("addressDao");
//
//        // Pull a note  using the id number recorded earlier.
//        Address thirdAddress = secondDao.get(id);
//
//        assertTrue(thirdAddress != null);
//
//        // Check that the update method saved the new text.
//        //assertEquals("This Is a test note.", thirdAddress.getNoteString());
////        assertEquals(city, thirdAddress.getCity());
////        assertEquals(country, thirdAddress.getCountry());
////        assertEquals(type, thirdAddress.getType());
////        assertEquals(zipCode, thirdAddress.getZipcode());
////        assertEquals(poBox, thirdAddress.getPoBox());
////        assertEquals(firstName, thirdAddress.getFirstName());
////        assertEquals(street, thirdAddress.getStreetAddress());
////        assertEquals(state, thirdAddress.getState());
//
//
//
//
// // Check that the update method saved the new text.
//        //assertEquals("This Is a test note.", thirdAddress.getNoteString());
//        assertEquals(city, thirdAddress.getCity());
//        //assertEquals(country, thirdAddress.getCountry());
//        //assertEquals(type, thirdAddress.getType());
//        assertEquals(zipCode, thirdAddress.getZip());
//        //assertEquals(poBox, thirdAddress.getPoBox());
//        assertEquals(firstName, thirdAddress.getFirstName());
//        assertEquals(street, thirdAddress.getStreetName());
//        assertEquals(street, thirdAddress.getStreetNumber());
//        assertEquals(state, thirdAddress.getState());
//
//
//
//// Delete the test note.
//        secondDao.delete(thirdAddress.getId());
//
//        // Load a third instance of the Dao and verify that 
//        // the note was deleted from the file.
//        AddressBookDao thirdDao = (AddressBookDao) ctx.getBean("addressDao");
//        assertEquals(thirdDao.get(id), null);
//
//    }
//
//    /**
//     * Test of create method, of class AddressBook.
//     */
//    @Test
//    public void testCreateC() {
//        System.out.println("create");
//        Address address = new Address();
//        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");
//        Address expResult = address;
//        Address result = instance.create(address);
//        assertEquals(expResult, result);
//
//        int id = result.getId();
//        assertTrue(result.getId() != 0);
//        assertTrue(result.getId() >= instance.list().size());
//
//        // Test get method.
//        Address returnedAddress = instance.get(id);
//        assertEquals(returnedAddress, result);
//        instance.delete(address.getId());
//
//        returnedAddress = instance.get(id);
//        assertEquals(returnedAddress, null);
//    }
//
//    /**
//     * Test of getAllAddresses method, of class AddressBook.
//     */
//    @Test
//    public void testGetAllAddressesC() {
//        System.out.println("getAllAddresses");
//        AddressBookDao instance = (AddressBookDao) ctx.getBean("addressDao");
//
//        Address addressOne = new Address();
//        Address addressTwo = new Address();
//        Address addressThree = new Address();
//
//        instance.create(addressOne);
//        instance.create(addressTwo);
//        instance.create(addressThree);
//
//        assertTrue(instance.list().contains(addressTwo));
//
//        instance.delete(addressOne.getId());
//        instance.delete(addressTwo.getId());
//        instance.delete(addressThree.getId());
//
//        int expSizeResult = 3;
//        int sizeResult = instance.list().size();
//        assertEquals(expSizeResult, sizeResult);
//
//        String lastName = "Steve";
//        List<Address> result = instance.searchByLastName(lastName);
//        assertEquals(true, result.isEmpty());
//
//        lastName = "Jones";
//        result = instance.searchByLastName(lastName);
//        assertEquals(false, result.isEmpty());
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    public void testEncodeAndDecodeC() {
//
//        //Dvd dvd = new DvdImplementation();
//        AddressBookDao addressBookDao = (AddressBookDao) ctx.getBean("addressDao");
//        Address newNote = new Address();
//
//        // Create the file in the Dao.
//        Address returnedNote = addressBookDao.create(newNote);
//
//        // Record the notes id number.
//        int id = newNote.getId();
//
//        // Verify that the note object that the create method passed back
//        // was the same one it was given.
//        assertEquals(newNote, returnedNote);
//
//        String city = "Seville";
//        String country = "USA";
//        String type = "mailing";
//        String zipCode = "88775";
//        String poBox = "21";
//        String firstName = "First and last name here";
//        String street = "3589 Street Road";
//        String state = "Michigan";
//
//        // Set some text in the note file.
//        //returnedNote.setNoteString("This Is a test note.");
////        returnedNote.setCity(city);
////        returnedNote.setCountry(country);
////        returnedNote.setType(type);
////        returnedNote.setZipcode(zipCode);
////        returnedNote.setPoBox(poBox);
////        returnedNote.setFirstName(firstName);
////        returnedNote.setStreetAddress(street);
////        returnedNote.setState(state);
//
//    String lastName = "Now With Last Names!";
//        returnedNote.setCity(city);
////        returnedNote.setCountry(country);
//        //      returnedNote.setType(type);
//        returnedNote.setZip(zipCode);
//        //returnedNote.setPoBox(poBox);
//        //returnedNote.setStreetName(poBox);
//        returnedNote.setFirstName(firstName);
//        returnedNote.setLastName(lastName);
//        returnedNote.setStreetName(street);
//        returnedNote.setStreetNumber(street);
//        returnedNote.setState(state);
//
//        // Use the update method to save this new text to file.
//        addressBookDao.update(newNote);
//
//        // Load a new instance of the NoteDao.
//        AddressBookDao secondDao = (AddressBookDao) ctx.getBean("addressDao");
//
//        // Pull a note  using the id number recorded earlier.
//        Address thirdAddress = secondDao.get(id);
//
//        assertTrue(thirdAddress != null);
//
//        // Check that the update method saved the new text.
//        //assertEquals("This Is a test note.", thirdAddress.getNoteString());
////        assertEquals(city, thirdAddress.getCity());
////        assertEquals(country, thirdAddress.getCountry());
////        assertEquals(type, thirdAddress.getType());
////        assertEquals(zipCode, thirdAddress.getZipcode());
////        assertEquals(poBox, thirdAddress.getPoBox());
////        assertEquals(firstName, thirdAddress.getFirstName());
////        assertEquals(street, thirdAddress.getStreetAddress());
////        assertEquals(state, thirdAddress.getState());
//
//
// // Check that the update method saved the new text.
//        //assertEquals("This Is a test note.", thirdAddress.getNoteString());
//        assertEquals(city, thirdAddress.getCity());
//        //assertEquals(country, thirdAddress.getCountry());
//        //assertEquals(type, thirdAddress.getType());
//        assertEquals(zipCode, thirdAddress.getZip());
//        //assertEquals(poBox, thirdAddress.getPoBox());
//        assertEquals(firstName, thirdAddress.getFirstName());
//        assertEquals(street, thirdAddress.getStreetName());
//        assertEquals(street, thirdAddress.getStreetNumber());
//        assertEquals(state, thirdAddress.getState());
//
//        // Delete the test note.
//        secondDao.delete(thirdAddress.getId());
//
//        // Load a third instance of the Dao and verify that 
//        // the note was deleted from the file.
//        AddressBookDao thirdDao = (AddressBookDao) ctx.getBean("addressDao");
//        assertEquals(thirdDao.get(id), null);
//
//    }
//
//
//
//
//
//
//    @Test
//    public void testTheSearchByCity() {
//        //Dvd dvd = new DvdImplementation();
//        AddressBookDao addressBookDao = ctx.getBean("productionAddressDao", AddressBookLambdaImpl.class);
//        Address newNote = new Address();
//
//        assertEquals(addressBookDao.searchByCity("wooster").size(), 4);
//
//    }
//
//    @Test
//    public void testTheSearchByZipcode() {
//        //Dvd dvd = new DvdImplementation();
//        AddressBookDao addressBookDao = ctx.getBean("productionAddressDao", AddressBookLambdaImpl.class);
//        Address newNote = new Address();
//
//        assertEquals(addressBookDao.searchByZip("44287").size(), 3);
//
//    }
//
//    @Test
//    public void testTheSearchByState() {
//        //Dvd dvd = new DvdImplementation();
//        AddressBookDao addressBookDao = ctx.getBean("productionAddressDao", AddressBookLambdaImpl.class);
//        Address newNote = new Address();
//
//        //List<List<Address>> mess = addressBookDao.searchByState("OH");
//        //Map<String /* City */, List<Address>> mess = addressBookDao.searchByState("OH");
//        List<Address> mess = addressBookDao.searchByState("OH");
//        assertEquals(mess.size(), 6);
//
//        // System.out.println(mess);
//    }
//
//    
//    @Test
//    public void testGenericEncodeAndDecode() {
//
//        //Dvd dvd = new DvdImplementation();
//        AddressBookDao addressBookDao = (AddressBookDao) ctx.getBean("addressDao");
//        Address newNote = new Address();
//        Address woosterOne = new Address();
//        woosterOne.setCity("wooster");
//        addressBookDao.create(woosterOne);
//        
//        Address woosterTwo = new Address();
//        woosterTwo.setCity("wooster");
//        addressBookDao.create(woosterTwo);
//        
//
//        // Create the file in the Dao.
//        Address returnedNote = addressBookDao.create(newNote);
//
//        // Record the notes id number.
//        int id = newNote.getId();
//
//        // Verify that the note object that the create method passed back
//        // was the same one it was given.
//        assertEquals(newNote, returnedNote);
//
//        String city = "Seville";
//        String zipCode = "88775";
//        String firstName = "First and last name here";
//        String street = "3589 Street Road";
//        String state = "Michigan";
//
//        String lastName = "Now With Last Names!";
//        returnedNote.setCity(city);
//        returnedNote.setZip(zipCode);
//        returnedNote.setFirstName(firstName);
//        returnedNote.setLastName(lastName);
//        returnedNote.setStreetName(street);
//        returnedNote.setStreetNumber(street);
//        returnedNote.setState(state);
//
//        assertEquals(addressBookDao.searchByCity("wooster").size(), 2);
//        
//        // Use the update method to save this new text to file.
//        addressBookDao.update(newNote);
//
//        // Load a new instance of the NoteDao.
//        AddressBookDao secondDao = (AddressBookDao) ctx.getBean("addressDao");
//
//        // Pull a note  using the id number recorded earlier.
//        Address thirdAddress = secondDao.get(id);
//
//        assertTrue(thirdAddress != null);
//
//        // Check that the update method saved the new text.
//        assertEquals(city, thirdAddress.getCity());
//        assertEquals(zipCode, thirdAddress.getZip());
//        assertEquals(firstName, thirdAddress.getFirstName());
//        assertEquals(street, thirdAddress.getStreetName());
//        assertEquals(street, thirdAddress.getStreetNumber());
//        assertEquals(state, thirdAddress.getState());
//
//        // Delete the test note.
//        secondDao.delete(thirdAddress.getId());
//
//        // Load a third instance of the Dao and verify that 
//        // the note was deleted from the file.
//        AddressBookDao thirdDao = (AddressBookDao) ctx.getBean("addressDao");
//        assertEquals(thirdDao.get(id), null);
//
//    }
//
//    
//    
//        @Test
//    public void testGenericEncodeAndDecode() {
//
//        //Dvd dvd = new DvdImplementation();
//        AddressBookDao addressBookDao = (AddressBookDao) ctx.getBean("addressDao");
//        Address newNote = new Address();
//
//        // Create the file in the Dao.
//        Address returnedNote = addressBookDao.create(newNote);
//
//        // Record the notes id number.
//        int id = newNote.getId();
//
//        // Verify that the note object that the create method passed back
//        // was the same one it was given.
//        assertEquals(newNote, returnedNote);
//
//        String city = "Seville";
//        String zipCode = "88775";
//        String firstName = "First and last name here";
//        String street = "3589 Street Road";
//        String state = "Michigan";
//
//        String lastName = "Now With Last Names!";
//        returnedNote.setCity(city);
//        returnedNote.setZip(zipCode);
//        returnedNote.setFirstName(firstName);
//        returnedNote.setLastName(lastName);
//        returnedNote.setStreetName(street);
//        returnedNote.setStreetNumber(street);
//        returnedNote.setState(state);
//
//        // Use the update method to save this new text to file.
//        addressBookDao.update(newNote);
//
//        // Load a new instance of the NoteDao.
//        AddressBookDao secondDao = (AddressBookDao) ctx.getBean("addressDao");
//
//        // Pull a note  using the id number recorded earlier.
//        Address thirdAddress = secondDao.get(id);
//
//        assertTrue(thirdAddress != null);
//
//        // Check that the update method saved the new text.
//        assertEquals(city, thirdAddress.getCity());
//        assertEquals(zipCode, thirdAddress.getZip());
//        assertEquals(firstName, thirdAddress.getFirstName());
//        assertEquals(street, thirdAddress.getStreetName());
//        assertEquals(street, thirdAddress.getStreetNumber());
//        assertEquals(state, thirdAddress.getState());
//
//        // Delete the test note.
//        secondDao.delete(thirdAddress.getId());
//
//        // Load a third instance of the Dao and verify that 
//        // the note was deleted from the file.
//        AddressBookDao thirdDao = (AddressBookDao) ctx.getBean("addressDao");
//        assertEquals(thirdDao.get(id), null);
//
//    }


}


