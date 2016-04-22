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
        Scanner keyboard = new Scanner(System.in);

        float annualInterestRate = 0;
        float initialInvestment = 0;
        float initialYears = 0;

        boolean validInterestRate = false;

        while (!validInterestRate) {
            System.out.println("Please enter the annual interest rate as a percentage.");
            annualInterestRate = keyboard.nextFloat();

            if (annualInterestRate > 0) {
                //System.out.println("That rate is valid.");
                validInterestRate = true;
            } else {
                System.out.println("This rate is invalid.");
            }
        }

        boolean validInitialInvestment = false;

        while (!validInitialInvestment) {
            System.out.println("Please enter the starting principle.");
            initialInvestment = keyboard.nextFloat();

            if (initialInvestment > 0) {
                //System.out.println("That value is valid.");
                validInitialInvestment = true;
            } else {
                System.out.println("This value is invalid.");
            }
        }

        boolean validInitialYears = false;

        while (!validInitialYears) {
            System.out.println("Please enter the time frame for this investment in years.");
            initialYears = keyboard.nextFloat();

            if (initialYears > 0) {
                //System.out.println("That value is valid.");
                validInitialYears = true;
            } else {
                System.out.println("This value is invalid.");
            }
        }

        float yearNumber = 0;
        float currentBalance = initialInvestment;
        float interestForCurrentYear = 0;
        float interestForCurrentQuarter = 0;
        int totalQuarters = Math.round(initialYears * 4);
        float quarterlyInterestRate = annualInterestRate / 4;

        for (int currentQuarter = 0; currentQuarter < totalQuarters; currentQuarter++) {

            if (currentQuarter % 4 == 0) {
                yearNumber = currentQuarter / 4;
                System.out.println("At the begining of year " + yearNumber + " your starting balance would be " + Math.round(currentBalance));
                interestForCurrentQuarter = 0;
            }

            //   This is the formula from the Lab PDF.
            //CurrentBalance * (1 + (QuarterlyInterestRate / 100))
            interestForCurrentQuarter = currentBalance * (quarterlyInterestRate / 100);

            interestForCurrentYear += interestForCurrentQuarter;
            currentBalance += interestForCurrentQuarter;

            if (currentQuarter % 4 == 3) {
                System.out.println("Total interest earned for this year: " + Math.round(interestForCurrentYear));
                System.out.println("Balance at the end of the year: " + Math.round(currentBalance));
                System.out.println("");
            }
        }

    }
}
