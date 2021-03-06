/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroom;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class PrimeFinder {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        int primeNumberCounter = 0;
        int numberOfTimesPlayed = 0;
        int totalPrimesFound = 0;

        printDescription();

        boolean playAgain = true;

        while (playAgain) {

            primeNumberCounter = 0;
            numberOfTimesPlayed++;

            int numberToTest = promptUserForAnInt("Please enter a number.", keyboard);

            primeNumberCounter = generatePrimes(numberToTest, primeNumberCounter);
            totalPrimesFound += primeNumberCounter;

            System.out.println(primeNumberCounter + " total prime numbers found.");

            playAgain = promptToPlayAgain(keyboard);

        }

        //System.out.println( "Thank you for running this program. \nYou have looped through this program " + numberOfTimesPlayed + " times.");
        System.out.println("Thank you for running this program. \nYou have found " + totalPrimesFound + " times.");

    }

    public static int generatePrimes(int numberToTest, int primeNumberCounter) {
        for (int x = 0; x <= numberToTest; x++) {
            if (isNumberPrime(x)) {

                System.out.println(x + " is a prime number.");
                primeNumberCounter++;
                // totalPrimesFound++;
            } else {
                //  System.out.println(x + " is not a prime number.");
            }

        }
        return primeNumberCounter;
    }

    public static boolean promptToPlayAgain(Scanner keyboard) {
        boolean playAgain;
        int exitNumber = promptUserForAnInt("Please enter '1' if you would like to run this program again,\n"
                + " enter any other number to exit.", keyboard);
        if (exitNumber == 1) {
            playAgain = true;
        } else {
            playAgain = false;
        }
        return playAgain;
    }

    public static int promptUserForAnInt(String promptString, Scanner keyboard) {

        boolean isValidNumber = false;
        int inputInt = 0;

        while (!isValidNumber) {
            System.out.println(promptString);
            inputInt = keyboard.nextInt();

            if (inputInt > 0) {
                isValidNumber = true;
            } else {
                isValidNumber = false;
                System.out.println("That input is not supported by this program.");
            }

        }

        return inputInt;
    }

    public static boolean isNumberPrime(int numberToBeTested) {
        boolean isPrime = false;

        // int numberToBeTested = 0;
        int primeNumberHolder = 0;

        for (int result = 1; result <= numberToBeTested; result++) {
            if (numberToBeTested % result == 0) {

                primeNumberHolder++;

            }

        }

        if (primeNumberHolder == 2) {
            // System.out.println(factorizedNumber + " is a prime number.");
            isPrime = true;
        } else {
            //System.out.println(factorizedNumber + " is not a prime number.");
            isPrime = false;
        }

        return isPrime;
    }

    public static void printDescription(){
                System.out.println("This program will find all of the prime numbers between 1 and the number that you enter.");
    }
    
}
