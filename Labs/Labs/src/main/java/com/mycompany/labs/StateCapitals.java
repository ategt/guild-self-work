/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.Set;

/**
 *
 * @author apprentice
 */
public class StateCapitals {

    java.util.HashMap<String, String> capitalsMap = new java.util.HashMap<>();
    ConsoleIO consoleIO = new ConsoleIO();
    
    public StateCapitals(){
        loadCapitalsMap();
    }

    public void printCaptials() {
        
        consoleIO.printStringToConsole("CAPTIALS:\n=========");

        java.util.Collection<String> values = capitalsMap.values();

        for (String value : values) {
            consoleIO.printStringToConsole(value);

        }

    }

    public void printStatesAndCapitals() {
        Set<String> keySet = capitalsMap.keySet();

        consoleIO.printStringToConsole("STATE/CAPTIAL PAIRS:\n" +
"====================");
        
        for (String key : keySet) {
            consoleIO.printStringToConsole(key + " : " + capitalsMap.get(key));
        }

    }

    public void printStates() {

        consoleIO.printStringToConsole("STATES:\n"
                + "=======");

        Set<String> keySet = capitalsMap.keySet();

        for (String key : keySet) {
            consoleIO.printStringToConsole(key);
        }

    }

    public void loadCapitalsMap() {
        capitalsMap.put("Alabama", "Montgomery");
        capitalsMap.put("Alaska", "Juneau");
        capitalsMap.put("Arizona", "Phoenix");
        capitalsMap.put("Arkansas", "Little Rock");
        capitalsMap.put("California", "Sacramento");
        capitalsMap.put("Colorado", "Denver");
        capitalsMap.put("Connecticut", "Hartford");
        capitalsMap.put("Delaware", "Dover");
        capitalsMap.put("Florida", "Tallahassee");
        capitalsMap.put("Georgia", "Atlanta");
        capitalsMap.put("Hawaii", "Honolulu");
        capitalsMap.put("Idaho", "Boise");
        capitalsMap.put("Illinois", "Springfield");
        capitalsMap.put("Indiana", "Indianapolis");
        capitalsMap.put("Iowa", "Des Moines");
        capitalsMap.put("Kansas", "Topeka");
        capitalsMap.put("Kentucky", "Frankfort");

        capitalsMap.put("State", "Capital");
        capitalsMap.put("Louisiana", "Baton Rouge");
        capitalsMap.put("Maine", "Augusta");
        capitalsMap.put("Maryland", "Annapolis");
        capitalsMap.put("Massachusetts", "Boston");
        capitalsMap.put("Michigan", "Lansing");
        capitalsMap.put("Minnesota", "Saint Paul");
        capitalsMap.put("Mississippi", "Jackson");
        capitalsMap.put("Missouri", "Jefferson City");
        capitalsMap.put("Montana", "Helena");
        capitalsMap.put("Nebraska", "Lincoln");
        capitalsMap.put("Nevada", "Carson City");
        capitalsMap.put("New Hampshire", "Concord");
        capitalsMap.put("New Jersey", "Trenton");
        capitalsMap.put("New Mexico", "Santa Fe");
        capitalsMap.put("New York", "Albany");
        capitalsMap.put("North Carolina", "Raleigh");
        capitalsMap.put("North Dakota", "Bismarck");

        capitalsMap.put("State", "Capital");
        capitalsMap.put("Ohio", "Columbus");
        capitalsMap.put("Oklahoma", "Oklahoma City");
        capitalsMap.put("Oregon", "Salem");
        capitalsMap.put("Pennsylvania", "Harrisburg");
        capitalsMap.put("Rhode Island", "Providence");
        capitalsMap.put("South Carolina", "Columbia");
        capitalsMap.put("South Dakota", "Pierre");
        capitalsMap.put("Tennessee", "Nashville");
        capitalsMap.put("Texas", "Austin");
        capitalsMap.put("Utah", "Salt Lake City");
        capitalsMap.put("Vermont", "Montpelier");
        capitalsMap.put("Virginia", "Richmond");
        capitalsMap.put("Washington", "Olympia");
        capitalsMap.put("West Virginia", "Charleston");
        capitalsMap.put("Wisconsin", "Madison");
        capitalsMap.put("Wyoming", "Cheyenne");
    }

}
