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

    public ConsoleIO() {
        this.keyboard = new Scanner(System.in);

    }

    public int getUserInputInt(String prompt) {
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
    private Number getUserInputValidationLoop(String prompt, int choice) {
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
    private Number getUserInputValidationLoop(Number input, String prompt, int choice) {
        boolean isValid = false;
        String inputString;

        while (!isValid) {

            try {

                inputString = getUserStringInput(prompt);

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
            } catch (Exception ex) {
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
    public int getUserIntInputRange(String prompt, int minValue, int maxValue, String errorMessage) {
        // Adapter to us SM's method and my phraseing.
        return getUserMinMax(prompt, minValue, maxValue, errorMessage);
    }

    public int getUserIntInputRange(String prompt, int minValue, int maxValue) {
        // Adapter to us SM's method and my phrasing.
        return getUserMinMax(prompt, minValue, maxValue);

    }

    public int getUserIntInputPositive(String prompt) {
        // Adapter to us SM's method and my phrasing.
        return getUserMinMax(prompt, 0, Integer.MAX_VALUE);

    }

    public int getUserMinMax(String prompt, int min, int max) {

        String defaultErrorMessage = "Number must be between " + min + " and " + max + ".";

        return getUserMinMax(prompt, min, max, defaultErrorMessage);
    }

    public int getUserMinMax(String prompt, int min, int max, String errorMessage) {

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

    public String getUserStringInput(String prompt) throws UserWantsOutException, UserWantsToDeleteValueException {
        printStringToConsole(prompt);
        
        String inputString = keyboard.nextLine().replace("\n", "").trim();
        String returnedString = null;
         if (inputString.equalsIgnoreCase("0") || inputString.equalsIgnoreCase("exit") || inputString.equalsIgnoreCase("x") || inputString.equalsIgnoreCase("e") || inputString.equalsIgnoreCase("ex")) {
                    throw new com.mycompany.consoleio.exceptions.UserWantsOutException("The User Has Requested To Return To The Main Menu.");
                } else if (inputString.equalsIgnoreCase("")) {
                    returnedString = null;
//                    if (allowNullDate) {
//                        valid = true;
//                    }
                } else if (inputString.equalsIgnoreCase("-")) {
                   // if (allowNullDate) {
                        throw new com.mycompany.consoleio.exceptions.UserWantsToDeleteValueException("The User Has Requested To Delete The Existing Value.");
                   // } else {
                        //throw new com.mycompany.consoleio.exceptions.UserWantsOutException("The User Has Requested To Return To The Main Menu.");

                   // }
                }
        
        returnedString = inputString;
        
        
        
        
        return returnedString;
    }

    
    // begin madness
    public java.util.Date getUserDate(String prompt, boolean allowNullDate) throws UserWantsOutException, UserWantsToDeleteDateException {
        java.util.Date passedInDate = null;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        boolean valid = false;

        while (!valid) {
            String inputText = getUserStringInput(prompt);

            try {
                if (inputText.equalsIgnoreCase("0") || inputText.equalsIgnoreCase("exit") || inputText.equalsIgnoreCase("x") || inputText.equalsIgnoreCase("e") || inputText.equalsIgnoreCase("ex")) {
                    throw new com.mycompany.consoleio.exceptions.UserWantsOutException("The User Has Requested To Return To The Main Menu.");
                } else if (inputText.equalsIgnoreCase("")) {
                    passedInDate = null;
                    if (allowNullDate) {
                        valid = true;
                    }
                } else if (inputText.equalsIgnoreCase("-")) {
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
// end madness
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public boolean getUserConfirmation() {
        return getUserConfirmation("");
    }

    public boolean getUserConfirmation(String prompt) {
        boolean confirmed = false;
        String stringResponse = getUserStringInput(prompt + "\n Please Enter \"Y\" To Confirm, Or Any Other Key To Abort.");

        if (stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("Yes")) {
            confirmed = true;
        }

        return confirmed;
    }

    public float getUserFloatInput(String prompt) {
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
    public float getUserFloatRange(String prompt, float minimumValue, float maximumValue) {
        return getUserFloatMinMax(prompt, minimumValue, maximumValue);
    }

    public float getUserFloatMinMax(String prompt, float min, float max) {

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

    public double getUserDoubleInput(String prompt) {
        Number input = 0d;
        int choice = 3;

        input = getUserInputValidationLoop(input, prompt, choice);

        return input.doubleValue();
    }

    public double getUserDoubleInputRange(String prompt, double min, double max) {

        return getUserDoubleMinMax(prompt, min, max);
    }

    public double getUserDoubleRange(String prompt, double min, double max) {

        return getUserDoubleMinMax(prompt, min, max);
    }

    public double getUserDoubleMinMax(String prompt, double min, double max) {

        double userDoubleInput = 0.0d;
        boolean isValid = false;

        while (!isValid) {
            userDoubleInput = this.getUserDoubleInput(prompt);
            if (userDoubleInput >= min && userDoubleInput <= max) {
                isValid = true;

            } else {
                isValid = false;
                printStringToConsole("Number must be between " + min + " and " + max + ".");
            }

        }
        return userDoubleInput;
    }

    public java.util.Date getUserDate(String prompt) throws UserWantsOutException {
        try {
            return getUserDate(prompt, false);
        } catch (UserWantsToDeleteDateException ex) {
            Logger.getLogger(ConsoleIO.class.getName()).log(Level.SEVERE, null, ex);
            printStringToConsole("Something Just Happened That Should Not Be Possible.\n Please Inform The Vendor.");
            printStringToConsole("The Following Message Might Help: " + ex.getMessage());
        }
        return null;
    }

    public java.util.Date getUserDate(String prompt, boolean allowNullDate) throws UserWantsOutException, UserWantsToDeleteDateException {
        java.util.Date passedInDate = null;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        boolean valid = false;

        while (!valid) {
            String inputText = getUserStringInput(prompt);

            try {
                if (inputText.equalsIgnoreCase("0") || inputText.equalsIgnoreCase("exit") || inputText.equalsIgnoreCase("x") || inputText.equalsIgnoreCase("e") || inputText.equalsIgnoreCase("ex")) {
                    throw new com.mycompany.consoleio.exceptions.UserWantsOutException("The User Has Requested To Return To The Main Menu.");
                } else if (inputText.equalsIgnoreCase("")) {
                    passedInDate = null;
                    if (allowNullDate) {
                        valid = true;
                    }
                } else if (inputText.equalsIgnoreCase("-")) {
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

}
