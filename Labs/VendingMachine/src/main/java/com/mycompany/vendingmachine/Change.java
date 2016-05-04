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

        calculateQuarters();
        calculateDimes();
        calculateNickles();
    }

    private void calculateQuarters() {
        int valueOfCoin = 25;

        while (getPennies() >= valueOfCoin) {
            quarters++;
            pennies -= valueOfCoin;
        }

    }

    private void calculateDimes() {

        int valueOfCoin = 10;

        while (getPennies() >= valueOfCoin) {
            dimes++;
            pennies -= valueOfCoin;
        }

    }

    private void calculateNickles() {

        int valueOfCoin = 5;

        while (getPennies() >= valueOfCoin) {
            nickles++;
            pennies -= valueOfCoin;
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
