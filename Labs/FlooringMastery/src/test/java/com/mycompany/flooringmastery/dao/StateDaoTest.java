/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.State;
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
}
