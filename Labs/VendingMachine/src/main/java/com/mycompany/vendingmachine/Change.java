/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine;

/**
 *
 * @author apprentice
 */
public class Change {

    private int quarters;
    private int nickles;
    private int dimes;
    private int pennies;

    public Change(int pennies) {

        this.pennies = pennies;
        
        calculateQuarters();
        calculateDimes();
        calculateNickles();
    }

    private void calculateQuarters() {
        final int VALUE_OF_COIN = 25;

        while ( pennies >= VALUE_OF_COIN) {
            quarters++;
            pennies -= VALUE_OF_COIN;
        }

    }

    private void calculateDimes() {

        final int VALUE_OF_COIN = 10;

        while ( pennies >= VALUE_OF_COIN) {
            dimes++;
            pennies -= VALUE_OF_COIN;
        }

    }

    private void calculateNickles() {

        final int VALUE_OF_COIN = 5;

        while ( pennies >= VALUE_OF_COIN) {
            nickles++;
            pennies -= VALUE_OF_COIN;
        }

    }

    /**
     * @return the quarters
     */
    public int getQuarters() {
        return quarters;
    }

    /**
     * @return the nickles
     */
    public int getNickles() {
        return nickles;
    }

    /**
     * @return the dimes
     */
    public int getDimes() {
        return dimes;
    }

    /**
     * @return the pennies
     */
    public int getPennies() {
        return pennies;
    }

}
