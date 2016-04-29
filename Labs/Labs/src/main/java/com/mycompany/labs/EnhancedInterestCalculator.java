/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import static com.mycompany.labs.InterestCalculator.calculateYearNumber;
import java.util.ArrayList;

/**
 *
 * @author apprentice
 */
public class EnhancedInterestCalculator {

    public void run() {

        ConsoleIO consoleIO = new ConsoleIO();

        // Ask the user for lots of input.
        float annualInterestRate = consoleIO.getUserFloatRange("Please enter the annual interest rate as a percentage.", 0, Integer.MAX_VALUE);
        float initialInvestment = consoleIO.getUserFloatRange("Please enter the starting principle.", 0, Integer.MAX_VALUE);
        float initialYears = consoleIO.getUserFloatRange("Please enter the time frame for this investment in years.", 0, Integer.MAX_VALUE);
        int compoundingsPerYear = consoleIO.getUserIntInputRange("How many times a year is this investment compounded?\n"
                + " 1) Yearly\n 4) Quarterly\n 12) Monthly\n 365) Daily", 0, Integer.MAX_VALUE);

        java.util.ArrayList<CompoundingPeriod> periodList = buildCompoundingPeriodList(annualInterestRate, compoundingsPerYear, initialYears, initialInvestment);

        for (CompoundingPeriod instance : periodList) {
            consoleIO.printStringToConsole(instance.getSequenceNumber() + ") Starting Balance: " + instance.getPrettyStartingBalance()
                    + "\n\t Interest Earned:" + instance.getPrettyInterest()
                    + "\n\t Ending Balance: " + instance.getPrettyEndBalance());

        }

    }

    public java.util.ArrayList<CompoundingPeriod> buildCompoundingPeriodList(float annualInterestRate, int compoundingsPerYear, float initialYears, float initialInvestment) {
        java.util.ArrayList<CompoundingPeriod> periodList = new java.util.ArrayList<>();

        float interestRateForCompoundingPeriod = calculateInterestRateForCompoundingPeriod(annualInterestRate, compoundingsPerYear);

        int totalCompoundingPeriods = calculateCompoundingPeriods(initialYears, compoundingsPerYear);
        int sequenceNumber = 0;

        CompoundingPeriod compoundingPeriod = new CompoundingPeriod(initialInvestment, interestRateForCompoundingPeriod, sequenceNumber);
        CompoundingPeriod previousCompoundingPeriod = compoundingPeriod;
        periodList.add(compoundingPeriod);

        for (; sequenceNumber <= totalCompoundingPeriods; sequenceNumber++) {

            compoundingPeriod = new CompoundingPeriod(previousCompoundingPeriod);
            previousCompoundingPeriod.setNextInstance(compoundingPeriod);
            previousCompoundingPeriod = compoundingPeriod;

            periodList.add(compoundingPeriod);

        }

        return periodList;
    }

    public static float calculateInterestRateForCompoundingPeriod(float annualInterestRate, int compoundingsPerYear) {
        return (annualInterestRate / compoundingsPerYear) / 100;
    }

    public static int calculateCompoundingPeriods(float initialYears, int compoundingsPerYear) {
        return Math.round(initialYears * compoundingsPerYear);
    }

    public void tasksForTheEndOfYear(ConsoleIO consoleIO, float interestForCurrentYear, float currentBalance) {
        consoleIO.printStringToConsole("Total interest earned for this year: " + Math.round(interestForCurrentYear));
        consoleIO.printStringToConsole("Balance at the end of the year: " + Math.round(currentBalance));
        consoleIO.printStringToConsole("");
    }

    public void tasksForTheBeginingOfTheYear(int currentCompoundingPeriod, int compoundingsPerYear, ConsoleIO consoleIO, float currentBalance) {
        float yearNumber;
        yearNumber = calculateYearNumber(currentCompoundingPeriod, compoundingsPerYear);
        consoleIO.printStringToConsole("At the begining of year " + (yearNumber + 1) + " your starting balance would be " + Math.round(currentBalance));
    }

    
    
    
}
