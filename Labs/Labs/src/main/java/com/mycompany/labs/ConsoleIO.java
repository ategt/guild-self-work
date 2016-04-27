/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.Scanner;

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
    public Number getUserInputValidationLoop(String prompt, int choice) {
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
    public Number getUserInputValidationLoop(Number input, String prompt, int choice) {
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

    public String getUserStringInput(String prompt) {
        printStringToConsole(prompt);
        return keyboard.nextLine();
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

}
