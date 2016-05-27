/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.Set;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author apprentice
 */
public class StateCapitals2 {

    private java.util.HashMap<String, Capital> capitalObjectMap = new java.util.HashMap<>();
    ConsoleIO consoleIO; // = new ConsoleIO();

    public StateCapitals2() {

        ApplicationContext ctx = com.mycompany.aop.ApplicationContextProvider.getApplicationContext();
        ConsoleIO consoleIO = ctx.getBean("consoleIo", ConsoleIO.class);
        init(consoleIO);
    }
    ///

    public StateCapitals2(ConsoleIO consoleIO) {
        init(consoleIO);
    }

    private void init(ConsoleIO consoleIO) {

        this.consoleIO = consoleIO;
        loadCapitalsMap2();

    }

    public void printCaptials() {

        consoleIO.printStringToConsole("CAPTIALS:\n=========");

        java.util.Collection<Capital> values = getCapitalObjectMap().values();

        for (Capital value : values) {
            consoleIO.printStringToConsole(value.getName());

        }

    }

    public void printStatesAndCapitals() {
        Set<String> keySet = getCapitalObjectMap().keySet();

        consoleIO.printStringToConsole("STATE/CAPTIAL PAIRS:\n"
                + "====================");

        for (String key : keySet) {
            consoleIO.printStringToConsole(key + " : " + getCapitalObjectMap().get(key).getName());
        }

    }

    public void printStatesCapitalsAndAttributes() {
        printStatesCapitalsAndAttributes(getCapitalObjectMap());
    }

    public void printStatesCapitalsAndAttributes(java.util.HashMap<String, Capital> localCapitalMap) {
        Set<String> keySet = localCapitalMap.keySet();

        consoleIO.printStringToConsole("STATE/CAPTIAL PAIRS:\n"
                + "====================");

        for (String key : keySet) {
            consoleIO.printStringToConsole(key + " - "
                    + localCapitalMap.get(key).getName() + " | Pop: "
                    + localCapitalMap.get(key).getPopulation() + " | Area: "
                    + localCapitalMap.get(key).getSquareMilage() + " sq mi");
        }

        //  Alabama - Montgomery | Pop: 205000 | Area: 156 sq mi
    }

    /**
     * Print the names of the states, that states capital, and some attributes
     * filtering out everything under the lowerLimit int.
     *
     * @param lowerLimit
     */
    public void printStatesCapitalsAndAttributes(int lowerLimit) {
        Set<String> keySet = getCapitalObjectMap().keySet();

        java.util.Map<String, Capital> filtedMap = getFilteredCapitalsMap(lowerLimit);

        consoleIO.printStringToConsole("STATE/CAPTIAL PAIRS:\n"
                + "====================");

        for (String key : keySet) {

            if (getCapitalObjectMap().get(key).getPopulation() > lowerLimit) {
                consoleIO.printStringToConsole(key + " - "
                        + getCapitalObjectMap().get(key).getName() + " | Pop: "
                        + getCapitalObjectMap().get(key).getPopulation() + " | Area: "
                        + getCapitalObjectMap().get(key).getSquareMilage() + " sq mi");
            }
        }

        //  Alabama - Montgomery | Pop: 205000 | Area: 156 sq mi
    }

    public java.util.Map getFilteredCapitalsMap(int lowerLimit) {
        Set<String> keySet = getCapitalObjectMap().keySet();

        java.util.Map<String, Capital> filteredMap = new java.util.HashMap<>();

        for (String key : keySet) {
            if (getCapitalObjectMap().get(key).getPopulation() > lowerLimit) {
                filteredMap.put(key, getCapitalObjectMap().get(key));
            }

        }

        return filteredMap;

    }

    public void printStates() {

        consoleIO.printStringToConsole("STATES:\n"
                + "=======");

        Set<String> keySet = getCapitalObjectMap().keySet();

        for (String key : keySet) {
            consoleIO.printStringToConsole(key);
        }

    }

    public Capital buildCapital(String capitalName) {
        Capital capital = null;

        java.util.Random random = new java.util.Random();

        capital = new Capital(capitalName, random.nextInt(120000000) + 40000, random.nextInt(70000000) + 20000);

        return capital;
    }

    public final void loadCapitalsMap2() {

        getCapitalObjectMap().put("Alabama", buildCapital("Montgomery"));
        getCapitalObjectMap().put("Alaska", buildCapital("Juneau"));
        getCapitalObjectMap().put("Arizona", buildCapital("Phoenix"));
        getCapitalObjectMap().put("Arkansas", buildCapital("Little Rock"));
        getCapitalObjectMap().put("California", buildCapital("Sacramento"));
        getCapitalObjectMap().put("Colorado", buildCapital("Denver"));
        getCapitalObjectMap().put("Connecticut", buildCapital("Hartford"));
        getCapitalObjectMap().put("Delaware", buildCapital("Dover"));
        getCapitalObjectMap().put("Florida", buildCapital("Tallahassee"));
        getCapitalObjectMap().put("Georgia", buildCapital("Atlanta"));
        getCapitalObjectMap().put("Hawaii", buildCapital("Honolulu"));
        getCapitalObjectMap().put("Idaho", buildCapital("Boise"));
        getCapitalObjectMap().put("Illinois", buildCapital("Springfield"));
        getCapitalObjectMap().put("Indiana", buildCapital("Indianapolis"));
        getCapitalObjectMap().put("Iowa", buildCapital("Des Moines"));
        getCapitalObjectMap().put("Kansas", buildCapital("Topeka"));
        getCapitalObjectMap().put("Kentucky", buildCapital("Frankfort"));

        getCapitalObjectMap().put("Louisiana", buildCapital("Baton Rouge"));
        getCapitalObjectMap().put("Maine", buildCapital("Augusta"));
        getCapitalObjectMap().put("Maryland", buildCapital("Annapolis"));
        getCapitalObjectMap().put("Massachusetts", buildCapital("Boston"));
        getCapitalObjectMap().put("Michigan", buildCapital("Lansing"));
        getCapitalObjectMap().put("Minnesota", buildCapital("Saint Paul"));
        getCapitalObjectMap().put("Mississippi", buildCapital("Jackson"));
        getCapitalObjectMap().put("Missouri", buildCapital("Jefferson City"));
        getCapitalObjectMap().put("Montana", buildCapital("Helena"));
        getCapitalObjectMap().put("Nebraska", buildCapital("Lincoln"));
        getCapitalObjectMap().put("Nevada", buildCapital("Carson City"));
        getCapitalObjectMap().put("New Hampshire", buildCapital("Concord"));
        getCapitalObjectMap().put("New Jersey", buildCapital("Trenton"));
        getCapitalObjectMap().put("New Mexico", buildCapital("Santa Fe"));
        getCapitalObjectMap().put("New York", buildCapital("Albany"));
        getCapitalObjectMap().put("North Carolina", buildCapital("Raleigh"));
        getCapitalObjectMap().put("North Dakota", buildCapital("Bismarck"));

        getCapitalObjectMap().put("Ohio", buildCapital("Columbus"));
        getCapitalObjectMap().put("Oklahoma", buildCapital("Oklahoma City"));
        getCapitalObjectMap().put("Oregon", buildCapital("Salem"));
        getCapitalObjectMap().put("Pennsylvania", buildCapital("Harrisburg"));
        getCapitalObjectMap().put("Rhode Island", buildCapital("Providence"));
        getCapitalObjectMap().put("South Carolina", buildCapital("Columbia"));
        getCapitalObjectMap().put("South Dakota", buildCapital("Pierre"));
        getCapitalObjectMap().put("Tennessee", buildCapital("Nashville"));
        getCapitalObjectMap().put("Texas", buildCapital("Austin"));
        getCapitalObjectMap().put("Utah", buildCapital("Salt Lake City"));
        getCapitalObjectMap().put("Vermont", buildCapital("Montpelier"));
        getCapitalObjectMap().put("Virginia", buildCapital("Richmond"));
        getCapitalObjectMap().put("Washington", buildCapital("Olympia"));
        getCapitalObjectMap().put("West Virginia", buildCapital("Charleston"));
        getCapitalObjectMap().put("Wisconsin", buildCapital("Madison"));
        getCapitalObjectMap().put("Wyoming", buildCapital("Cheyenne"));
    }
//
//    public void loadCapitalsMap() {
//        
//        capitalsMap.put("Alabama", "Montgomery");
//        capitalsMap.put("Alaska", "Juneau");
//        capitalsMap.put("Arizona", "Phoenix");
//        capitalsMap.put("Arkansas", "Little Rock");
//        capitalsMap.put("California", "Sacramento");
//        capitalsMap.put("Colorado", "Denver");
//        capitalsMap.put("Connecticut", "Hartford");
//        capitalsMap.put("Delaware", "Dover");
//        capitalsMap.put("Florida", "Tallahassee");
//        capitalsMap.put("Georgia", "Atlanta");
//        capitalsMap.put("Hawaii", "Honolulu");
//        capitalsMap.put("Idaho", "Boise");
//        capitalsMap.put("Illinois", "Springfield");
//        capitalsMap.put("Indiana", "Indianapolis");
//        capitalsMap.put("Iowa", "Des Moines");
//        capitalsMap.put("Kansas", "Topeka");
//        capitalsMap.put("Kentucky", "Frankfort");
//
//        capitalsMap.put("State", "Capital");
//        capitalsMap.put("Louisiana", "Baton Rouge");
//        capitalsMap.put("Maine", "Augusta");
//        capitalsMap.put("Maryland", "Annapolis");
//        capitalsMap.put("Massachusetts", "Boston");
//        capitalsMap.put("Michigan", "Lansing");
//        capitalsMap.put("Minnesota", "Saint Paul");
//        capitalsMap.put("Mississippi", "Jackson");
//        capitalsMap.put("Missouri", "Jefferson City");
//        capitalsMap.put("Montana", "Helena");
//        capitalsMap.put("Nebraska", "Lincoln");
//        capitalsMap.put("Nevada", "Carson City");
//        capitalsMap.put("New Hampshire", "Concord");
//        capitalsMap.put("New Jersey", "Trenton");
//        capitalsMap.put("New Mexico", "Santa Fe");
//        capitalsMap.put("New York", "Albany");
//        capitalsMap.put("North Carolina", "Raleigh");
//        capitalsMap.put("North Dakota", "Bismarck");
//
//        capitalsMap.put("State", "Capital");
//        capitalsMap.put("Ohio", "Columbus");
//        capitalsMap.put("Oklahoma", "Oklahoma City");
//        capitalsMap.put("Oregon", "Salem");
//        capitalsMap.put("Pennsylvania", "Harrisburg");
//        capitalsMap.put("Rhode Island", "Providence");
//        capitalsMap.put("South Carolina", "Columbia");
//        capitalsMap.put("South Dakota", "Pierre");
//        capitalsMap.put("Tennessee", "Nashville");
//        capitalsMap.put("Texas", "Austin");
//        capitalsMap.put("Utah", "Salt Lake City");
//        capitalsMap.put("Vermont", "Montpelier");
//        capitalsMap.put("Virginia", "Richmond");
//        capitalsMap.put("Washington", "Olympia");
//        capitalsMap.put("West Virginia", "Charleston");
//        capitalsMap.put("Wisconsin", "Madison");
//        capitalsMap.put("Wyoming", "Cheyenne");
//    }

    /**
     * @return the capitalObjectMap
     */
    public java.util.HashMap<String, Capital> getCapitalObjectMap() {
        return capitalObjectMap;
    }

}
