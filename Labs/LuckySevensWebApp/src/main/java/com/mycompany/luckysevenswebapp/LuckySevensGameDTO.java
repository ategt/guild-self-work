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
public class LuckySevensGameDTO {
    
    private int startingBet;
    private int rollCounter;
    private int rollNumberAtMaxAmountHeld;
    private int maxAmountHeld;

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
