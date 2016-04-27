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
        int inputString = 0;
        //  Scanner keyboard = new Scanner(System.in);

        System.out.println(prompt);
        inputString = keyboard.nextInt();

        return inputString;
    }

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
                System.out.println(errorMessage);
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
        String userStringInput;
        System.out.println(prompt);
        userStringInput = keyboard.next();
        return userStringInput;
    }

    public float getUserFloatInput(String prompt) {
        float userFloatInput;
        System.out.println(prompt);
        userFloatInput = keyboard.nextFloat();
        return userFloatInput;
    }

    
    /** Returns a floating-point number between the range of minimumValue and maximumValue.
     * The method will continue looping until the user enters a float in the valid range.
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
                System.out.println("Number must be between " + min + " and " + max + ".");
            }

        }
        return userFloatInput;
    }

    public double getUserDoubleInput(String prompt) {
        double userDoubleInput;
        System.out.println(prompt);
        userDoubleInput = keyboard.nextDouble();
        return userDoubleInput;
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
                System.out.println("Number must be between " + min + " and " + max + ".");
            }

        }
        return userDoubleInput;
    }

}
