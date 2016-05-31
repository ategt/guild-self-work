/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.factorizor;

/**
 *
 * @author apprentice
 */
public class FactorizerLogic {
    
    NumberCharacteristics factorizerDto;
    
    public String run(int factorizedNumber) {
        
        factorizerDto = new NumberCharacteristics();

        factorizerDto.s
        String output = "";

        //numberOfFactors = 0;
        //sumOfFactors = 0;

        factorizerDto.setNumberOfFactors(0);
        factorizerDto.setSumOfFactors(0);
        
        //int factorizedNumber = consoleIO.getUserIntInputRange("Please Enter A Number To Be Factorized:", 1, Integer.MAX_VALUE);
        //output += factorizingCalculationsLoop(factorizedNumber) + "<br />";

        //output += displayIsPerfectNumber(sumOfFactors, factorizedNumber) + "<br />";

        //output += displayIsPrimeNumber(numberOfFactors, factorizedNumber) + "<br />";

        return output;

    }

    public String factorizingCalculationsLoop(int factorizedNumber) {
        //java.util.List<String> output = ArrayList();

        String output = "";

        for (int testNumber = 1; testNumber <= factorizedNumber; testNumber++) {
            String responseString = factorizingCalculations(factorizedNumber, testNumber);
            // if ("".equalsIgnoreCase(responseString.trim())) {
            output += responseString; // + "d<br />";
            //}
        }

        return output;

    }

    public String factorizingCalculations(int factorizedNumber, int testNumber) {
        String output = "";
        if (isNumberEvenlyDivisible(factorizedNumber, testNumber)) {

            numberOfFactors++;

            if (testNumber != factorizedNumber) {
                sumOfFactors += testNumber;
                output += testNumber + "<br />";
                //consoleIO.printStringToConsole(testNumber + "");
            }

        }

        return output;
    }

    /**
     * This method performs a modulus test on two numbers, divisor and dividend,
     * to determine if one is evenly divisible by the other or not. This is
     * defined as divisor divided by dividend with no remainder.
     *
     * @param divisor
     * @param dividend
     * @return
     */
    public static boolean isNumberEvenlyDivisible(int divisor, int dividend) {
        return divisor % dividend == 0;
    }

    //public void displayIsPrimeNumber(int numberOfFactors, ConsoleIO consoleIO, int factorizedNumber) {
    public String displayIsPrimeNumber(int numberOfFactors, int factorizedNumber) {
        String output = "";

        if (isPrimeNumber(numberOfFactors)) {
            output = factorizedNumber + " is a prime number.";
        } else {
            output = factorizedNumber + " is not a prime number.";
        }

        return output;
    }

    public String displayIsPerfectNumber(int sumOfFactors, int factorizedNumber) {
        String output = "";
        if (isPerfectNumber(sumOfFactors, factorizedNumber)) {
            output = factorizedNumber + " is a perfect number.";
        } else {
            output = factorizedNumber + " is not a perfect number.";
        }
        return output;
    }

    public static boolean isPrimeNumber(int numberOfFactors) {
        return numberOfFactors == 2;
    }

    public static boolean isPerfectNumber(int sumOfFactors, int factorizedNumber) {
        return sumOfFactors == factorizedNumber;
    }

    //@Override
    public String getName() {
        return "Factorizer";
    }

}
