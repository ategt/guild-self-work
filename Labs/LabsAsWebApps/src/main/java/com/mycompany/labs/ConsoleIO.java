/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

/**
 *
 * @author apprentice
 */
public interface ConsoleIO {

    double getUserDoubleInput(String prompt);

    double getUserDoubleInputRange(String prompt, double min, double max);

    double getUserDoubleMinMax(String prompt, double min, double max);

    double getUserDoubleRange(String prompt, double min, double max);

    float getUserFloatInput(String prompt);

    float getUserFloatMinMax(String prompt, float min, float max);

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
    float getUserFloatRange(String prompt, float minimumValue, float maximumValue);

    int getUserInputInt(String prompt);

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
    Number getUserInputValidationLoop(String prompt, int choice);

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
    Number getUserInputValidationLoop(Number input, String prompt, int choice);

    int getUserIntInputPositive(String prompt);

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
    int getUserIntInputRange(String prompt, int minValue, int maxValue, String errorMessage);

    int getUserIntInputRange(String prompt, int minValue, int maxValue);

    int getUserMinMax(String prompt, int min, int max);

    int getUserMinMax(String prompt, int min, int max, String errorMessage);

    String getUserStringInput(String prompt);

    void printStringToConsole(String stringToPrint);

    void printToConsole(String stringToPrint);
    
}
