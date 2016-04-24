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
public class InterestCalculator {

    public static void main(String[] args) {

        float annualInterestRate = promptUserFloat("Please enter the annual interest rate as a percentage.");
        float initialInvestment = promptUserFloat("Please enter the starting principle.");
        float initialYears = promptUserFloat("Please enter the time frame for this investment in years.");

        int compoundingsPerYear = promptUserInt("How many times a year is this investment compounded?\n"
                + " 1) Yearly\n 4) Quarterly\n 12) Monthly\n 365) Daily");

        float yearNumber = 0;
        float currentBalance = initialInvestment;
        float interestForCurrentYear = 0;
        float interestForCurrentQuarter = 0;
        int totalQuarters = Math.round(initialYears * compoundingsPerYear);
        float quarterlyInterestRate = annualInterestRate / compoundingsPerYear;

        for (int currentQuarter = 0; currentQuarter < totalQuarters; currentQuarter++) {

            if (currentQuarter % compoundingsPerYear == 0) {
                yearNumber = currentQuarter / compoundingsPerYear;
                System.out.println("At the begining of year " + (yearNumber + 1) + " your starting balance would be " + Math.round(currentBalance));
                interestForCurrentQuarter = 0;
            }

            //   This is the formula from the Lab PDF.
            //CurrentBalance * (1 + (QuarterlyInterestRate / 100))
            interestForCurrentQuarter = currentBalance * (quarterlyInterestRate / 100);

            interestForCurrentYear += interestForCurrentQuarter;
            currentBalance += interestForCurrentQuarter;
            
            if ((currentQuarter % compoundingsPerYear) == (compoundingsPerYear - 1)) {
                System.out.println("Total interest earned for this year: " + Math.round(interestForCurrentYear));
                System.out.println("Balance at the end of the year: " + Math.round(currentBalance));
                System.out.println("");
            }
        }
    }

    public static float promptUserFloat(String promptString) {
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

    public static int promptUserInt(String promptString) {
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


}
