/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interestcalculator;

import java.io.IOException;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author apprentice
 */
@WebServlet(name = "InterestCalculator", urlPatterns = {"/InterestCalculator"})
public class InterestCalculator extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("entry.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String annualInterestRateString = request.getParameter("annualInterestRate");
        String initialInvestmentString = request.getParameter("initialInvestment");
        String initialYearsString = request.getParameter("initialYears");
        String compoundingsPerYearString = request.getParameter("compoundingsPerYear");

        float annualInterestRate = Float.parseFloat(annualInterestRateString);
        float initialInvestment = Float.parseFloat(initialInvestmentString);
        float initialYears = Float.parseFloat(initialYearsString);
        int compoundingsPerYear = Integer.parseInt(compoundingsPerYearString);

        String output = "";
        float yearNumber = 0;
        float currentBalance = initialInvestment;
        float interestForCurrentYear = 0;
        float interestEarnedForCurrentCompoundingPeriod = 0;
        int totalCompoundingPeriods = calculateCompoundingPeriods(initialYears, compoundingsPerYear);
        float interestRateForCompoundingPeriod = calculateInterestRateForCompoundingPeriod(annualInterestRate, compoundingsPerYear);

        for (int currentCompoundingPeriod = 0; currentCompoundingPeriod < totalCompoundingPeriods; currentCompoundingPeriod++) {

            if (isBeginingOfYear(currentCompoundingPeriod, compoundingsPerYear)) {
                output += tasksForTheBeginingOfTheYear(currentCompoundingPeriod, compoundingsPerYear, currentBalance) + "<br />";
            }

            //   This is the formula from the Lab PDF.
            //CurrentBalance * (1 + (QuarterlyInterestRate / 100))
            interestEarnedForCurrentCompoundingPeriod = currentBalance * (interestRateForCompoundingPeriod);

            interestForCurrentYear += interestEarnedForCurrentCompoundingPeriod;
            currentBalance += interestEarnedForCurrentCompoundingPeriod;

            if (isEndOfYear(currentCompoundingPeriod, compoundingsPerYear)) {
                output += tasksForTheEndOfYear(interestForCurrentYear, currentBalance) + "<br />";
            }
        }

        request.setAttribute("message", output);
        RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
        rd.forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

//
//    public void run(ConsoleIO consoleIO) {
//
//        // Ask the user for lots of input.
//        float annualInterestRate = consoleIO.getUserFloatRange("Please enter the annual interest rate as a percentage.", 0, Integer.MAX_VALUE);
//        float initialInvestment = consoleIO.getUserFloatRange("Please enter the starting principle.", 0, Integer.MAX_VALUE);
//        float initialYears = consoleIO.getUserFloatRange("Please enter the time frame for this investment in years.", 0, Integer.MAX_VALUE);
//        int compoundingsPerYear = consoleIO.getUserIntInputRange("How many times a year is this investment compounded?\n"
//                + " 1) Yearly\n 4) Quarterly\n 12) Monthly\n 365) Daily", 0, Integer.MAX_VALUE);
//
//        float yearNumber = 0;
//        float currentBalance = initialInvestment;
//        float interestForCurrentYear = 0;
//        float interestEarnedForCurrentCompoundingPeriod = 0;
//        int totalCompoundingPeriods = calculateCompoundingPeriods(initialYears, compoundingsPerYear);
//        float interestRateForCompoundingPeriod = calculateInterestRateForCompoundingPeriod(annualInterestRate, compoundingsPerYear);
//
//        for (int currentCompoundingPeriod = 0; currentCompoundingPeriod < totalCompoundingPeriods; currentCompoundingPeriod++) {
//
//            if (isBeginingOfYear(currentCompoundingPeriod, compoundingsPerYear)) {
//                tasksForTheBeginingOfTheYear(currentCompoundingPeriod, compoundingsPerYear, consoleIO, currentBalance);
//            }
//
//            //   This is the formula from the Lab PDF.
//            //CurrentBalance * (1 + (QuarterlyInterestRate / 100))
//            interestEarnedForCurrentCompoundingPeriod = currentBalance * (interestRateForCompoundingPeriod);
//
//            interestForCurrentYear += interestEarnedForCurrentCompoundingPeriod;
//            currentBalance += interestEarnedForCurrentCompoundingPeriod;
//
//            if (isEndOfYear(currentCompoundingPeriod, compoundingsPerYear)) {
//                tasksForTheEndOfYear(consoleIO, interestForCurrentYear, currentBalance);
//            }
//        }
//    }
    public static float calculateInterestRateForCompoundingPeriod(float annualInterestRate, int compoundingsPerYear) {
        return (annualInterestRate / compoundingsPerYear) / 100;
    }

//    public void tasksForTheEndOfYear(ConsoleIO consoleIO, float interestForCurrentYear, float currentBalance) {
//        consoleIO.printStringToConsole("Total interest earned for this year: " + Math.round(interestForCurrentYear));
//        consoleIO.printStringToConsole("Balance at the end of the year: " + Math.round(currentBalance));
//        consoleIO.printStringToConsole("");
//    }
    public String tasksForTheEndOfYear(float interestForCurrentYear, float currentBalance) {
        String output = "Total interest earned for this year: " + Math.round(interestForCurrentYear)
                + "Balance at the end of the year: " + Math.round(currentBalance);
        return output;
    }

//    public void tasksForTheBeginingOfTheYear(int currentCompoundingPeriod, int compoundingsPerYear, ConsoleIO consoleIO, float currentBalance) {
//        float yearNumber;
//        float interestEarnedForCurrentCompoundingPeriod;
//        yearNumber = calculateYearNumber(currentCompoundingPeriod, compoundingsPerYear);
//        //System.out.println("At the begining of year " + (yearNumber + 1) + " your starting balance would be " + Math.round(currentBalance));
//        consoleIO.printStringToConsole("At the begining of year " + (yearNumber + 1) + " your starting balance would be " + Math.round(currentBalance));
//        // reset the interest for the year
//        interestEarnedForCurrentCompoundingPeriod = 0;
//    }
    public String tasksForTheBeginingOfTheYear(int currentCompoundingPeriod, int compoundingsPerYear, float currentBalance) {

        float yearNumber;
        float interestEarnedForCurrentCompoundingPeriod;
        yearNumber = calculateYearNumber(currentCompoundingPeriod, compoundingsPerYear);

        String output = "At the begining of year " + (yearNumber + 1) + " your starting balance would be " + Math.round(currentBalance);
        // reset the interest for the year
        interestEarnedForCurrentCompoundingPeriod = 0;
        return output;
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

//    public float promptUserFloat(String promptString) {
//        Scanner keyboard = new Scanner(System.in);
//        float userInput = 0;
//
//        boolean validInitialInvestment = false;
//
//        while (!validInitialInvestment) {
//            System.out.println(promptString);
//            userInput = keyboard.nextFloat();
//
//            if (userInput > 0) {
//                //System.out.println("That value is valid.");
//                validInitialInvestment = true;
//            } else {
//                System.out.println("This value is invalid.");
//            }
//        }
//
//        return userInput;
//
//    }
//    public int promptUserInt(String promptString) {
//        Scanner keyboard = new Scanner(System.in);
//        int userInput = 0;
//
//        boolean validInitialInvestment = false;
//
//        while (!validInitialInvestment) {
//            System.out.println(promptString);
//            userInput = keyboard.nextInt();
//
//            if (userInput > 0) {
//                //System.out.println("That value is valid.");
//                validInitialInvestment = true;
//            } else {
//                System.out.println("This value is invalid.");
//            }
//        }
//
//        return userInput;
//
//    }
//    public int promptUserInt(String promptString) {
//        Scanner keyboard = new Scanner(System.in);
//        int userInput = 0;
//
//        boolean validInitialInvestment = false;
//
//        while (!validInitialInvestment) {
//            System.out.println(promptString);
//            userInput = keyboard.nextInt();
//
//            if (userInput > 0) {
//                //System.out.println("That value is valid.");
//                validInitialInvestment = true;
//            } else {
//                System.out.println("This value is invalid.");
//            }
//        }
//
//        return userInput;
//
//    }
}
