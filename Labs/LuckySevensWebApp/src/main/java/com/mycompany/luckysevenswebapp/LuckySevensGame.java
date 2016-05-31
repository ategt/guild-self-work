/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luckysevenswebapp;

/**
 *
 * @author apprentice
 */
public class LuckySevensGame {

    private int startingBet;
    private int currentBalance;// = 0;
    private int maxAmountHeld; //= 0;
    private int rollNumberAtMaxAmountHeld; //= 0;
    private int rollCounter;

    /**
     * @return the currentBalance
     */
    public int getCurrentBalance() {
        return currentBalance;
    }

    /**
     * @param currentBalance the currentBalance to set
     */
    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * @return the maxAmountHeld
     */
    public int getMaxAmountHeld() {
        return maxAmountHeld;
    }

    /**
     * @param maxAmountHeld the maxAmountHeld to set
     */
    public void setMaxAmountHeld(int maxAmountHeld) {
        this.maxAmountHeld = maxAmountHeld;
    }

    /**
     * @return the rollNumberAtMaxAmountHeld
     */
    public int getRollNumberAtMaxAmountHeld() {
        return rollNumberAtMaxAmountHeld;
    }

    /**
     * @param rollNumberAtMaxAmountHeld the rollNumberAtMaxAmountHeld to set
     */
    public void setRollNumberAtMaxAmountHeld(int rollNumberAtMaxAmountHeld) {
        this.rollNumberAtMaxAmountHeld = rollNumberAtMaxAmountHeld;
    }

    /**
     * @return the rollCounter
     */
    public int getRollCounter() {
        return rollCounter;
    }

    /**
     * @param rollCounter the rollCounter to set
     */
    public void setRollCounter(int rollCounter) {
        this.rollCounter = rollCounter;
    }

    /**
     * @return the startingBet
     */
    public int getStartingBet() {
        return startingBet;
    }

    /**
     * @param startingBet the startingBet to set
     */
    public void setStartingBet(int startingBet) {
        this.startingBet = startingBet;
    }
}
