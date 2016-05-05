/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.dto.Change;

/**
 *
 * @author apprentice
 */
public class ChangeMaker {

    Change change;

    public Change makeChange(int balance) {

        change = new Change();

        int pennies = balance;

        pennies = calculateQuarters(pennies);
        pennies = calculateDimes(pennies);
        pennies = calculateNickles(pennies);

        change.setPennies(pennies);
        return change;
        
    }

    private int calculateQuarters(int pennies) {
        final int VALUE_OF_COIN = 25;
        int quarters = 0;

        while (pennies >= VALUE_OF_COIN) {
            quarters++;
            pennies -= VALUE_OF_COIN;
        }

        change.setQuarters(quarters);
        return pennies;
    }

    private int calculateDimes(int pennies) {

        final int VALUE_OF_COIN = 10;
        int dimes = 0;
        while (pennies >= VALUE_OF_COIN) {
            dimes++;
            pennies -= VALUE_OF_COIN;
        }
        change.setDimes(dimes);

        return pennies;
    }

    private int calculateNickles(int pennies) {

        final int VALUE_OF_COIN = 5;
        int nickles = 0;

        while (pennies >= VALUE_OF_COIN) {
            nickles++;
            pennies -= VALUE_OF_COIN;
        }

        change.setNickles(nickles);

        return pennies;
    }

}
