/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.factorizor;

import java.util.List;

/**
 *
 * @author apprentice
 */
public class NumberCharacteristics {

    private int numberToBeFactorized;
    
    private int numberOfFactors;
    private int sumOfFactors;
    private List<Integer> factors;
    
    private boolean isPrime;
    private boolean isPerfect;

    /**
     * @return the numberOfFactors
     */
    public int getNumberOfFactors() {
        return numberOfFactors;
    }

    /**
     * @param numberOfFactors the numberOfFactors to set
     */
    public void setNumberOfFactors(int numberOfFactors) {
        this.numberOfFactors = numberOfFactors;
    }

    /**
     * @return the sumOfFactors
     */
    public int getSumOfFactors() {
        return sumOfFactors;
    }

    /**
     * @param sumOfFactors the sumOfFactors to set
     */
    public void setSumOfFactors(int sumOfFactors) {
        this.sumOfFactors = sumOfFactors;
    }

    /**
     * @return the factors
     */
    public List<Integer> getFactors() {
        return factors;
    }

    /**
     * @param factors the factors to set
     */
    public void setFactors(List<Integer> factors) {
        this.factors = factors;
    }

    /**
     * @return the isPrime
     */
    public boolean isIsPrime() {
        return isPrime;
    }

    /**
     * @param isPrime the isPrime to set
     */
    public void setIsPrime(boolean isPrime) {
        this.isPrime = isPrime;
    }

    /**
     * @return the isPerfect
     */
    public boolean isIsPerfect() {
        return isPerfect;
    }

    /**
     * @param isPerfect the isPerfect to set
     */
    public void setIsPerfect(boolean isPerfect) {
        this.isPerfect = isPerfect;
    }

    /**
     * @return the numberToBeFactorized
     */
    public int getNumberToBeFactorized() {
        return numberToBeFactorized;
    }

    /**
     * @param numberToBeFactorized the numberToBeFactorized to set
     */
    public void setNumberToBeFactorized(int numberToBeFactorized) {
        this.numberToBeFactorized = numberToBeFactorized;
    }

}
