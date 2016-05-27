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
public class InterestCalculator implements Game {

    public void run() {
    ApplicationContext ctx = com.mycompany.aop.ApplicationContextProvider.getApplicationContext();    
    ConsoleIO consoleIO =  ctx.getBean("consoleIo", ConsoleIO.class);
    run(consoleIO);
    }
    
    public void run(ConsoleIO consoleIO){

        // Ask the user for lots of input.
        float annualInterestRate = consoleIO.getUserFloatRange("Please enter the annual interest rate as a percentage.", 0, Integer.MAX_VALUE);
        float initialInvestment = consoleIO.getUserFloatRange("Please enter the starting principle.", 0, Integer.MAX_VALUE);
        float initialYears = consoleIO.getUserFloatRange("Please enter the time frame for this investment in years.", 0, Integer.MAX_VALUE);
        int compoundingsPerYear = consoleIO.getUserIntInputRange("How many times a year is this investment compounded?\n"
                + " 1) Yearly\n 4) Quarterly\n 12) Monthly\n 365) Daily", 0, Integer.MAX_VALUE);

        float yearNumber = 0;
        float currentBalance = initialInvestment;
        float interestForCurrentYear = 0;
        float interestEarnedForCurrentCompoundingPeriod = 0;
        int totalCompoundingPeriods = calculateCompoundingPeriods(initialYears, compoundingsPerYear);
        float interestRateForCompoundingPeriod = calculateInterestRateForCompoundingPeriod(annualInterestRate, compoundingsPerYear);

        for (int currentCompoundingPeriod = 0; currentCompoundingPeriod < totalCompoundingPeriods; currentCompoundingPeriod++) {

            if (isBeginingOfYear(currentCompoundingPeriod, compoundingsPerYear)) {
                tasksForTheBeginingOfTheYear(currentCompoundingPeriod, compoundingsPerYear, consoleIO, currentBalance);
            }

            //   This is the formula from the Lab PDF.
            //CurrentBalance * (1 + (QuarterlyInterestRate / 100))
            interestEarnedForCurrentCompoundingPeriod = currentBalance * (interestRateForCompoundingPeriod);

            interestForCurrentYear += interestEarnedForCurrentCompoundingPeriod;
            currentBalance += interestEarnedForCurrentCompoundingPeriod;

            if (isEndOfYear(currentCompoundingPeriod, compoundingsPerYear)) {
                tasksForTheEndOfYear(consoleIO, interestForCurrentYear, currentBalance);
            }
        }
    }

    public static float calculateInterestRateForCompoundingPeriod(float annualInterestRate, int compoundingsPerYear) {
        return (annualInterestRate / compoundingsPerYear) / 100;
    }

    public void tasksForTheEndOfYear(ConsoleIO consoleIO, float interestForCurrentYear, float currentBalance) {
        consoleIO.printStringToConsole("Total interest earned for this year: " + Math.round(interestForCurrentYear));
        consoleIO.printStringToConsole("Balance at the end of the year: " + Math.round(currentBalance));
        consoleIO.printStringToConsole("");
    }

    public void tasksForTheBeginingOfTheYear(int currentCompoundingPeriod, int compoundingsPerYear, ConsoleIO consoleIO, float currentBalance) {
        float yearNumber;
        float interestEarnedForCurrentCompoundingPeriod;
        yearNumber = calculateYearNumber(currentCompoundingPeriod, compoundingsPerYear);
        //System.out.println("At the begining of year " + (yearNumber + 1) + " your starting balance would be " + Math.round(currentBalance));
        consoleIO.printStringToConsole("At the begining of year " + (yearNumber + 1) + " your starting balance would be " + Math.round(currentBalance));
        // reset the interest for the year
        interestEarnedForCurrentCompoundingPeriod = 0;
    }

    public static boolean isEndOfYear(int currentCompoundingPeriod, int compoundingsPerYear) {
        return (currentCompoundingPeriod % compoundingsPerYear) == (compoundingsPerYear - 1);
    }

    public static int calculateYearNumber(int currentCompoundingPeriod, int compoundingsPerYear) {
        return currentCompoundingPeriod / compoundingsPerYear;
    }

    public static boolean isBeginingOfYear(int currentCompoundingPeriod, int compoundingsPerYear) {
        return currentCompoundingPeriod % compoundingsPerYear == 0;
    }

    public static int calculateCompoundingPeriods(float initialYears, int compoundingsPerYear) {
        return Math.round(initialYears * compoundingsPerYear);
    }

    public float promptUserFloat(String promptString) {
        Scanner keyboard = new Scanner(System.in);
        float userInput = 0;

        boolean validInitialInvestment = false;

        while (!validInitialInvestment) {
            System.out.println(promptString);
            userInput = keyboard.nextFloat();

            if (userInput > 0) {
                //System.out.println("That value is valid.");
                validInitialInvestment = true;
            } else {
                System.out.println("This value is invalid.");
            }
        }

        return userInput;

    }

    public int promptUserInt(String promptString) {
        Scanner keyboard = new Scanner(System.in);
        int userInput = 0;

        boolean validInitialInvestment = false;

        while (!validInitialInvestment) {
            System.out.println(promptString);
            userInput = keyboard.nextInt();

            if (userInput > 0) {
                //System.out.println("That value is valid.");
                validInitialInvestment = true;
            } else {
                System.out.println("This value is invalid.");
            }
        }

        return userInput;

    }

    @Override
    public String getName() {
        return "Interest Calculator";
    }

}
