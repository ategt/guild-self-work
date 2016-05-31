/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.factorizor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FactorizerLogic {

    NumberCharacteristics factorizerDto;

    public NumberCharacteristics run(int factorizedNumber) {

        factorizerDto = new NumberCharacteristics();

        factorizerDto.setNumberToBeFactorized(factorizedNumber);
        //String output = "";

        factorizerDto.setNumberOfFactors(0);
        factorizerDto.setSumOfFactors(0);

        factorizingCalculationsLoop(factorizerDto);
        //factorizerDto.setIsPrime(displayIsPrimeNumber(factorizerDto.getFactors().size(), factorizerDto.getNumberToBeFactorized()));
        factorizerDto.setIsPrime(isPrimeNumber(factorizerDto.getFactors().size()));
        factorizerDto.setIsPerfect(isPerfectNumber(factorizerDto.getSumOfFactors(), factorizerDto.getNumberToBeFactorized()));
//isPerfectNumber
        return factorizerDto;

    }

    //public String factorizingCalculationsLoop(NumberCharacteristics factorizerDto) {
    public void factorizingCalculationsLoop(NumberCharacteristics factorizerDto) {
        //java.util.List<String> output = ArrayList();

        for (int testNumber = 1; testNumber <= factorizerDto.getNumberToBeFactorized(); testNumber++) {
            //String responseString = factorizingCalculations(factorizedNumber, testNumber);
            factorizingCalculations(factorizerDto, testNumber);
            // if ("".equalsIgnoreCase(responseString.trim())) {
            //output += responseString; // + "d<br />";
            //}
        }

        calculateSumOfFactors(factorizerDto);        //return output;
        factorizerDto.setNumberOfFactors(factorizerDto.getFactors().size());
    }

    //public void factorizingCalculations(int factorizedNumber, int testNumber) {
    public void factorizingCalculations(NumberCharacteristics factorizerDto, int testNumber) {

        if (isNumberEvenlyDivisible(factorizerDto.getNumberToBeFactorized(), testNumber)) {

            if (factorizerDto.getFactors() == null) {
                factorizerDto.setFactors(new ArrayList());
            }

            factorizerDto.setNumberOfFactors(factorizerDto.getNumberOfFactors() + 1);

            if (testNumber != factorizerDto.getNumberToBeFactorized()) {
                factorizerDto.getFactors().add(testNumber);
            }

        }

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
    //public String displayIsPrimeNumber(int numberOfFactors, int factorizedNumber) {
    public boolean displayIsPrimeNumber(int numberOfFactors, int factorizedNumber) {
        if (isPrimeNumber(numberOfFactors)) {
            return true;
        } else {
            return false;
        }
    }

    public void calculateSumOfFactors(NumberCharacteristics factorizerDto) {
        List<Integer> factors = factorizerDto.getFactors();

        int sumOfFactors = 0;
        for (Integer factor : factors) {
            sumOfFactors += factor;
        }

        factorizerDto.setSumOfFactors(sumOfFactors);
    }

    public boolean displayIsPerfectNumber(int sumOfFactors, int factorizedNumber) {

        if (isPerfectNumber(sumOfFactors, factorizedNumber)) {
            return true;
        } else {
            return false;
        }

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
