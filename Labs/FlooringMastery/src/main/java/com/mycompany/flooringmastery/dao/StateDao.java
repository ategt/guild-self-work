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

        states = decode();

        if (states == null) {
            states = new ArrayList();
            System.out.println("The list was empty, making a new one.");
        }

        nextId = determineNextId();
    }

    public State create(State state) {
        state.setId(nextId);
        nextId++;

        states.add(state);

        encode();

        return state;
    }

    public State get(Integer id) {

        for (State state : states) {
            if (state != null) {
                if (state.getId() == id) {
                    return state;
                }
            }
        }

        return null;
    }

    public void update(State state) {
        State found = null;

        for (State currentState : states) {
            if (currentState.getId() == state.getId()) {
                found = currentState;
                break;
            }
        }

        if (found != null) {
            states.remove(found);
            states.add(state);
        }

        encode();

    }

    public void delete(State state) {
        State found = null;

        for (State currentState : states) {
            if (currentState.getId() == state.getId()) {
                found = currentState;
                break;
            }
        }

        if (found != null) {
            states.remove(found);
        }

        encode();

    }

    public List<State> getList() {

        return states;
    }

    public int size() {
        return states.size();
    }

    private int determineNextId() {
        int highestId = 100;

        for (State state : states) {
            if (highestId < state.getId()) {
                highestId = state.getId();
            }
        }

        highestId++;
        return highestId++;
    }

    private void encode() {

        final String TOKEN = ",";
        final String DATAHEADER = "State,TaxRate";
        try {

            PrintWriter out = new PrintWriter(new FileWriter(stateDataFile));

            out.println(DATAHEADER);

            for (State state : states) {

                out.print(state.getState());
                out.print(TOKEN);
                out.print(state.getStateTax());
                out.println("");
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(StateDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<State> decode() {

        List<State> stateList = new ArrayList<>();

        final String TOKEN = ",";
        final String DATAHEADER = "State,TaxRate";

        try {

            if (!stateDataFile.exists()) {
                stateDataFile.createNewFile();
            }

            Scanner sc = new Scanner(new BufferedReader(new FileReader(stateDataFile)));

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

                    stateList.add(state);
                }
            }
            sc.close();

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
