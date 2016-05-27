/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author apprentice
 */
public class Factorizer implements Game {

    int numberOfFactors = 0;
    int sumOfFactors = 0;

//    public void run() {
//        //if (consoleIO == null){
//        ApplicationContext ctx = com.mycompany.aop.ApplicationContextProvider.getApplicationContext();
//        ConsoleIO consoleIO = ctx.getBean("consoleIo", ConsoleIO.class);
//
//        run(consoleIO);
//    }

    public void run(ConsoleIO consoleIO) {

        numberOfFactors = 0;
        sumOfFactors = 0;

        int factorizedNumber = consoleIO.getUserIntInputRange("Please Enter A Number To Be Factorized:", 1, Integer.MAX_VALUE);

        factorizingCalculationsLoop(factorizedNumber, consoleIO);

        displayIsPerfectNumber(sumOfFactors, factorizedNumber, consoleIO);

        displayIsPrimeNumber(numberOfFactors, consoleIO, factorizedNumber);

    }

    public void factorizingCalculationsLoop(int factorizedNumber, ConsoleIO consoleIO) {
        for (int testNumber = 1; testNumber <= factorizedNumber; testNumber++) {
            factorizingCalculations(factorizedNumber, testNumber, consoleIO);
        }
    }

    public void factorizingCalculations(int factorizedNumber, int testNumber, ConsoleIO consoleIO) {
        if (isNumberEvenlyDivisible(factorizedNumber, testNumber)) {

            numberOfFactors++;

            if (testNumber != factorizedNumber) {
                sumOfFactors += testNumber;
                consoleIO.printStringToConsole(testNumber + "");
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

    public void displayIsPrimeNumber(int numberOfFactors, ConsoleIO consoleIO, int factorizedNumber) {
        if (isPrimeNumber(numberOfFactors)) {
            consoleIO.printStringToConsole(factorizedNumber + " is a prime number.");
        } else {
            consoleIO.printStringToConsole(factorizedNumber + " is not a prime number.");
        }
    }

    public void displayIsPerfectNumber(int sumOfFactors, int factorizedNumber, ConsoleIO consoleIO) {
        if (isPerfectNumber(sumOfFactors, factorizedNumber)) {
            consoleIO.printStringToConsole(factorizedNumber + " is a perfect number.");
        } else {
            consoleIO.printStringToConsole(factorizedNumber + " is not a perfect number.");
        }
    }

    public static boolean isPrimeNumber(int numberOfFactors) {
        return numberOfFactors == 2;
    }

    public static boolean isPerfectNumber(int sumOfFactors, int factorizedNumber) {
        return sumOfFactors == factorizedNumber;
    }

    @Override
    public String getName() {
        return "Factorizer";
    }
}
