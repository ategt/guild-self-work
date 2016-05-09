/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.State;
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

    //List<State> states;
    java.util.Map<String, State> statesMap;
    int nextId;
    File stateDataFile = new File("StatesData.txt");

    public StateDao() {
        this(false);
    }

    protected StateDao(boolean isATest) {

        if (isATest) {
            stateDataFile = new File("StatesTestData.txt");
        }

        statesMap = decode();

        if (statesMap == null) {
            statesMap = new java.util.HashMap();
            System.out.println("The list was empty, making a new one.");
        }

        //nextId = determineNextId();
    }

    public State create(State state, String stateName) {
        return create(stateName, state);
    }

    public State create(String stateName, State state) {

        if (stateName.equals(state.getState())) {
            statesMap.put(stateName, state);
            encode();

            return state;
        } else {
            return null;  // Look up how to throw exceptions and consider that instead.
        }
    }

    public State get(String name) {
        return statesMap.get(name);

    }

    public void update(State state) {
        State foundState = statesMap.get(state.getState());

//        State found = null;
//
//        for (State currentState : states) {
//            if (currentState.getId() == state.getId()) {
//                found = currentState;
//                break;
//            }
//        }
        if (foundState != null) {

            if (foundState.getState().equals(state.getState())) {
                statesMap.remove(foundState.getState());
                statesMap.put(state.getState(), state);

                encode();
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
//        State found = null;
//
//        for (State currentState : states) {
//            if (currentState.getId() == state.getId()) {
//                found = currentState;
//                break;
//            }
//        }
//
//        if (found != null) {
//            states.remove(found);
//        }

        if (statesMap.containsKey(state.getState())) {
            statesMap.remove(state.getState());
            encode();
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
//
//    private int determineNextId() {
//        int highestId = 100;
//
//        for (State state : states) {
//            if (highestId < state.getId()) {
//                highestId = state.getId();
//            }
//        }
//
//        highestId++;
//        return highestId++;
//    }

    private void encode() {

        final String TOKEN = ",";
        final String DATAHEADER = "State,TaxRate";
        try {

            try (PrintWriter out = new PrintWriter(new FileWriter(stateDataFile))) {
                out.println(DATAHEADER);

                for (String stateName : getList()) {

                    State state = statesMap.get(stateName);

                    out.print(state.getState());
                    out.print(TOKEN);
                    out.print(state.getStateTax());
                    out.println("");
                }

                out.flush();
            }

        } catch (IOException ex) {
            Logger.getLogger(StateDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //private List<State> decode() {
    private Map<String, State> decode() {

        //List<State> stateList = new ArrayList<>();
        Map<String, State> stateList = new java.util.HashMap<>();

        final String TOKEN = ",";
        final String DATAHEADER = "State,TaxRate";

        try {

            if (!stateDataFile.exists()) {
                stateDataFile.createNewFile();
            }

            try (Scanner sc = new Scanner(new BufferedReader(new FileReader(stateDataFile)))) {
                while (sc.hasNextLine()) {
                    String currentLine = sc.nextLine();
                    if (currentLine.equalsIgnoreCase(DATAHEADER)) {

                    } else if (!currentLine.trim().isEmpty()) {

                        String[] stringParts = currentLine.split(TOKEN);

                        State state = new State();

                        String content = stringParts[0];

                        state.setState(content);

                        String stateSalesTaxString = stringParts[1];

                        try {
                            double stateSalesTax = Double.parseDouble(stateSalesTaxString);
                            state.setStateTax(stateSalesTax);
                        } catch (NumberFormatException numFmtEx) {

                        }

                        stateList.put(state.getState(), state);
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(StateDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StateDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return stateList;
    }

}
