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
    private float interestEarned;
    private float totalInterestEarned;

    private boolean isBeginingOfYear;
    private boolean isEndOfYear;

    private int sequenceNumber;
    private int compoundingsPerYear;

    private CompoundingPeriod previousInstance;
    private CompoundingPeriod nextInstance;

    public CompoundingPeriod(CompoundingPeriod previousInstance, float startingBalance, float interestRate, int sequenceNumber) {
        setStartingBalance(startingBalance);
        setInterestRate(interestRate);
        setSequenceNumber(sequenceNumber);
        setPreviousInstance(previousInstance);

    }

    public CompoundingPeriod(float startingBalance, float interestRate, int sequenceNumber) {
        setStartingBalance(startingBalance);
        setInterestRate(interestRate);
        setSequenceNumber(sequenceNumber);

    }

    public String getPrettyBalance() {
        return "";
    }

    public String getPrettyInterest() {
        return "";
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
        if (endingBalance == 0) {
            populateEmptyFields();
        }

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
        if (interestRate == 0) {
            populateEmptyFields();
        }

        return interestRate;
    }

    /**
     * @param interestRate the interestRate to set
     */
    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public void resetTotalInterestEarned() {

        this.totalInterestEarned = getInterestEarned();
    }

    /**
     * @return the totalInterestEarned
     */
    public float getTotalInterestEarned() {

        if (isIsBeginingOfYear()) {
            resetTotalInterestEarned();
        }

        return totalInterestEarned;
    }

    //public void 
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
        if (sequenceNumber == 0) {
            populateEmptyFields();
        }

        return sequenceNumber;
    }

    /**
     * @param sequenceNumber the sequenceNumber to set
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public float calculateInterestEarned(float balance, float rate) {
        //float calculatedInterest = 0;
        return balance * rate;
    }

    public float calculateEndingBalance(float balance, float interestEarned) {
        return balance + interestEarned;
    }

    public float calculateEndingBalanceFromRate(float balance, float rate) {

        return calculateEndingBalance(getStartingBalance(), calculateInterestEarned(getStartingBalance(), getInterestRate()));
    }

    public void populateEmptyFields() {

        if (getInterestEarned() == 0) {
            setInterestEarned(calculateInterestEarned(getStartingBalance(), getInterestRate()));
        }

        if (getEndingBalance() == 0) {
            setEndingBalance(calculateEndingBalance(getStartingBalance(), getInterestEarned()));
        }
        //setTotalEarnedInterest();  // get the last object and add this periods interest to its total interest.

        if (isBeginingOfYear(getSequenceNumber(), getCompoundingsPerYear()) == false) {
            setIsBeginingOfYear(isBeginingOfYear(getSequenceNumber(), getCompoundingsPerYear()));
        }

        if (isEndOfYear(getSequenceNumber(), getCompoundingsPerYear()) == false) {
            setIsEndOfYear(isEndOfYear(getSequenceNumber(), getCompoundingsPerYear()));
        }

    }

    public boolean isBeginingOfYear(int sequenceNumber, int compoundingsPerYear) {
        return (sequenceNumber % compoundingsPerYear == 0);

    }

    public boolean isEndOfYear(int sequenceNumber, int compoundingsPerYear) {

        return (sequenceNumber % compoundingsPerYear) == (compoundingsPerYear - 1);
    }

    /**
     * @return the compoundingsPerYear
     */
    public int getCompoundingsPerYear() {
        if (compoundingsPerYear == 0) {
            populateEmptyFields();
        }

        return compoundingsPerYear;
    }

    /**
     * @param compoundingsPerYear the compoundingsPerYear to set
     */
    public void setCompoundingsPerYear(int compoundingsPerYear) {
        this.compoundingsPerYear = compoundingsPerYear;
    }

    /**
     * @return the interestEarned
     */
    public float getInterestEarned() {

        if (interestEarned == 0) {
            setInterestEarned(calculateInterestEarned(getStartingBalance(), getInterestRate()));
        }

        return interestEarned;
    }

    /**
     * @param interestEarned the interestEarned to set
     */
    public void setInterestEarned(float interestEarned) {
        this.interestEarned = interestEarned;
    }

    public void setPreviousInstance(CompoundingPeriod previousInstance) {
        this.previousInstance = previousInstance;
    }

    /**
     * @return the previousInstance
     */
    public CompoundingPeriod getPreviousInstance() {
        return previousInstance;
    }

    /**
     * @return the nextInstance
     */
    public CompoundingPeriod getNextInstance() {
        return nextInstance;
    }

    /**
     * @param nextInstance the nextInstance to set
     */
    public void setNextInstance(CompoundingPeriod nextInstance) {
        this.nextInstance = nextInstance;
    }
}
