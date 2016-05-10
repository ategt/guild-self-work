/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.State;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class StateDaoTest {

    public StateDaoTest() {
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
        State state = new State();
        StateDao instance = new StateDao(true);
        State expResult = null;
        State result = instance.create(state);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateB() {
        System.out.println("create");
        State state = null;
        StateDao instance = new StateDao(true);
        State expResult = null;
        State result = instance.create(state);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateC() {
        System.out.println("create");
        State state = new State();
        state.setState("GH");
        StateDao instance = new StateDao(true);
        State expResult = state;
        State result = instance.create(state);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateD() {
        System.out.println("create");
        State state = new State();
        state.setState("Z");
        StateDao instance = new StateDao(true);
        State expResult = null;
        State result = instance.create(state);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateE() {
        System.out.println("create");
        State state = new State();
        state.setState("GH");
        StateDao instance = new StateDao(true);
        State expResult = state;
        State result = instance.create(state, state.getState());
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateF() {
        System.out.println("create");
        State state = new State();
        state.setState("Mexico");
        StateDao instance = new StateDao(true);
        State expResult = state;
        State result = instance.create(state, state.getState());
        assertEquals(expResult, result);
    }

    @Test
    public void testDelete() {
        System.out.println("create");
        State state = new State();
        state.setState("SG");
        StateDao instance = new StateDao(true);
        State expResult = state;
        State result = instance.create(state);
        assertEquals(expResult, result);

        // Test get method.
        State returnedState = instance.get(state.getState());
        assertEquals(returnedState, result);
        instance.delete(state);

        returnedState = instance.get(state.getState());
        assertEquals(returnedState, null);

    }

    @Test
    public void testGet() {
        System.out.println("create");
        State state = new State();
        state.setState("SG");
        StateDao instance = new StateDao(true);
        State expResult = state;
        State result = instance.create(state);
        assertEquals(expResult, result);

        // Test get method.
        State returnedState = instance.get(state.getState());
        assertEquals(returnedState, result);
        instance.delete(state);

        returnedState = instance.get(state.getState());
        assertEquals(returnedState, null);

    }

    @Test
    public void testGet2() {
        System.out.println("create");
        State state = new State();
        state.setState("SG");
        StateDao instance = new StateDao(true);
        State expResult = state;
        String stateNameLowerCase = "sg";
        State result = instance.create(state);
        assertEquals(expResult, result);

        // Test get method.
        State returnedState = instance.get(stateNameLowerCase);
        assertEquals(returnedState, result);
        instance.delete(state);

        returnedState = instance.get(stateNameLowerCase);
        assertEquals(returnedState, null);

    }

    @Test
    public void testGet3() {
        System.out.println("create");
        State state = new State();
        state.setState("SG");
        StateDao instance = new StateDao(true);
        State expResult = state;
        String stateNameLowerCase = "sG";
        State result = instance.create(state);
        assertEquals(expResult, result);

        // Test get method.
        State returnedState = instance.get(stateNameLowerCase);
        assertEquals(returnedState, result);
        instance.delete(state);

        returnedState = instance.get(stateNameLowerCase);
        assertEquals(returnedState, null);

    }

    @Test
    public void testSize() {

        State state = new State();
        state.setState("DQ");
        StateDao instance = new StateDao(true);

        instance.create(state);

        State secondState = new State();
        secondState.setState("SG");

        State thirdState = new State();
        thirdState.setState("FG");

        instance.create(secondState);
        instance.create(thirdState);

        assertEquals(5, instance.size());

        assertTrue(instance.getList().contains("SG"));
        assertTrue(instance.getList().contains("FG"));
        assertTrue(instance.getList().contains("DQ"));
        assertTrue(instance.getList().contains("Mexico"));
        assertTrue(instance.getList().contains("GH"));


    }

    @Test
    public void testEncodeAndDecode() {

        // The true parameter in the StateDao constructor signifies a test.
        StateDao stateDao = new StateDao(true);
        State testState = new State();

        String stateName = "UK";
        testState.setState(stateName);

        // Create the file in the Dao.
        State returnedState = stateDao.create(testState);

        // Verify that the state object that the create method passed back
        // was the same one it was given.
        assertEquals(testState, returnedState);

        double taxDouble = 8.25;
        returnedState.setStateTax(taxDouble);

        // Use the update method to save this new text to file.
        stateDao.update(testState);

//        String country = "USA";
//        String type = "mailing";
//        String zipCode = "88775";
//        String poBox = "21";
//        String firstName = "First and last name here";
//        String street = "3589 Street Road";
//        String state = "Michigan";
        // Set some text in the state file.
        //returnedState.setNoteString("This Is a test state.");
//        returnedState.setCity(city);
//        returnedState.setCountry(country);
//        returnedState.setType(type);
//        returnedState.setZipcode(zipCode);
//        returnedState.setPoBox(poBox);
//        returnedState.setFirstName(firstName);
//        returnedState.setStreetState(street);
//        returnedState.setState(state);
        // Load a new instance of the StateDao.
        StateDao secondDao = new StateDao(true);

        // Pull a state  using the id number recorded earlier.
        State thirdState = secondDao.get(stateName);

        assertTrue(thirdState != null);

        // Check that the update method saved the new text.
        assertEquals(taxDouble, thirdState.getStateTax(), 1e-8);
        assertEquals(stateName, thirdState.getState());

        // Delete the test state.
        secondDao.delete(thirdState);

        // Load a third instance of the Dao and verify that 
        // the state was deleted from the file.
        StateDao thirdDao = new StateDao(true);
        assertEquals(thirdDao.get(stateName), null);

    }

//    
//    /**
//     * Test of create method, of class StateDao.
//     */
//    @Test
//    public void testCreate_State_String() {
//        System.out.println("create");
//        State state = null;
//        String stateName = "";
//        StateDao instance = new StateDao();
//        State expResult = null;
//        State result = instance.create(state, stateName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of create method, of class StateDao.
//     */
//    @Test
//    public void testCreate_String_State() {
//        System.out.println("create");
//        String stateName = "";
//        State state = null;
//        StateDao instance = new StateDao();
//        State expResult = null;
//        State result = instance.create(stateName, state);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get method, of class StateDao.
//     */
//    @Test
//    public void testGet() {
//        System.out.println("get");
//        String name = "";
//        StateDao instance = new StateDao();
//        State expResult = null;
//        State result = instance.get(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of update method, of class StateDao.
//     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        State state = null;
//        StateDao instance = new StateDao();
//        instance.update(state);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete method, of class StateDao.
//     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        State state = null;
//        StateDao instance = new StateDao();
//        instance.delete(state);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getList method, of class StateDao.
//     */
//    @Test
//    public void testGetList() {
//        System.out.println("getList");
//        StateDao instance = new StateDao();
//        List<String> expResult = null;
//        List<String> result = instance.getList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of size method, of class StateDao.
//     */
//    @Test
//    public void testSize() {
//        System.out.println("size");
//        StateDao instance = new StateDao();
//        int expResult = 0;
//        int result = instance.size();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
