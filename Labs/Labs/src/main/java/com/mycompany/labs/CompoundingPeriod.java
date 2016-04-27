/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

/**
 *
 * @author apprentice
 */
public class CompoundingPeriod {

    private float startingBalance;
    private float endingBalance;
    private float interestRate;
    private float interestEarnedForCurrentPeriod;
    private float totalInterestEarned;

    private boolean isBeginingOfYear;
    private boolean isEndOfYear;
    
    private int sequenceNumber;

    public CompoundingPeriod(float startingBalance, float interestRate, int sequenceNumber ){
        setStartingBalance(startingBalance);
        setInterestRate(interestRate);
        setSequenceNumber(sequenceNumber);
        
        
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
     * @return the interestRate
     */
    public float getInterestRate() {
        return interestRate;
    }

    /**
     * @param interestRate the interestRate to set
     */
    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * @return the interestEarnedForCurrentPeriod
     */
    public float getInterestEarnedForCurrentPeriod() {
        return interestEarnedForCurrentPeriod;
    }

    /**
     * @param interestEarnedForCurrentPeriod the interestEarnedForCurrentPeriod
     * to set
     */
    public void setInterestEarnedForCurrentPeriod(float interestEarnedForCurrentPeriod) {
        this.interestEarnedForCurrentPeriod = interestEarnedForCurrentPeriod;
    }

    /**
     * @return the totalInterestEarned
     */
    public float getTotalInterestEarned() {
        return totalInterestEarned;
    }

    /**
     * @param totalInterestEarned the totalInterestEarned to set
     */
    public void setTotalInterestEarned(float totalInterestEarned) {
        this.totalInterestEarned = totalInterestEarned;
    }

    /**
     * @return the isBeginingOfYear
     */
    public boolean isIsBeginingOfYear() {
        return isBeginingOfYear;
    }

    /**
     * @param isBeginingOfYear the isBeginingOfYear to set
     */
    public void setIsBeginingOfYear(boolean isBeginingOfYear) {
        this.isBeginingOfYear = isBeginingOfYear;
    }

    /**
     * @return the isEndOfYear
     */
    public boolean isIsEndOfYear() {
        return isEndOfYear;
    }

    /**
     * @param isEndOfYear the isEndOfYear to set
     */
    public void setIsEndOfYear(boolean isEndOfYear) {
        this.isEndOfYear = isEndOfYear;
    }

    /**
     * @return the sequenceNumber
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * @param sequenceNumber the sequenceNumber to set
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

}
