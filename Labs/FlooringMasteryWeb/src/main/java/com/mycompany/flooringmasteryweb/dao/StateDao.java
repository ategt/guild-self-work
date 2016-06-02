/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryweb.dao;

import com.mycompany.flooringmasteryweb.dto.State;
import com.mycompany.flooringmasteryweb.utilities.StateFileIO;
import com.mycompany.flooringmasteryweb.utilities.StateFileIOImplementation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class StateDao {

    private java.util.Map<String, State> statesMap;
    private File stateDataFile = new File("StatesData.txt");
    private StateFileIO fileIo;

    public StateDao(ConfigDao configDao) {

        this.fileIo = new StateFileIOImplementation(this);
        stateDataFile = configDao.get().getTaxesFile();

        statesMap = fileIo.decode(stateDataFile);

        if (statesMap == null) {
            statesMap = new java.util.HashMap();
            System.out.println("The list was empty, making a new one.");
        }
    }

    public State create(State state) {
        if (state == null) {
            return null;
        } else {
            return create(state.getState(), state);
        }
    }

    public State create(State state, String stateName) {
        return create(stateName, state);
    }

    /**
     * The state Name must be the two character state postal code abbreviation
     * and must match the getState() method of the passed in state object.
     *
     * @param stateName
     * @param state
     * @return
     */
    public State create(String stateName, State state) {
        State returnedState = null;

        if (state.getState() == null) {
        } else if (stateName == null) {
        } else if (stateName.length() != 2) {
        } else if (stateName.equals(state.getState())) {

            String postalCode = stateName.toUpperCase();

            if (!statesMap.containsKey(postalCode)) {

                state.setState(postalCode);
                statesMap.put(postalCode, state);

                fileIo.encode(stateDataFile, getList());

                returnedState = state;
            }
        } else {
            // Look up how to throw exceptions and consider that here.
        }
        return returnedState;
    }

    public State get(String name) {
        String input = null;
        for (String stateTest : statesMap.keySet()) {
            if (name.equalsIgnoreCase(stateTest)) {
                input = stateTest;
                break;
            }
        }
        return statesMap.get(input);
    }

    public void update(State state) {
        State foundState = statesMap.get(state.getState());

        if (foundState != null) {

            if (foundState.getState().equals(state.getState())) {
                statesMap.remove(foundState.getState());
                statesMap.put(state.getState(), state);

                fileIo.encode(stateDataFile, getList());
            } else {
                System.out.println("Throwing a State Not Found exception!!!!");
                // Look up exception throwing and consider putting one here, too!
            }
        } else {
            System.out.println("Throwing a State is null exception!!!!");
            // Look up exception throwing and consider putting one here, too!
        }
    }

    public void delete(State state) {

        if (statesMap.containsKey(state.getState())) {
            statesMap.remove(state.getState());
            fileIo.encode(stateDataFile, getList());
        } else {
            System.out.println("Throwing a State Not Found exception!!!!");
            // Look up exception throwing and consider putting one here, too!

        }
    }

    public List<String> getList() {
        return new ArrayList(statesMap.keySet());
    }

    public int size() {
        return statesMap.size();
    }

}
