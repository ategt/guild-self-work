/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleio;

import com.mycompany.consoleio.exceptions.UserWantsOutException;
import com.mycompany.consoleio.exceptions.UserWantsToDeleteDateException;
import com.mycompany.consoleio.exceptions.UserWantsToDeleteValueException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class ConsoleIO {

    Scanner keyboard;
    boolean test = false;
    String input = null;

    private String[] exitArray = {"exit", "x", "e", "ex", "exi"};
    private String[] deleteCommands = {"-"};

    public ConsoleIO() {
        this.keyboard = new Scanner(System.in);

    }

    public ConsoleIO(String input, boolean test) {

        this();

        if (test) {
            this.test = test;
            this.input = input;
        }

    }

    public int getUserInputInt(String prompt) throws UserWantsOutException, UserWantsToDeleteValueException {
        Number input = 0;
        int choice = 1;

        input = getUserInputValidationLoop(input, prompt, choice);

        return input.intValue();
    }

    /**
     * The method will call another method to ask the user for input and return
     * a number Object which is parsed based on the second parameter.
     *
     * 1 - Integer 2 - Float 3 - Double
     *
     * Integer is the default.
     *
     * @param prompt
     * @param choice
     * @return
     */
    private Number getUserInputValidationLoop(String prompt, int choice) throws UserWantsOutException, UserWantsToDeleteValueException {
        Number input = 0;
        return getUserInputValidationLoop(input, prompt, choice);
    }

    /**
     * The method will ask the user for input and return a number Object which
     * is parsed based on the second parameter.
     *
     * 1 - Integer 2 - Float 3 - Double
     *
     * Integer is the default.
     *
     * @param input
     * @param prompt
     * @param choice
     * @return
     */
    private Number getUserInputValidationLoop(Number input, String prompt, int choice) throws UserWantsOutException, UserWantsToDeleteValueException {
        boolean isValid = false;
        String inputString;

        while (!isValid) {

            try {

                inputString = getUserStringInput(prompt);

                if (inputString.equalsIgnoreCase("")) {
                    inputString = input.toString();
                }

                switch (choice) {
                    case 2:
                        input = Float.parseFloat(inputString);
                        break;
                    case 3:
                        input = Double.parseDouble(inputString);
                        break;
                    default:
                        input = Integer.parseInt(inputString);
                        break;
                }
                isValid = true;

            } catch (NullPointerException ex) {
                printStringToConsole("That value is not supported.\n" + ex.getMessage());
            } catch (NumberFormatException ex) {
                printStringToConsole("That value is not supported.");
            }
        }
        return input;
    }

    /**
     * The method will call another method to ask the user for input and return
     * a number Object which is parsed based on the second parameter.
     *
     * 1 - Integer 2 - Float 3 - Double
     *
     * Integer is the default.
     *
     * @param prompt
     * @param choice
     * @return
     */
    public int getUserIntInputRange(String prompt, int minValue, int maxValue, String errorMessage) throws UserWantsOutException, UserWantsToDeleteValueException {
        // Adapter to us SM's method and my phraseing.
        return getUserMinMax(prompt, minValue, maxValue, errorMessage);
    }

    public int getUserIntInputRange(String prompt, int minValue, int maxValue) throws UserWantsOutException, UserWantsToDeleteValueException {
        // Adapter to us SM's method and my phrasing.
        return getUserMinMax(prompt, minValue, maxValue);

    }

    public int getUserIntInputPositive(String prompt) throws UserWantsOutException, UserWantsToDeleteValueException {
        // Adapter to us SM's method and my phrasing.
        return getUserMinMax(prompt, 0, Integer.MAX_VALUE);

    }

    public int getUserMinMax(String prompt, int min, int max) throws UserWantsOutException, UserWantsToDeleteValueException {

        String defaultErrorMessage = "Number must be between " + min + " and " + max + ".";

        return getUserMinMax(prompt, min, max, defaultErrorMessage);
    }

    public int getUserMinMax(String prompt, int min, int max, String errorMessage) throws UserWantsOutException, UserWantsToDeleteValueException {

        int userIntInput = 0;
        boolean isValid = false;

        while (!isValid) {
            userIntInput = this.getUserInputInt(prompt);
            if (userIntInput >= min && userIntInput <= max) {
                isValid = true;

            } else {
                isValid = false;
                printStringToConsole(errorMessage);
            }

        }
        return userIntInput;
    }

    public void printStringToConsole(String stringToPrint) {
        printToConsole(stringToPrint);
    }

    public void printToConsole(String stringToPrint) {
        System.out.println(stringToPrint);
    }

    public String getUserStringInputSimple(String prompt) {
        printStringToConsole(prompt);

        if (test) {
            return input.replace("\n", "").trim();
        }

        return keyboard.nextLine().replace("\n", "").trim();

    }

    public String getUserStringInput(String prompt) throws UserWantsOutException, UserWantsToDeleteValueException {
        String returnedString = null;

        String inputString = getUserStringInputSimple(prompt);

        //if (inputString.equalsIgnoreCase("exit") || inputString.equalsIgnoreCase("x") || inputString.equalsIgnoreCase("e") || inputString.equalsIgnoreCase("ex") || inputString.equalsIgnoreCase("exi")) {
        // Chris did not like "0" as an escape option, so I removed it. inputString.equalsIgnoreCase("0") || 
        //if (inputString.equalsIgnoreCase("exit") || inputString.equalsIgnoreCase("x") || inputString.equalsIgnoreCase("e") || inputString.equalsIgnoreCase("ex") || inputString.equalsIgnoreCase("exi")) {
        for (String exitCommand : getExitArray()) {
            if (inputString.equalsIgnoreCase(exitCommand)) {
                throw new com.mycompany.consoleio.exceptions.UserWantsOutException("The User Has Requested To Return To The Main Menu.");
            }
        }

//if (  ) {  
// Chris did not like "0" as an escape option, so I removed it. inputString.equalsIgnoreCase("0") || 
        //throw new com.mycompany.consoleio.exceptions.UserWantsOutException("The User Has Requested To Return To The Main Menu.");
        //} else if (inputString.equalsIgnoreCase("")) {
        // returnedString = null;
//                    if (allowNullDate) {
//                        valid = true;
//                    }
        //} else if (inputString.equalsIgnoreCase("-")) {
        //} else if (inputString.equalsIgnoreCase("-")) {
        // if (allowNullDate) {
        for (String deleteCommand : getDeleteCommands()) {
            if (inputString.equalsIgnoreCase(deleteCommand)) {
                throw new com.mycompany.consoleio.exceptions.UserWantsToDeleteValueException("The User Has Requested To Delete The Existing Value.");
            }
        }

        // } else {
        //throw new com.mycompany.consoleio.exceptions.UserWantsOutException("The User Has Requested To Return To The Main Menu.");
        // }
        //}
        if (inputString.equalsIgnoreCase("")) {
            returnedString = null;
        } else {
            returnedString = inputString;
        }
        return returnedString;
    }

    public boolean getUserConfirmation() throws UserWantsOutException, UserWantsToDeleteValueException {
        return getUserConfirmation("");
    }

    public boolean getUserConfirmation(String prompt) throws UserWantsOutException, UserWantsToDeleteValueException {
        return getUserConfirmation(prompt, "\n Please Enter \"Y\" To Confirm, Or Any Other Key To Abort.");
    }

    public boolean getUserConfirmation(String prompt, String explanation) throws UserWantsOutException, UserWantsToDeleteValueException {
        boolean confirmed = false;
        String stringResponse = getUserStringInput(prompt + explanation);

        if (stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("Yes")) {
            confirmed = true;
        }

        return confirmed;
    }

    public float getUserFloatInput(String prompt) throws UserWantsOutException, UserWantsToDeleteValueException {
        Number input = 0f;
        int choice = 2;

        input = getUserInputValidationLoop(input, prompt, choice);

        return input.floatValue();
    }

    /**
     * Returns a floating-point number between the range of minimumValue and
     * maximumValue. The method will continue looping until the user enters a
     * float in the valid range.
     *
     * This method is an adapter for SM's getUserFloatMinMax method.
     *
     * @param prompt
     * @param minimumValue
     * @param maximumValue
     * @return
     */
    public float getUserFloatRange(String prompt, float minimumValue, float maximumValue) throws UserWantsOutException, UserWantsToDeleteValueException {
        return getUserFloatMinMax(prompt, minimumValue, maximumValue);
    }

    public float getUserFloatMinMax(String prompt, float min, float max) throws UserWantsOutException, UserWantsToDeleteValueException {

        float userFloatInput = 0;
        boolean isValid = false;

        while (!isValid) {
            userFloatInput = this.getUserFloatInput(prompt);
            if (userFloatInput >= min && userFloatInput <= max) {
                isValid = true;

            } else {
                isValid = false;
                printStringToConsole("Number must be between " + min + " and " + max + ".");
            }

        }
        return userFloatInput;
    }

    public double getUserDoubleInput(String prompt) throws UserWantsOutException, UserWantsToDeleteValueException {
        return getUserDoubleInput(prompt, 0.0d);
    }

    public double getUserDoubleInput(String prompt, double oldValue) throws UserWantsOutException, UserWantsToDeleteValueException {
        Number input = oldValue;
        int choice = 3;

        input = getUserInputValidationLoop(input, prompt, choice);

        return input.doubleValue();
    }

    public double getUserDouble(String prompt, double min, double max) throws UserWantsOutException, UserWantsToDeleteValueException {
        return getUserDoubleInputRange(prompt, min, max);
    }

    public double getUserDoubleInputRange(String prompt, double min, double max) throws UserWantsOutException, UserWantsToDeleteValueException {

        return getUserDoubleMinMax(prompt, min, max);
    }

    public double getUserDoubleRange(String prompt, double min, double max) throws UserWantsOutException, UserWantsToDeleteValueException {

        return getUserDoubleMinMax(prompt, min, max);
    }

    public double getUserDoubleMinMax(String prompt, double min, double max) throws UserWantsOutException, UserWantsToDeleteValueException {
        return getUserDoubleMinMax(prompt, min, max, 0.0d);
    }

    public double getUserDoubleMinMax(String prompt, double min, double max, double oldValue) throws UserWantsOutException, UserWantsToDeleteValueException {

        double userDoubleInput = oldValue;
        boolean isValid = false;

        while (!isValid) {
            userDoubleInput = this.getUserDoubleInput(prompt, oldValue);
            if (userDoubleInput >= min && userDoubleInput <= max) {
                isValid = true;

            } else {
                isValid = false;
                printStringToConsole("Number must be between " + min + " and " + max + ".");
            }

        }
        return userDoubleInput;
    }

    public java.util.Date getUserDate(String prompt) throws UserWantsOutException, UserWantsToDeleteValueException {
        try {
            return getUserDate(prompt, false);
        } catch (UserWantsToDeleteDateException ex) {
            Logger.getLogger(ConsoleIO.class.getName()).log(Level.SEVERE, null, ex);
            printStringToConsole("Something Just Happened That Should Not Be Possible.\n Please Inform The Vendor.");
            printStringToConsole("The Following Message Might Help: " + ex.getMessage());
        }
        return null;
    }

    public java.util.Date getUserDate(String prompt, boolean allowNullDate) throws UserWantsOutException, UserWantsToDeleteDateException, UserWantsToDeleteValueException {
        java.util.Date passedInDate = null;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        boolean valid = false;

        while (!valid) {
            String inputText = getUserStringInput(prompt);

            try {
//                if (inputText.equalsIgnoreCase("0") || inputText.equalsIgnoreCase("exit") || inputText.equalsIgnoreCase("x") || inputText.equalsIgnoreCase("e") || inputText.equalsIgnoreCase("ex")) {
//                    throw new com.mycompany.consoleio.exceptions.UserWantsOutException("The User Has Requested To Return To The Main Menu.");
//                } else
                if (inputText.equalsIgnoreCase("")) {
                    passedInDate = null;
                    if (allowNullDate) {
                        valid = true;
                    }
                    //   } else if (inputText.equalsIgnoreCase("-")) {
                    if (allowNullDate) {
                        throw new com.mycompany.consoleio.exceptions.UserWantsToDeleteDateException("The User Has Requested To Delete The Existing Date.");
                    } else {
                        throw new com.mycompany.consoleio.exceptions.UserWantsOutException("The User Has Requested To Return To The Main Menu.");

                    }
                }

                passedInDate = dateFormat.parse(inputText);
                valid = true;
            } catch (ParseException ex) {
                //Logger.getLogger(ConsoleIO.class.getName()).log(Level.SEVERE, null, ex);
                printStringToConsole("The System Could Understand That Date. \n - Please Enter The Date In DD-MM-yyyy Format or 0 to exit. -\n");
            }

        }
        return passedInDate;
    }

    /**
     * @return the exitArray
     */
    public String[] getExitArray() {
        return exitArray;
    }

    /**
     * @param exitArray the exitArray to set
     */
    public void setExitArray(String[] exitArray) {
        this.exitArray = exitArray;
    }

    /**
     * @return the deleteCommands
     */
    public String[] getDeleteCommands() {
        return deleteCommands;
    }

    /**
     * @param deleteCommands the deleteCommands to set
     */
    public void setDeleteCommands(String[] deleteCommands) {
        this.deleteCommands = deleteCommands;
    }

}
