/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interestcalculator;

/**
 *
 * @author apprentice
 */
public class Year {
    private int yearNumber;
    private float interestForCurrentYear;
    private float endingBalance;
    private float startingBalance;


    
//int compoundingsPerYear, float currentBalance

    /**
     * @return the yearNumber
     */
    public int getYearNumber() {
        return yearNumber;
    }

    /**
     * @param yearNumber the yearNumber to set
     */
    public void setYearNumber(int yearNumber) {
        this.yearNumber = yearNumber;
    }

    /**
     * @return the interestForCurrentYear
     */
    public float getInterestForCurrentYear() {
        return interestForCurrentYear;
    }

    /**
     * @param interestForCurrentYear the interestForCurrentYear to set
     */
    public void setInterestForCurrentYear(float interestForCurrentYear) {
        this.interestForCurrentYear = interestForCurrentYear;
    }

    /**
     * @return the endingBalance
     */
    public float getEndingBalance() {
        return endingBalance;
    }

    /**
     * @param endingBalance the endingBalance to set
     */
    public void setEndingBalance(float endingBalance) {
        this.endingBalance = endingBalance;
    }

    /**
     * @return the startingBalance
     */
    public float getStartingBalance() {
        return startingBalance;
    }

    /**
     * @param startingBalance the startingBalance to set
     */
    public void setStartingBalance(float startingBalance) {
        this.startingBalance = startingBalance;
    }
}
