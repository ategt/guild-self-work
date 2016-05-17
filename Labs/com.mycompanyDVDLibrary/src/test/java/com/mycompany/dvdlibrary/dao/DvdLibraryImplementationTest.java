/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.interfaces.NoteDao;
import com.mycompany.dvdlibrary.interfaces.DvdLibrary;
import com.mycompany.dvdlibrary.dto.DvdImplementation;
import com.mycompany.dvdlibrary.dto.NoteImplementation;
import com.mycompany.dvdlibrary.interfaces.Dvd;
import com.mycompany.dvdlibrary.interfaces.Note;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class DvdLibraryImplementationTest {

    public DvdLibraryImplementationTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class DvdLibrary.
     */
    @Test
    public void testCreate() {

        Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);
        Dvd expResult = dvd;
        Dvd result = instance.create(dvd);

        // Check to see that create() passed back to same file.
        assertEquals(expResult, result);

        // Check to see that create() assigned the Object a valid ID.
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Dvd returnedDvd = instance.get(id);
        assertEquals(returnedDvd, result);
        instance.delete(dvd);

        // Test that the Delete method erased it.
        returnedDvd = instance.get(id);
        assertEquals(returnedDvd, null);

    }

    @Test
    public void testCreateB() {

        Dvd dvd = new DvdImplementation();
        Dvd secondDvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);
        Dvd expResult = dvd;
        Dvd result = instance.create(dvd);

        // Check to see that create() passed back to same file.
        assertEquals(expResult, result);

        // Check to see that create() assigned the Object a valid ID.
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Check to verify that the create Method did not get confused.
        Dvd secondResult = instance.create(secondDvd);
        assertEquals(secondResult, secondDvd);

        // Verify that the create method gave the second DVD Object the appriate ID.
        int secondId = secondResult.getId();
        assertEquals(secondId, id + 1);

        // Test get method.
        Dvd returnedDvd = instance.get(id);
        assertEquals(returnedDvd, result);
        instance.delete(dvd);

        Dvd thirdReturnedDvd = instance.get(secondId);
        assertEquals(thirdReturnedDvd, secondResult);
        instance.delete(thirdReturnedDvd);

        // Test that the Delete method erased it.
        returnedDvd = instance.get(id);
        assertEquals(returnedDvd, null);

        returnedDvd = instance.get(secondId);
        assertEquals(returnedDvd, null);

    }

    @Test
    public void testEncode() {

        //Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        // Check to see if a fictional Dvd is on the list.
        String title = "Steel Drum";
        List<Dvd> titleSearch = instance.searchByTitle(title);
        assertEquals(true, titleSearch.isEmpty());

        // Set some properties for my test DVD.
        Dvd testDvd = new DvdImplementation();
        testDvd.setTitle(title);
        testDvd.setDirectorsName("Steven Spielburg");

        // Add my Made up Dvd To the list.
        Dvd resultTest = instance.create(testDvd);

        // Check to see that create() passed back to same file.
        assertEquals(resultTest, testDvd);

        // Check to see that the fictional Dvd has been added to the list.
        titleSearch = instance.searchByTitle(title);
        assertEquals(false, titleSearch.isEmpty());

        testDvd.setStudio("Warner Studios");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date newDate = null;

        try {
            newDate = dateFormat.parse("1950-12-19");
        } catch (ParseException ex) {
            Logger.getLogger(DvdLibraryImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        testDvd.setReleaseDate(newDate);

        // Test update
        instance.update(testDvd);

        // Load a new Instance of everything and verify that everything loaded.
        // Instantiate a DvdLibrary object using the test constructor.
        // This constructor loads a seperate test file.
        NoteDao newNoteDao = new NoteDaoImplementation();
        DvdLibrary newInstance = new DvdLibraryImplementation(true, newNoteDao);

        // Verify that my made up Dvd title was loaded from the saved file.
        String titleb = "Steel Drum";
        List<Dvd> titleSearchb = newInstance.searchByTitle(titleb);
        assertEquals(false, titleSearchb.isEmpty());

        assertEquals(1, titleSearchb.size());

        Dvd dvd = titleSearchb.get(0);

        // Check to see that the Test dvd got loaded with the right Attributes.
        assertEquals("Steel Drum", dvd.getTitle());
        assertEquals("Steven Spielburg", dvd.getDirectorsName());
        assertEquals(newDate, dvd.getReleaseDate());
        assertEquals("Warner Studios", dvd.getStudio());

        newInstance.delete(dvd);

        // Verify that my made up Dvd title was deleted from the DAOs list.
        List<Dvd> titleSearchc = newInstance.searchByTitle(titleb);
        assertEquals(true, titleSearchc.isEmpty());

    }

    /**
     * Test of getAllAddresses method, of class DvdLibrary.
     */
    @Test
    public void testGetAllDvdsB() {

        // Instantiate a DvdLibrary object using the test constructor.
        // This constructor loads a seperate test file.
        NoteDao noteDao = new NoteDaoImplementation();
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        Dvd dvdOne = new DvdImplementation();
        Dvd dvdTwo = new DvdImplementation();
        Dvd dvdThree = new DvdImplementation();

        // Add three DVDs to the list.
        instance.create(dvdOne);
        instance.create(dvdTwo);
        instance.create(dvdThree);

        // Check to see that the list contains one of the added DVDs.
        assertTrue(instance.getAllDvds().contains(dvdOne));
        assertTrue(instance.getAllDvds().contains(dvdTwo));
        assertTrue(instance.getAllDvds().contains(dvdThree));

        int expSizeResult = 11;

        // Check that the size of the list grew by 3.
        assertEquals(expSizeResult + 3, instance.size());

        instance.delete(dvdOne);
        instance.delete(dvdTwo);
        instance.delete(dvdThree);

        // Check that the delete method shrank the list size by 3.
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);

        // Check to see if a fictional Dvd is on the list.
        String title = "SpongeBob";
        List<Dvd> result = instance.searchByTitle(title);
        assertEquals(true, result.isEmpty());

        // Check to see if a real DVD is on the list.
        // This title search should return two copies.
        title = "Bills movie 3";
        result = instance.searchByTitle(title);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
    }

    /**
     * Test of create method, of class DvdLibrary.
     */
    @Test
    public void testCreateb() {

        Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);
        Dvd expResult = dvd;
        Dvd result = instance.create(dvd);

        // Check to see that create() passed back to same file.
        assertEquals(expResult, result);

        // Check to see that create() assigned the Object a valid ID.
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Dvd returnedDvd = instance.get(id);
        assertEquals(returnedDvd, result);
        instance.delete(dvd);

        // Test that the Delete method erased it.
        returnedDvd = instance.get(id);
        assertEquals(returnedDvd, null);

    }

    @Test
    public void testEncodeb() {

        //Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        // Check to see if a fictional Dvd is on the list.
        String title = "Steel Drum";
        List<Dvd> titleSearch = instance.searchByTitle(title);
        assertEquals(true, titleSearch.isEmpty());

        // Set some properties for my test DVD.
        Dvd testDvd = new DvdImplementation();
        testDvd.setTitle(title);
        testDvd.setDirectorsName("Steven Spielburg");

        // Add my Made up Dvd To the list.
        Dvd resultTest = instance.create(testDvd);

        // Check to see that create() passed back to same file.
        assertEquals(resultTest, testDvd);

        // Check to see that the fictional Dvd has been added to the list.
        titleSearch = instance.searchByTitle(title);
        assertEquals(false, titleSearch.isEmpty());

        testDvd.setStudio("Warner Studios");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date newDate = null;

        try {
            newDate = dateFormat.parse("1950-12-19");
        } catch (ParseException ex) {
            Logger.getLogger(DvdLibraryImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        testDvd.setReleaseDate(newDate);

        // Test update
        instance.update(testDvd);

        // Load a new Instance of everything and verify that everything loaded.
        // Instantiate a DvdLibrary object using the test constructor.
        // This constructor loads a seperate test file.
        NoteDao newNoteDao = new NoteDaoImplementation();
        DvdLibrary newInstance = new DvdLibraryImplementation(true, newNoteDao);

        // Verify that my made up Dvd title was loaded from the saved file.
        String titleb = "Steel Drum";
        List<Dvd> titleSearchb = newInstance.searchByTitle(titleb);
        assertEquals(false, titleSearchb.isEmpty());

        assertEquals(1, titleSearchb.size());

        Dvd dvd = titleSearchb.get(0);

        // Check to see that the Test dvd got loaded with the right Attributes.
        assertEquals("Steel Drum", dvd.getTitle());
        assertEquals("Steven Spielburg", dvd.getDirectorsName());
        assertEquals(newDate, dvd.getReleaseDate());
        assertEquals("Warner Studios", dvd.getStudio());

        newInstance.delete(dvd);

        // Verify that my made up Dvd title was deleted from the DAOs list.
        List<Dvd> titleSearchc = newInstance.searchByTitle(titleb);
        assertEquals(true, titleSearchc.isEmpty());

    }

    /**
     * Test of getAllAddresses method, of class DvdLibrary.
     */
    @Test
    public void testGetAllDvdsb() {

        // Instantiate a DvdLibrary object using the test constructor.
        // This constructor loads a seperate test file.
        NoteDao noteDao = new NoteDaoImplementation();
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        Dvd dvdOne = new DvdImplementation();
        Dvd dvdTwo = new DvdImplementation();
        Dvd dvdThree = new DvdImplementation();

        // Add three DVDs to the list.
        instance.create(dvdOne);
        instance.create(dvdTwo);
        instance.create(dvdThree);

        // Check to see that the list contains one of the added DVDs.
        assertTrue(instance.getAllDvds().contains(dvdOne));
        assertTrue(instance.getAllDvds().contains(dvdTwo));
        assertTrue(instance.getAllDvds().contains(dvdThree));

        int expSizeResult = 11;

        // Check that the size of the list grew by 3.
        assertEquals(expSizeResult + 3, instance.size());

        instance.delete(dvdOne);
        instance.delete(dvdTwo);
        instance.delete(dvdThree);

        // Check that the delete method shrank the list size by 3.
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);

        // Check to see if a fictional Dvd is on the list.
        String title = "SpongeBob";
        List<Dvd> result = instance.searchByTitle(title);
        assertEquals(true, result.isEmpty());

        // Check to see if a real DVD is on the list.
        // This title search should return two copies.
        title = "Bills movie 3";
        result = instance.searchByTitle(title);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
    }

    /**
     * Test of create method, of class DvdLibrary.
     */
    @Test
    public void testCreateC() {

        Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);
        Dvd expResult = dvd;
        Dvd result = instance.create(dvd);

        // Check to see that create() passed back to same file.
        assertEquals(expResult, result);

        // Check to see that create() assigned the Object a valid ID.
        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Dvd returnedDvd = instance.get(id);
        assertEquals(returnedDvd, result);
        instance.delete(dvd);

        // Test that the Delete method erased it.
        returnedDvd = instance.get(id);
        assertEquals(returnedDvd, null);

    }

    @Test
    public void testEncodeC() {

        //Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        // Check to see if a fictional Dvd is on the list.
        String title = "Steel Drum";
        List<Dvd> titleSearch = instance.searchByTitle(title);
        assertEquals(true, titleSearch.isEmpty());

        // Set some properties for my test DVD.
        Dvd testDvd = new DvdImplementation();
        testDvd.setTitle(title);
        testDvd.setDirectorsName("Steven Spielburg");

        // Add my Made up Dvd To the list.
        Dvd resultTest = instance.create(testDvd);

        // Check to see that create() passed back to same file.
        assertEquals(resultTest, testDvd);

        // Check to see that the fictional Dvd has been added to the list.
        titleSearch = instance.searchByTitle(title);
        assertEquals(false, titleSearch.isEmpty());

        testDvd.setStudio("Warner Studios");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date newDate = null;

        try {
            newDate = dateFormat.parse("1950-12-19");
        } catch (ParseException ex) {
            Logger.getLogger(DvdLibraryImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        testDvd.setReleaseDate(newDate);

        // Test update
        instance.update(testDvd);

        // Load a new Instance of everything and verify that everything loaded.
        // Instantiate a DvdLibrary object using the test constructor.
        // This constructor loads a seperate test file.
        NoteDao newNoteDao = new NoteDaoImplementation();
        DvdLibrary newInstance = new DvdLibraryImplementation(true, newNoteDao);

        // Verify that my made up Dvd title was loaded from the saved file.
        String titleb = "Steel Drum";
        List<Dvd> titleSearchb = newInstance.searchByTitle(titleb);
        assertEquals(false, titleSearchb.isEmpty());

        assertEquals(1, titleSearchb.size());

        Dvd dvd = titleSearchb.get(0);

        // Check to see that the Test dvd got loaded with the right Attributes.
        assertEquals("Steel Drum", dvd.getTitle());
        assertEquals("Steven Spielburg", dvd.getDirectorsName());
        assertEquals(newDate, dvd.getReleaseDate());
        assertEquals("Warner Studios", dvd.getStudio());

        newInstance.delete(dvd);

        // Verify that my made up Dvd title was deleted from the DAOs list.
        List<Dvd> titleSearchc = newInstance.searchByTitle(titleb);
        assertEquals(true, titleSearchc.isEmpty());

    }

    /**
     * Test of getAllAddresses method, of class DvdLibrary.
     */
    @Test
    public void testGetAllDvdsc() {

        // Instantiate a DvdLibrary object using the test constructor.
        // This constructor loads a seperate test file.
        NoteDao noteDao = new NoteDaoImplementation();
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        Dvd dvdOne = new DvdImplementation();
        Dvd dvdTwo = new DvdImplementation();
        Dvd dvdThree = new DvdImplementation();

        // Add three DVDs to the list.
        instance.create(dvdOne);
        instance.create(dvdTwo);
        instance.create(dvdThree);

        // Check to see that the list contains one of the added DVDs.
        assertTrue(instance.getAllDvds().contains(dvdOne));
        assertTrue(instance.getAllDvds().contains(dvdTwo));
        assertTrue(instance.getAllDvds().contains(dvdThree));

        int expSizeResult = 11;

        // Check that the size of the list grew by 3.
        assertEquals(expSizeResult + 3, instance.size());

        instance.delete(dvdOne);
        instance.delete(dvdTwo);
        instance.delete(dvdThree);

        // Check that the delete method shrank the list size by 3.
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);

        // Check to see if a fictional Dvd is on the list.
        String title = "SpongeBob";
        List<Dvd> result = instance.searchByTitle(title);
        assertEquals(true, result.isEmpty());

        // Check to see if a real DVD is on the list.
        // This title search should return two copies.
        title = "Bills movie 3";
        result = instance.searchByTitle(title);
        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void testSearchByAge() {

        Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);
//        Dvd expResult = dvd;
//        Dvd result = instance.create(dvd);    

        Date date = new Date();
        date.setTime(0);
        date.setYear(-1900);

        List<Dvd> fullLibrary = instance.searchByAge(date);

        assertEquals(fullLibrary.size(), instance.getAllDvds().size() - 2);

        List<Dvd> shouldBeEmpty = instance.searchByAge(new Date());

        assertEquals(shouldBeEmpty.size(), 0);

    }

    @Test
    public void testSearchByRating() {

        Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        List<Dvd> goodMoviesInLibrary = instance.searchByRating("A+");

        assertEquals(goodMoviesInLibrary.size(), 2);

        goodMoviesInLibrary = instance.searchByRating(null);
        assertEquals(goodMoviesInLibrary.size(), 0);
    }

    @Test
    public void testSearchByStudio() {

        Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        List<Dvd> searchResultsInLibrary = instance.searchByStudio("La Studios");

        assertEquals(searchResultsInLibrary.size(), 5);

        searchResultsInLibrary = instance.searchByStudio(null);
        assertEquals(searchResultsInLibrary.size(), 0);
    }

    @Test
    public void testAverageAge() {

        Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        Date dateOne = new Date();
        dateOne.setTime(300);

        Dvd dvdOne = new DvdImplementation();
        dvdOne.setReleaseDate(dateOne);

        Date dateTwo = new Date();
        dateTwo.setTime(100);

        Dvd dvdTwo = new DvdImplementation();
        dvdTwo.setReleaseDate(dateTwo);

        //Long correctAge = -20222382400000;
        Date dateThree = new Date();
        dateThree.setTime(200);

        List<Dvd> testList = new ArrayList();
        testList.add(dvdOne);
        testList.add(dvdTwo);

        Date average = instance.averageAge(testList);
        assertEquals(average.getTime(), dateThree.getTime());

    }

    @Test
    public void testFindNewest() {

        Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        Date dateOne = new Date();

        Dvd dvdOne = new DvdImplementation();
        dvdOne.setReleaseDate(dateOne);

        instance.create(dvdOne);

        Dvd newest = instance.findNewestDvd();
        assertEquals(newest, dvdOne);

        instance.delete(dvdOne);

        Date dateTwo = new Date();

        Dvd dvdTwo = new DvdImplementation();
        dvdTwo.setReleaseDate(dateTwo);

        instance.create(dvdTwo);
        newest = instance.findNewestDvd();
        assertTrue(newest != dvdOne);

        instance.delete(dvdTwo);
    }

    @Test
    public void testFindOldest() {

        Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        Date dateOne = new Date();
        dateOne.setTime(Long.MIN_VALUE);

        Dvd dvdOne = new DvdImplementation();
        dvdOne.setReleaseDate(dateOne);

        instance.create(dvdOne);

        Dvd oldest = instance.findOldestDvd();
        assertEquals(oldest, dvdOne);

        instance.delete(dvdOne);

        Date dateTwo = new Date();

        Dvd dvdTwo = new DvdImplementation();
        dvdTwo.setReleaseDate(dateTwo);

        instance.create(dvdTwo);
        oldest = instance.findOldestDvd();
        assertTrue(oldest != dvdOne);

        instance.delete(dvdTwo);

        Date dateThree = new Date();

        Dvd dvdThree = new DvdImplementation();
        dvdThree.setReleaseDate(null);

        instance.create(dvdThree);
        oldest = instance.findOldestDvd();
        assertTrue(oldest != dvdOne);

        instance.delete(dvdThree);

    }

    @Test
    public void testFindAverageNotesCount() {

        Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        List<Note> notesListOne = new ArrayList();

        for (int x = 0; x < 75; x++) {
            notesListOne.add(new NoteImplementation());
        }

        Dvd dvdOne = new DvdImplementation();
        dvdOne.setNotes(notesListOne);

        List<Note> notesListTwo = new ArrayList();

        for (int x = 0; x < 25; x++) {
            notesListTwo.add(new NoteImplementation());
        }

        Dvd dvdTwo = new DvdImplementation();
        dvdTwo.setNotes(notesListTwo);

        List<Dvd> dvdTestList = new ArrayList();

        dvdTestList.add(dvdTwo);
        dvdTestList.add(dvdOne);

        Float expected = 50f;

        Float averageNumberOfNotes = instance.findAverageNumberOfNotes(dvdTestList);
        assertEquals(averageNumberOfNotes, expected);

    }

    @Test
    public void testSearchByDirector() {

        Dvd dvd = new DvdImplementation();
        NoteDao noteDao = new NoteDaoImplementation();

        // Load the test File
        DvdLibrary instance = new DvdLibraryImplementation(true, noteDao);

        // java.util.Map<String /* Rating  */, List<Dvd>> 
        String directorsName = "Luc Besson";

        java.util.Map<String, List<Dvd>> returnedMapList = instance.searchByDirector(directorsName);

        assertEquals(returnedMapList.size(), 5);

        List<Dvd> minusList = returnedMapList.get("C-");
        assertEquals(minusList.size(), 2);

        List<Dvd> plusList = returnedMapList.get("C+");
        assertEquals(plusList.size(), 1);

    }
}
