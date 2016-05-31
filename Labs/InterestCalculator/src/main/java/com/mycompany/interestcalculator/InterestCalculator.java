/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interestcalculator;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
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

        //String output = "";
        //float yearNumber = 0;
        float currentBalance = initialInvestment;
        float interestForCurrentYear = 0;
        float interestEarnedForCurrentCompoundingPeriod = 0;
        int totalCompoundingPeriods = calculateCompoundingPeriods(initialYears, compoundingsPerYear);
        float interestRateForCompoundingPeriod = calculateInterestRateForCompoundingPeriod(annualInterestRate, compoundingsPerYear);

        //List<String> tableRow = new ArrayList();
        List<Year> yearList = new ArrayList();
        Year year = new Year();

        for (int currentCompoundingPeriod = 0; currentCompoundingPeriod < totalCompoundingPeriods; currentCompoundingPeriod++) {

            String output = "";
            if (isBeginingOfYear(currentCompoundingPeriod, compoundingsPerYear)) {
                year = new Year();
                //output += tasksForTheBeginingOfTheYear(currentCompoundingPeriod, compoundingsPerYear, currentBalance) + "<br />";
                //output += tasksForTheBeginingOfTheYear(currentCompoundingPeriod, compoundingsPerYear, currentBalance); // + "<br />";
                tasksForTheBeginingOfTheYear(currentCompoundingPeriod, compoundingsPerYear, currentBalance, year); // + "<br />";
            }

            //   This is the formula from the Lab PDF.
            //CurrentBalance * (1 + (QuarterlyInterestRate / 100))
            interestEarnedForCurrentCompoundingPeriod = currentBalance * (interestRateForCompoundingPeriod);

            interestForCurrentYear += interestEarnedForCurrentCompoundingPeriod;
            currentBalance += interestEarnedForCurrentCompoundingPeriod;

            if (isEndOfYear(currentCompoundingPeriod, compoundingsPerYear)) {
                tasksForTheEndOfYear(interestForCurrentYear, currentBalance, year); // +  "</tr>"; //"<br />";
                yearList.add(year);
            }

            //tableRow.add(output);
        }

        List<String> outputList = new ArrayList();

        for (Year oneYear : yearList) {
            outputList.add("<td>" + oneYear.getYearNumber() + "</td>"
                    + "<td>" + displayCurrency(request.getLocale(), oneYear.getStartingBalance()) + "</td>"
                    + "<td>" + displayCurrency(request.getLocale(), oneYear.getInterestForCurrentYear()) + "</td>"
                    + "<td>" + displayCurrency(request.getLocale(), oneYear.getEndingBalance()) + "</td>");

        }
        request.setAttribute("tableRow", outputList);
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

    public static float calculateInterestRateForCompoundingPeriod(float annualInterestRate, int compoundingsPerYear) {
        return (annualInterestRate / compoundingsPerYear) / 100;
    }

    public void tasksForTheEndOfYear(float interestForCurrentYear, float currentBalance, Year year) {
        year.setInterestForCurrentYear(interestForCurrentYear);
        year.setEndingBalance(currentBalance);
//        String output = "<td class=\"colum3\">" + Math.round(interestForCurrentYear) + "</td>"
//                + "<td class=\"colum4\">" + Math.round(currentBalance) + "</td>";
        //return output;
    }
//    public String tasksForTheEndOfYear(float interestForCurrentYear, float currentBalance) {
//        String output = "<td class=\"colum3\">" + Math.round(interestForCurrentYear) + "</td>"
//                + "<td class=\"colum4\">" + Math.round(currentBalance) + "</td>";
//        return output;
//    }

//    public String tasksForTheEndOfYear(float interestForCurrentYear, float currentBalance) {
//        String output = "Total interest earned for this year: " + Math.round(interestForCurrentYear)
//                + "<Br />Balance at the end of the year: " + Math.round(currentBalance);
//        return output;
//    }
    public void tasksForTheBeginingOfTheYear(int currentCompoundingPeriod, int compoundingsPerYear, float currentBalance, Year year) {

        float yearNumber;
        float interestEarnedForCurrentCompoundingPeriod;
        yearNumber = calculateYearNumber(currentCompoundingPeriod, compoundingsPerYear);

        year.setYearNumber(Math.round(yearNumber));
        year.setStartingBalance(currentBalance);
        //String output = "At the begining of year " + (yearNumber + 1) + " your starting balance would be " + Math.round(currentBalance);
        //String output = "<td class=\"colum1\">" + (yearNumber + 1) + "</td>" + "<td class=\"colum2\">" + Math.round(currentBalance) + "</td>";
        // reset the interest for the year
        interestEarnedForCurrentCompoundingPeriod = 0;
        //return output;
    }

//    public String tasksForTheBeginingOfTheYear(int currentCompoundingPeriod, int compoundingsPerYear, float currentBalance, Year year) {
//
//        float yearNumber;
//        float interestEarnedForCurrentCompoundingPeriod;
//        yearNumber = calculateYearNumber(currentCompoundingPeriod, compoundingsPerYear);
//
//        year.setYearNumber(Math.round(yearNumber));
//        //String output = "At the begining of year " + (yearNumber + 1) + " your starting balance would be " + Math.round(currentBalance);
//        String output = "<td class=\"colum1\">" + (yearNumber + 1) + "</td>" + "<td class=\"colum2\">" + Math.round(currentBalance) + "</td>";
//        // reset the interest for the year
//        interestEarnedForCurrentCompoundingPeriod = 0;
//        return output;
//    }
//
//    public static boolean isEndOfYear(int currentCompoundingPeriod, int compoundingsPerYear) {
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
    
        public String displayCurrency(Locale currentLocale, float currencyAmount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        return currencyFormatter.format(currencyAmount);
    }


}
